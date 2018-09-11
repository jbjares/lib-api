package de.difuture.ekut.pht.lib.train.registry

import de.difuture.ekut.pht.lib.common.HostPortTuple
import de.difuture.ekut.pht.lib.common.docker.DockerImageId
import de.difuture.ekut.pht.lib.runtime.docker.DockerContainerOutput
import de.difuture.ekut.pht.lib.common.docker.DockerRepositoryName
import de.difuture.ekut.pht.lib.common.docker.DockerTag
import de.difuture.ekut.pht.lib.train.api.interf.IDockerTrainArrival
import de.difuture.ekut.pht.lib.train.api.interf.IDockerTrainDeparture
import de.difuture.ekut.pht.lib.train.api.interf.ITrainArrival
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.id.ITrainId
import de.difuture.ekut.pht.lib.train.tag.ITrainTag
import de.difuture.ekut.pht.lib.runtime.docker.IDockerClient
import de.difuture.ekut.pht.lib.train.RunAlgorithmFailed
import jdregistry.client.DockerRegistryGetClient

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
    private val dockerRegistryClient: DockerRegistryGetClient,
    private val namespace: String? = null
) : ITrainRegistryClient<IDockerClient> {

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

            if (!this.isValid()) {

                throw IllegalStateException(
                        "The Docker image with the ID $imageId was deleted. Please use the corresponding arrival to rerun the algorithm")
            }
            return this.client.run(imageId, listOf("print_summary"), rm = true).stdout
        }
    }

    private data class DockerTrainArrival(
        override val trainId: ITrainId,
        override val trainTag: ITrainTag,
        private val hostTuple: HostPortTuple,
        private val namespace: String?

    ) : IDockerTrainArrival {

        private fun run(dockerClient: IDockerClient, rm: Boolean, commands: List<String>): DockerContainerOutput {

            // ensure that the image is pulled from remote and determine the ID of the Docker image
            val imageId = dockerClient.pull(
                    toDockerRepoName(this.namespace, trainId, hostTuple),
                    DockerTag(trainTag.repr))

            // Actually run the image
            return dockerClient.run(imageId, commands, rm)
        }

        override fun printSummary(client: IDockerClient, info: StationInfo) = run(client, true, info.commandLine.plus("print_summary")).stdout

        override fun checkRequirements(client: IDockerClient, info: StationInfo) = run(client, true, info.commandLine.plus("check_requirements")).exitCode == 0

        override fun runAlgorithm(client: IDockerClient, info: StationInfo): DockerTrainDeparture {

            // Run the container by passing the command line and tool name
            val containerOutput = run(client, false, info.commandLine.plus("run_algorithm"))

            // If the algorithm terminates with a non-zero exitCode, a proper TrainDeparture
            // cannot be generated
            if (containerOutput.exitCode != 0) {

                throw RunAlgorithmFailed("Run Algorithm has failed!", containerOutput)
            }

            // The Departure Tag is always going to be the StationID
            val departureTag = ITrainTag.of(info.stationID.toString())

            // Commit the container and create the image
            val imageId = client.commit(
                    containerOutput.containerId,
                    toDockerRepoName(this.namespace, trainId, hostTuple),
                    DockerTag(departureTag.repr))

            return DockerTrainDeparture(client, trainId, departureTag, imageId)
        }
    }

    override fun listTrainArrivals(): List<IDockerTrainArrival> =
            dockerRegistryClient
                    .listRepositories()
                    .flatMap { repo ->
                        val tags = dockerRegistryClient.listTags(repo).tags ?: emptyList()
                        tags.map { tag ->

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
                        }
                    }.filter { (namespace, repo, _) ->

                        // The repo must be a valid TrainId and the namespace must be the one that we target
                        ITrainId.isValid(repo) && namespace == this.namespace
                    }.map { (namespace, repo, tag) ->

                        // Create The DockerRegistryTrainArrival based on trainId, namespace, tag and original registry.
                        DockerTrainArrival(
                                ITrainId.of(repo),
                                ITrainTag.of(tag),
                                HostPortTuple(dockerRegistryClient.uri),
                                namespace)
                    }

    override fun listTrainArrivals(tag: ITrainTag): List<IDockerTrainArrival> =
            this.listTrainArrivals().filter { it.trainTag == tag }

    override fun listTrainArrivals(id: ITrainId): List<ITrainArrival<IDockerClient>> =
            this.listTrainArrivals().filter { it.trainId == id }

    override fun getTrainArrival(id: ITrainId, tag: ITrainTag): ITrainArrival<IDockerClient>? {

        val arrivals = this.listTrainArrivals().filter { it.trainTag == tag && it.trainId == id }

        return when (arrivals.size) {

            0 -> null
            1 -> arrivals[0]
            else -> throw IllegalStateException("Tuple of TrainID and trainTag must only resolve to one TrainArrival for each Train Registry")
        }
    }

    override fun hasTrainArrival(id: ITrainId, tag: ITrainTag) = this.getTrainArrival(id, tag) != null

    override fun push(departure: IDockerTrainDeparture) {

        val name = toDockerRepoName(
                this.namespace,
                departure.trainId,
                HostPortTuple(dockerRegistryClient.uri))

        val tag = DockerTag(departure.trainTag.repr)

        departure.client.push(name, tag)
    }

    companion object {

        const val NAMESPACE_SEP = '/'

        private fun toDockerRepoName(namespace: String?, id: ITrainId, host: HostPortTuple) =

                namespace?.let { DockerRepositoryName(namespace, id.repr, hostPortTuple = host) }
                        ?: DockerRepositoryName(id.repr, hostPortTuple = host)
    }
}
