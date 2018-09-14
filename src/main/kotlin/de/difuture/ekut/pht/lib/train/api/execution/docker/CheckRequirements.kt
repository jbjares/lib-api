package de.difuture.ekut.pht.lib.train.api.execution.docker

import de.difuture.ekut.pht.lib.runtime.docker.DockerClient
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.command.CheckRequirements
import de.difuture.ekut.pht.lib.train.api.execution.DockerArrivalExecution
import de.difuture.ekut.pht.lib.train.api.interf.arrival.DockerRegistryTrainArrival

object CheckRequirements : DockerArrivalExecution<Boolean, DockerRegistryTrainArrival> {

    override val command = CheckRequirements

    /**
     * Only check whether the exit code of the container is 0
     */
    override fun execArrival(interf: DockerRegistryTrainArrival, client: DockerClient, info: StationInfo) =
            execute(interf, client, command, info) { it.exitCode == 0 }
}
