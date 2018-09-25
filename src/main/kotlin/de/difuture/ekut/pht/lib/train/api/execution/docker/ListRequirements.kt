package de.difuture.ekut.pht.lib.train.api.execution.docker

import de.difuture.ekut.pht.lib.runtime.docker.DockerRuntimeClient
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.command.ListRequirements
import de.difuture.ekut.pht.lib.train.api.data.ListRequirementsResponse
import de.difuture.ekut.pht.lib.train.api.execution.DockerArrivalExecution
import de.difuture.ekut.pht.lib.train.api.interf.arrival.DockerRegistryTrainArrival

object ListRequirements : DockerArrivalExecution<ListRequirementsResponse, DockerRegistryTrainArrival> {

    override val command = ListRequirements

    /**
     * Only check whether the exit code of the container is 0
     */
    override fun execArrival(interf: DockerRegistryTrainArrival, client: DockerRuntimeClient, info: StationInfo) =
            execute<ListRequirementsResponse>(interf, this.command, client, info)
}
