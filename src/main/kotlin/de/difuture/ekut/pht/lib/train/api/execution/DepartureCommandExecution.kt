package de.difuture.ekut.pht.lib.train.api.execution

import de.difuture.ekut.pht.lib.runtime.RuntimeClient
import de.difuture.ekut.pht.lib.runtime.docker.DockerRuntimeClient
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.command.TrainCommand
import de.difuture.ekut.pht.lib.train.api.execution.docker.DockerOutputSupplier
import de.difuture.ekut.pht.lib.train.api.interf.TrainInterface
import de.difuture.ekut.pht.lib.train.api.interf.departure.TrainDeparture
import java.util.function.Supplier

/**
 * The [CommandExecution] for Train Departures.
 *
 * @param A The return type of the Arrival Command that is chained by this [ArrivalCommandExecution]
 * @param B The [TrainInterface] for which this command execution is defined
 * @param C The [RuntimeClient] that is required for this execution
 *
 */
interface DepartureCommandExecution<A, B : TrainDeparture<C>, C : RuntimeClient, D : Supplier<A>> : CommandExecution<A> {

    /**
     * Executes the trainCommand for a given object with the Train Interface using
     * a particular Runtime client.
     *
     * @param interf The object implementing the [TrainInterface]
     * @param info Additional Information that the station needs to provide at runtime
     * @return The value that [TrainCommand] is supposed to return.
     */
    fun execDeparture(interf: B, info: StationInfo): D
}

/**
 * The specialization of [DepartureCommandExecution] for [DockerRuntimeClient]
 */
typealias DockerDepartureExecution<A, B> = DepartureCommandExecution<A, B, DockerRuntimeClient, DockerOutputSupplier<A>>
