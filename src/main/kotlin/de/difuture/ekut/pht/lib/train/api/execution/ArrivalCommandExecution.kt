package de.difuture.ekut.pht.lib.train.api.execution

import de.difuture.ekut.pht.lib.runtime.RuntimeClient
import de.difuture.ekut.pht.lib.runtime.docker.DockerClient
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.command.TrainCommand
import de.difuture.ekut.pht.lib.train.api.execution.docker.DockerOutputSupplier
import de.difuture.ekut.pht.lib.train.api.interf.TrainInterface
import de.difuture.ekut.pht.lib.train.api.interf.arrival.TrainArrival
import java.util.function.Supplier

/**
 * The [CommandExecution] for Train Arrivals.
 *
 * @param A The return type of the Arrival Command that is chained by this [ArrivalCommandExecution]
 * @param B The [TrainInterface] for which this command execution is defined
 * @param C The [RuntimeClient] that is required for this execution
 *
 */
interface ArrivalCommandExecution<A, B : TrainArrival, C : RuntimeClient, D : Supplier<A>> : CommandExecution<A> {

    /**
     * Executes the trainCommand for a given object with the Train Interface using
     * a particular Runtime client.
     *
     * @param interf The object implementing the [TrainInterface]
     * @param client The [RuntimeClient] for which the CommandExecution should be performed
     * @param info Additional Information that the station needs to provide at runtime
     * @return The value that [TrainCommand] is supposed to return.
     */
    fun execArrival(interf: B, client: C, info: StationInfo): D
}

/**
 * Specialization of the [ArrivalCommandExecution] for [DockerClient].
 */
typealias DockerArrivalExecution<A, B> = ArrivalCommandExecution<A, B, DockerClient, DockerOutputSupplier<A>>