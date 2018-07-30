package de.difuture.ekut.pht.lib.registry.train.departure

import de.difuture.ekut.pht.lib.common.docker.DockerRepositoryName
import de.difuture.ekut.pht.lib.common.docker.DockerTag
import de.difuture.ekut.pht.lib.common.docker.HostPort
import de.difuture.ekut.pht.lib.registry.docker.data.DockerRegistryEvent
import de.difuture.ekut.pht.lib.registry.train.departure.tag.TrainTag
import de.difuture.ekut.pht.lib.runtime.DockerClientException
import de.difuture.ekut.pht.lib.runtime.IDockerClient
import java.net.URI

data class DockerTrainDeparture(
        override val trainId: TrainId,
        override val trainTag: TrainTag,
        private val host : HostPort
) : ITrainDeparture<IDockerClient> {


    private fun <T> runOrDockerException(msg : String, fn : () -> T? ) : T =

            fn.invoke() ?: throw DockerClientException(msg)



    override fun printSummary(client: IDockerClient): String {

        val imageId  = runOrDockerException("Cannot pull image ") {

            client.pull(
                    DockerRepositoryName(
                            trainId.stringRepresentation), DockerTag(trainTag.stringRepresentation))
        }
        val containerID = runOrDockerException("Failed to run container") {

            client.run(imageId, listOf("print_summary"))
        }
        return runOrDockerException("Container does not exist") {

            client.log(containerID)
        }
    }
}
