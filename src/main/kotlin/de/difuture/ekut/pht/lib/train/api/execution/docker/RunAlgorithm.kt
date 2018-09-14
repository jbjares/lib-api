package de.difuture.ekut.pht.lib.train.api.execution.docker

import de.difuture.ekut.pht.lib.runtime.docker.DockerClient
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.command.RunAlgorithm
import de.difuture.ekut.pht.lib.train.api.execution.DockerArrivalExecution
import de.difuture.ekut.pht.lib.train.api.interf.arrival.DockerRegistryTrainArrival

object RunAlgorithm : DockerArrivalExecution<Boolean, DockerRegistryTrainArrival> {

    override val command = RunAlgorithm

    /**
     * Only check whether the exit code of the container is 0
     */
    override fun execArrival(interf: DockerRegistryTrainArrival, client: DockerClient, info: StationInfo) =
            execute(interf, client, command, info) { it.exitCode == 0 }
}
