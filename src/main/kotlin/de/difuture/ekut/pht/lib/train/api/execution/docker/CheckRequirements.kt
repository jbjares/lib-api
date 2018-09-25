package de.difuture.ekut.pht.lib.train.api.execution.docker

import de.difuture.ekut.pht.lib.runtime.docker.DockerRuntimeClient
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.command.CheckRequirements
import de.difuture.ekut.pht.lib.train.api.data.CheckRequirementsResponse
import de.difuture.ekut.pht.lib.train.api.execution.DockerArrivalExecution
import de.difuture.ekut.pht.lib.train.api.interf.arrival.DockerRegistryTrainArrival

object CheckRequirements : DockerArrivalExecution<CheckRequirementsResponse, DockerRegistryTrainArrival> {

    override val command = CheckRequirements

    override fun execArrival(interf: DockerRegistryTrainArrival, client: DockerRuntimeClient, info: StationInfo) =
            execute<CheckRequirementsResponse>(interf, this.command, client, info)
}
