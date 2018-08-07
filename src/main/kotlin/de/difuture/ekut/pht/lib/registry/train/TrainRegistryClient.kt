package de.difuture.ekut.pht.lib.registry.train

import de.difuture.ekut.pht.lib.common.HostPortTuple
import de.difuture.ekut.pht.lib.common.docker.DockerImageId
import de.difuture.ekut.pht.lib.runtime.DockerContainerOutput
import de.difuture.ekut.pht.lib.common.docker.DockerRepositoryName
import de.difuture.ekut.pht.lib.common.docker.DockerTag
import de.difuture.ekut.pht.lib.registry.docker.IDockerRegistryClient
import de.difuture.ekut.pht.lib.registry.train.api.IDockerTrainArrival
import de.difuture.ekut.pht.lib.registry.train.api.IDockerTrainDeparture
import de.difuture.ekut.pht.lib.registry.train.api.ITrainArrival
import de.difuture.ekut.pht.lib.registry.train.id.ITrainId
import de.difuture.ekut.pht.lib.registry.train.tag.ITrainTag
import de.difuture.ekut.pht.lib.runtime.IDockerClient

/**
 * Canonical implementation of the [ITrainRegistryClient].
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
class TrainRegistryClient(
        private val dockerRegistryClient: IDockerRegistryClient) : ITrainRegistryClient<IDockerClient> {


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
            private val hostTuple : HostPortTuple

    ) : IDockerTrainArrival {


        private fun run(dockerClient : IDockerClient, command : String) : DockerContainerOutput {

            // ensure that the image is pulled from remote and determine the ID of the Docker image
            val imageId = dockerClient.pull(
                    DockerRepositoryName(
                        trainId.canonicalStringRepresentation,
                        hostPortTuple = hostTuple),
                    DockerTag(trainTag.canonicalStringRepresentation))
            return dockerClient.run(imageId, listOf(command), true)
        }

        override fun printSummary(client: IDockerClient) = run(client, "print_summary").stdout

        override fun checkRequirements(client: IDockerClient) = run(client, "check_requirements").exitCode == 0

        override fun runAlgorithm(client: IDockerClient): IDockerTrainDeparture {

            val containerOutput = run(client, "run_algorithm")
            val containerId = containerOutput.containerId

            // Running the algorithm will return the Train Tag for the new image (and consequently also the Docker Tag
            val dockerTag = DockerTag(containerOutput.stdout)
            val trainTag  = ITrainTag.of(dockerTag)

            val imageId = client.commit(
                    containerId,
                    DockerRepositoryName(trainId.canonicalStringRepresentation),
                    dockerTag)

            return DockerTrainDeparture(
                    client, trainId, trainTag, imageId)
        }
    }


    override fun listTrainArrivals(): List<IDockerTrainArrival> =
            dockerRegistryClient
                    .listRepositories()
                    .flatMap { repo ->  dockerRegistryClient.listTags(repo).map {

                        DockerTrainArrival(
                                ITrainId.of(repo),
                                ITrainTag.of(it), HostPortTuple(dockerRegistryClient.uri))
                    } }


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


    override fun push(departure: IDockerTrainDeparture) {

        val name = DockerRepositoryName(
                departure.trainId.canonicalStringRepresentation,
                hostPortTuple = HostPortTuple(dockerRegistryClient.uri))

        val tag = DockerTag(departure.trainTag.canonicalStringRepresentation)

        departure.client.push(name, tag)
    }
}
