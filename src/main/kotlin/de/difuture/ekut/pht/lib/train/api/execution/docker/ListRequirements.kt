package de.difuture.ekut.pht.lib.train.api.execution.docker

import de.difuture.ekut.pht.lib.runtime.docker.DockerRuntimeClient
import de.difuture.ekut.pht.lib.train.station.StationInfo
import de.difuture.ekut.pht.lib.train.api.TrainCommand
import de.difuture.ekut.pht.lib.train.api.interf.arrival.DockerRegistryTrainArrival
import de.difuture.ekut.pht.lib.train.api.output.TrainResponse

object ListRequirements : DockerArrivalExecution<TrainResponse.ListRequirementsResponse> {

    override val command = TrainCommand.LIST_REQUIREMENTS

    /**
     * Only check whether the exit code of the container is 0
     */
    override fun execArrival(interf: DockerRegistryTrainArrival, client: DockerRuntimeClient, info: StationInfo) =
            execute<TrainResponse.ListRequirementsResponse>(this.command, interf, client, info)
}
