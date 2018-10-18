package de.difuture.ekut.pht.lib.train.api.execution.docker

import de.difuture.ekut.pht.lib.runtime.docker.DockerRuntimeClient
import de.difuture.ekut.pht.lib.train.station.StationInfo
import de.difuture.ekut.pht.lib.train.api.TrainCommand
import de.difuture.ekut.pht.lib.train.api.interf.arrival.DockerRegistryTrainArrival
import de.difuture.ekut.pht.lib.train.api.output.TrainResponse

object RunAlgorithm : DockerArrivalExecution<TrainResponse.RunAlgorithmResponse> {

    override val command = TrainCommand.RUN_ALGORITHM

    override fun execArrival(interf: DockerRegistryTrainArrival, client: DockerRuntimeClient, info: StationInfo) =
            // RunAlgorithm will not remove the container on finish, as the container might be used for the new trainImage
            execute<TrainResponse.RunAlgorithmResponse>(this.command, interf, client, info, rm = false)
}
