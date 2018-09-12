package de.difuture.ekut.pht.lib.train.api.execution.docker

import de.difuture.ekut.pht.lib.runtime.docker.IDockerClient
import de.difuture.ekut.pht.lib.runtime.docker.pullAndRun
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.command.ArrivalCommand
import de.difuture.ekut.pht.lib.train.api.command.PrintSummary
import de.difuture.ekut.pht.lib.train.api.execution.DockerArrivalExecution
import de.difuture.ekut.pht.lib.train.api.execution.DockerDepartureExecution
import de.difuture.ekut.pht.lib.train.api.interf.arrival.DockerRegistryTrainArrival
import de.difuture.ekut.pht.lib.train.api.interf.departure.DockerRegistryTrainDeparture


/**
 * Specifies how the Print Summary trainCommand is to be executed on a particular
 * Train Arrival using a Docker Client.
 *
 * @author Lukas Zimmermann
 * @since 0.0.3
 *
 */
object PrintSummary
    : DockerArrivalExecution<String, DockerRegistryTrainArrival>,
      DockerDepartureExecution<String, DockerRegistryTrainDeparture> {

    override val command: ArrivalCommand<String> = PrintSummary

    /**
     * Executes the "print_summary" train trainCommand for b
     * using a specific [IDockerClient]
     *
     */
    override fun execArrival(interf: DockerRegistryTrainArrival, client: IDockerClient, info: StationInfo) =

            client.pullAndRun(
                    interf.name,
                    interf.tag,
                    commandLine(info, command),
                    true).stdout


    /**
     * Executes the "print_summary" trainCommand on the [DockerRegistryTrainDeparture].
     *
     */
    override fun execDeparture(interf: DockerRegistryTrainDeparture, info: StationInfo) =

            interf.client.run(
                    interf.imageId,
                    commandLine(info, command),
                    true).stdout
}
