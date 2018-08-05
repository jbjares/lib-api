package de.difuture.ekut.pht.lib.registry.train.arrival

import de.difuture.ekut.pht.lib.registry.train.TrainInterface
import de.difuture.ekut.pht.lib.runtime.RuntimeClient

/**
 * Interface for Train Arrivals.
 *
 */
interface ITrainArrival<A : RuntimeClient> : TrainInterface<A> {

    fun checkRequirements(client : A, timeout: Int) : Boolean
}
