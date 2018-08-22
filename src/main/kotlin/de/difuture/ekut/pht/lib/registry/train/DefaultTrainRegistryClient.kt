package de.difuture.ekut.pht.lib.registry.train

import com.fasterxml.jackson.databind.ObjectMapper
import de.difuture.ekut.pht.lib.common.HostPortTuple
import de.difuture.ekut.pht.lib.common.docker.DockerImageId
import de.difuture.ekut.pht.lib.runtime.DockerContainerOutput
import de.difuture.ekut.pht.lib.common.docker.DockerRepositoryName
import de.difuture.ekut.pht.lib.common.docker.DockerTag
import de.difuture.ekut.pht.lib.registry.docker.IDockerRegistryClient
import de.difuture.ekut.pht.lib.registry.train.api.IDockerTrainArrival
import de.difuture.ekut.pht.lib.registry.train.api.IDockerTrainDeparture
import de.difuture.ekut.pht.lib.registry.train.api.ITrainArrival
import de.difuture.ekut.pht.lib.registry.train.api.RunInfo
import de.difuture.ekut.pht.lib.registry.train.id.ITrainId
import de.difuture.ekut.pht.lib.registry.train.tag.ITrainTag
import de.difuture.ekut.pht.lib.runtime.IDockerClient

/**
 * Canonical implementation of the [ITrainRegistryClient].
 *
 * @param dockerRegistryClient The [IDockerRegistryClient] that should be used for interacting with the train registry
 * @param namespace The namespace of the Docker Registry that is targeted by this client. If the name
 * space is null, then the root namespace of the registry is targeted
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
class DefaultTrainRegistryClient(
        private val dockerRegistryClient: IDockerRegistryClient,
        private val namespace: String? = null) : ITrainRegistryClient<IDockerClient> {

    /**
     * Implementations of the ITrainInterface
     */

    private data class DockerTrainDeparture(
            override val client: IDockerClient,
            override val trainId: ITrainId,
            override val trainTag: ITrainTag,
            private val imageId: DockerImageId
    ) : IDockerTrainDeparture {

        override fun isValid() = imageId in client.images()

        override fun printSummary(): String {

            if ( ! this.isValid()) {

                throw IllegalStateException(
                        "The Docker image with the ID $imageId was deleted. Please use the corresponding arrival to rerun the algorithm")
            }
            return this.client.run(imageId, listOf("print_summary"), rm=true).stdout
        }
    }

    private data class DockerTrainArrival(
            override val trainId: ITrainId,
            override val trainTag: ITrainTag,
            private val hostTuple : HostPortTuple,
            private val namespace: String?

    ) : IDockerTrainArrival {


        private fun run(dockerClient : IDockerClient, rm : Boolean, commands : List<String>) : DockerContainerOutput {

            // ensure that the image is pulled from remote and determine the ID of the Docker image
            val imageId = dockerClient.pull(
                    DockerRepositoryName(
                        trainId.repr,
                        hostPortTuple = hostTuple),
                    DockerTag(trainTag.repr))
            return dockerClient.run(imageId, commands, rm)
        }

        override fun printSummary(client: IDockerClient, info : RunInfo)
                = run(client, true, info.commandLine.plus("print_summary")).stdout

        override fun checkRequirements(client: IDockerClient, info : RunInfo)
                = run(client, true, info.commandLine.plus("check_requirements")).exitCode == 0


        override fun runAlgorithm(client: IDockerClient, info : RunInfo): DockerTrainDeparture {

            val containerOutput = run(client, false, info.commandLine.plus("run_algorithm"))

            val containerId = containerOutput.containerId
            val departureTag = ObjectMapper().readTree(containerOutput.stdout)["departure_tag"].asText()
            val trainTag  = ITrainTag.of(departureTag)

            val imageId = client.commit(
                    containerId,
                    DockerRepositoryName(trainId.repr),
                    DockerTag(departureTag))
            return DockerTrainDeparture(client, trainId, trainTag, imageId)
        }
    }


    override fun listTrainArrivals(): List<IDockerTrainArrival> =
            dockerRegistryClient
                    .listRepositories()
                    .flatMap { repo ->  dockerRegistryClient.listTags(repo).map {tag ->

                        // Count the number of '/' characters in the repository
                        val count = repo.count { c -> c == NAMESPACE_SEP }

                        // Only 0 or 1 '/' characters are allowed, and they determine the namespace and the trainID
                        // Collect namespace, reponame, and tag here
                        when (count) {

                            0 -> Triple(null, repo, tag)
                            1 -> Triple(
                                    repo.substringBefore(NAMESPACE_SEP),
                                    repo.substringAfter(NAMESPACE_SEP),
                                    tag)
                            else -> throw IllegalStateException("Too many '$NAMESPACE_SEP' characters in Docker Registry response")
                        }
                    }}.filter { (namespace, repo, _) ->

                        // The repo must be a valid TrainId and the namespace must be the one that we target
                        ITrainId.isValid(repo) && namespace == this.namespace
                    }.map { (namespace, repo, tag) ->

                        // Create The DockerTrainArrival based on trainId, namespace, tag and original registry.
                        DockerTrainArrival(
                                ITrainId.of(repo),
                                ITrainTag.of(tag),
                                HostPortTuple(dockerRegistryClient.uri),
                                namespace)
                    }


    override fun listTrainArrivals(tag: ITrainTag): List<IDockerTrainArrival> =
        this.listTrainArrivals().filter { it.trainTag == tag }



    override fun getTrainArrival(id: ITrainId, tag: ITrainTag): ITrainArrival<IDockerClient>? {

        val arrivals = this.listTrainArrivals().filter { it.trainTag == tag && it.trainId == id}

        return when(arrivals.size) {

            0 -> null
            1 -> arrivals[0]
            else -> throw IllegalStateException("Tuple of TrainID and trainTag must only resolve to one TrainArrival for each Train Registry")
        }
    }

    override fun hasTrainArrival(id: ITrainId, tag: ITrainTag) = this.getTrainArrival(id, tag) != null


    override fun push(departure: IDockerTrainDeparture) {

        val name = DockerRepositoryName(
                departure.trainId.repr,
                hostPortTuple = HostPortTuple(dockerRegistryClient.uri))

        val tag = DockerTag(departure.trainTag.repr)

        departure.client.push(name, tag)
    }


    companion object {

        const val NAMESPACE_SEP = '/'
    }
}
