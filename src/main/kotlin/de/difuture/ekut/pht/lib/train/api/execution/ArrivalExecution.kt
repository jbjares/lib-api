package de.difuture.ekut.pht.lib.train.api.execution

import de.difuture.ekut.pht.lib.runtime.RuntimeClient
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.interf.TrainInterface
import de.difuture.ekut.pht.lib.train.api.interf.arrival.TrainArrival
import de.difuture.ekut.pht.lib.train.api.output.TrainOutput
import de.difuture.ekut.pht.lib.train.api.output.TrainResponse

/**
 * The [TrainExecution] for Train Arrivals.
 *
 * @param A The return type of the Arrival TrainCommand that is chained by this [ArrivalExecution]
 * @param B The [TrainInterface] for which this command execution is defined
 * @param C The [RuntimeClient] that is required for this execution
 *
 */
interface ArrivalExecution<A : TrainArrival, B : RuntimeClient, C : TrainOutput<D>, D : TrainResponse> : TrainExecution {

    /**
     * Executes the trainCommand for a given object with the Train Interface using
     * a particular Runtime client.
     *
     * @param interf The object implementing the [TrainInterface]
     * @param client The [RuntimeClient] for which the CommandExecution should be performed
     * @param info Additional Information that the payload needs to provide at runtime
     * @return The value that [TrainCommand] is supposed to return.
     */
    fun execArrival(interf: A, client: B, info: StationInfo): C
}
