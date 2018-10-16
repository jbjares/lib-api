package de.difuture.ekut.pht.lib.train.api.execution

import de.difuture.ekut.pht.lib.runtime.RuntimeClient
import de.difuture.ekut.pht.lib.runtime.docker.DockerRuntimeClient
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.interf.TrainInterface
import de.difuture.ekut.pht.lib.train.api.interf.departure.TrainDeparture
import de.difuture.ekut.pht.lib.train.api.output.TrainOutput

/**
 * The [CommandExecution] for Train Departures.
 *
 * @param A The return type of the Arrival TrainCommand that is chained by this [ArrivalExecution]
 * @param B The [TrainInterface] for which this command execution is defined
 * @param C The [RuntimeClient] that is required for this execution
 *
 */
interface DepartureExecution<A : TrainDeparture<B>, B : RuntimeClient, C : TrainOutput> : TrainExecution {

    /**
     * Executes the trainCommand for a given object with the Train Interface using
     * a particular Runtime client.
     *
     * @param interf The object implementing the [TrainInterface]
     * @param info Additional Information that the payload needs to provide at runtime
     * @return The value that [TrainCommand] is supposed to return.
     */
    fun execDeparture(interf: A, info: StationInfo): C
}
/**
 * The specialization of [DepartureExecution] for [DockerRuntimeClient]
 */
typealias DockerDepartureExecution<A> =
        DepartureExecution<A, DockerRuntimeClient, TrainOutput.DockerTrainOutput>
