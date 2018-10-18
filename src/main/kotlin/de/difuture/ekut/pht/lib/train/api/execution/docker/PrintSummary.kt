package de.difuture.ekut.pht.lib.train.api.execution.docker

import de.difuture.ekut.pht.lib.runtime.docker.DockerRuntimeClient
import de.difuture.ekut.pht.lib.train.station.StationInfo
import de.difuture.ekut.pht.lib.train.api.TrainCommand
import de.difuture.ekut.pht.lib.train.api.interf.arrival.DockerRegistryTrainArrival
import de.difuture.ekut.pht.lib.train.api.interf.departure.DockerRegistryTrainDeparture
import de.difuture.ekut.pht.lib.train.api.output.TrainResponse

/**
 * Specifies how the Print Summary trainCommand is to be executed on a particular
 * Train Arrival using a Docker Client.
 *
 * @author Lukas Zimmermann
 * @since 0.0.3
 *
 */
object PrintSummary : DockerArrivalExecution<TrainResponse.PrintSummaryResponse>, DockerDepartureExecution<TrainResponse.PrintSummaryResponse> {

    override val command = TrainCommand.PRINT_SUMMARY

    /**
     * Executes the "print_summary" train trainCommand for b
     * using a specific [DockerRuntimeClient]
     *
     */
    override fun execArrival(interf: DockerRegistryTrainArrival, client: DockerRuntimeClient, info: StationInfo) =
            execute<TrainResponse.PrintSummaryResponse>(this.command, interf, client, info)

    /**
     * Executes the "print_summary" trainCommand on the [DockerRegistryTrainDeparture].
     *
     */
    override fun execDeparture(interf: DockerRegistryTrainDeparture, info: StationInfo) =
            execute<TrainResponse.PrintSummaryResponse>(this.command, interf, info)
}
