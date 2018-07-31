package de.difuture.ekut.pht.lib.registry.train.arrival

import de.difuture.ekut.pht.lib.registry.train.arrival.tag.TrainTag
import de.difuture.ekut.pht.lib.runtime.RuntimeClient

/**
 * Interface for Train Arrivals.
 *
 */
interface ITrainArrival<A : RuntimeClient>{

    val trainId: TrainId
    val trainTag: TrainTag

    fun printSummary(client: A, timeout : Int) : String
}
