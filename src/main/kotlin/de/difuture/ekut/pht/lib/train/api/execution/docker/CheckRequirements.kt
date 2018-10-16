package de.difuture.ekut.pht.lib.train.api.execution.docker

import de.difuture.ekut.pht.lib.runtime.docker.DockerRuntimeClient
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.TrainCommand
import de.difuture.ekut.pht.lib.train.api.output.TrainResponse
import de.difuture.ekut.pht.lib.train.api.interf.arrival.DockerRegistryTrainArrival

object CheckRequirements
    : DockerArrivalExecution<TrainResponse.CheckRequirementsResponse> {

    override val command = TrainCommand.CHECK_REQUIREMENTS

    override fun execArrival(interf: DockerRegistryTrainArrival, client: DockerRuntimeClient, info: StationInfo) =
            execute<TrainResponse.CheckRequirementsResponse>(this.command, interf, client, info)
}
