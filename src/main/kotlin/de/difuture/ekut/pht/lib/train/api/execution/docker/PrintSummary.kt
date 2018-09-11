package de.difuture.ekut.pht.lib.train.api.execution.docker

import de.difuture.ekut.pht.lib.common.docker.DockerRepositoryName
import de.difuture.ekut.pht.lib.runtime.docker.IDockerClient
import de.difuture.ekut.pht.lib.runtime.docker.pullAndRun
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.command.ArrivalCommand
import de.difuture.ekut.pht.lib.train.api.command.PrintSummary
import de.difuture.ekut.pht.lib.train.api.execution.DockerArrivalExecution
import de.difuture.ekut.pht.lib.train.api.interf.DockerRegistryTrainArrival
import de.difuture.ekut.pht.lib.train.api.interf.ITrainArrival


/**
 * Specifies how the Print Summary command is to be executed on a particular
 * Train Arrival using a Docker Client.
 *
 * @author Lukas Zimmermann
 * @since 0.0.3
 *
 */
object PrintSummary : DockerArrivalExecution<String> {

    override val command: ArrivalCommand<String> = PrintSummary

    override fun execute(interf: ITrainArrival<IDockerClient>, client: IDockerClient, info: StationInfo): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun execute(interf: DockerRegistryTrainArrival, client: IDockerClient, info: StationInfo) =

        client.pullAndRun(
                DockerRepositoryName()
                info.commandLine.plus("print_summary")).stdout
}
