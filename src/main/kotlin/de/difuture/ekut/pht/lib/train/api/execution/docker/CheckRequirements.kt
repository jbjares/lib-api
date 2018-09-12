package de.difuture.ekut.pht.lib.train.api.execution.docker

import de.difuture.ekut.pht.lib.runtime.docker.IDockerClient
import de.difuture.ekut.pht.lib.runtime.docker.pullAndRun
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.command.CheckRequirements
import de.difuture.ekut.pht.lib.train.api.execution.DockerArrivalExecution
import de.difuture.ekut.pht.lib.train.api.interf.arrival.DockerRegistryTrainArrival

object CheckRequirements : DockerArrivalExecution<Boolean, DockerRegistryTrainArrival> {

    override val command = CheckRequirements

    override fun execArrival(interf: DockerRegistryTrainArrival, client: IDockerClient, info: StationInfo) =

            client.pullAndRun(
                    interf.name,
                    interf.tag,
                    commandLine(info, command),
                    true).exitCode == 0
}
