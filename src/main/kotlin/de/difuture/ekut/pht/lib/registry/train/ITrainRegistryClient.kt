package de.difuture.ekut.pht.lib.registry.train

import de.difuture.ekut.pht.lib.registry.train.arrival.ITrainArrival
import de.difuture.ekut.pht.lib.registry.train.arrival.tag.TrainTag
import de.difuture.ekut.pht.lib.runtime.RuntimeClient


/**
 * General Interface of a Train Registry
 *
 * @author Lukas Zimmermann
 *
 */
interface ITrainRegistryClient<A : RuntimeClient> {

    /**
     * Lists all Train Arrivals
     */
    fun listTrainArrivals() : List<ITrainArrival<A>>

    /**
     * Lists all the train Arrivals for a certain train Tag.
     */
    fun listTrainArrivals(tag : TrainTag) : List<ITrainArrival<A>>
}
