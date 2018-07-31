package de.difuture.ekut.pht.lib.registry.train.arrival

import de.difuture.ekut.pht.lib.common.docker.DockerRepositoryName
import de.difuture.ekut.pht.lib.common.docker.DockerTag
import de.difuture.ekut.pht.lib.common.docker.HostPort
import de.difuture.ekut.pht.lib.registry.train.arrival.tag.TrainTag
import de.difuture.ekut.pht.lib.runtime.DockerClientException
import de.difuture.ekut.pht.lib.runtime.IDockerClient

data class DockerTrainArrival(
        override val trainId: TrainId,
        override val trainTag: TrainTag,
        private val host : HostPort
) : ITrainArrival<IDockerClient> {


    private fun <T> runOrDockerException(msg : String, fn : () -> T? ) : T =

            fn.invoke() ?: throw DockerClientException(msg)


    override fun printSummary(client: IDockerClient, timeout : Int): String {

        // Pull the image for this dockerTrain Arrival
        val imageId = client.pull(
                DockerRepositoryName(trainId.stringRepresentation, hostPort = host),
                DockerTag(trainTag.stringRepresentation)
        )
        return client.run(imageId, listOf("print_summary"), true, timeout).stdout
    }
}
