package de.difuture.ekut.pht.lib.registry.train.arrival

import de.difuture.ekut.pht.lib.common.docker.DockerContainerOutput
import de.difuture.ekut.pht.lib.common.docker.DockerRepositoryName
import de.difuture.ekut.pht.lib.common.docker.DockerTag
import de.difuture.ekut.pht.lib.common.docker.HostPort
import de.difuture.ekut.pht.lib.registry.train.TrainId
import de.difuture.ekut.pht.lib.registry.train.tag.TrainTag
import de.difuture.ekut.pht.lib.runtime.IDockerClient

data class DockerTrainArrival(
        override val trainId: TrainId,
        override val trainTag: TrainTag,
        private val host : HostPort
) : ITrainArrival<IDockerClient> {

    private fun pullAndRun(client : IDockerClient, command : String, timeout: Int) : DockerContainerOutput {

        val imageId = client.pull(
                repo=DockerRepositoryName(this.trainId.canonicalStringRepresentation, hostPort = this.host),
                tag= DockerTag(trainTag.canonicalStringRepresentation))
        return client.run(imageId, listOf(command), true, timeout)
    }


    override fun printSummary(client: IDockerClient, timeout : Int) =
            pullAndRun(client, "print_summary", timeout).stdout

    override fun checkRequirements(client: IDockerClient, timeout: Int) : Boolean =
            pullAndRun(client, "check_requirements", timeout).exitCode == 0
}
