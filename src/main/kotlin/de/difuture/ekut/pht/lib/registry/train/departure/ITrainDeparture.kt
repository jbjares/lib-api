package de.difuture.ekut.pht.lib.registry.train.departure

import de.difuture.ekut.pht.lib.registry.train.departure.tag.TrainTag
import de.difuture.ekut.pht.lib.runtime.RuntimeClient

/**
 * Interface for Train Departures.
 *
 */
interface ITrainDeparture<A : RuntimeClient>{

    val trainId: TrainId
    val trainTag: TrainTag

    fun printSummary(client: A) : String
}
