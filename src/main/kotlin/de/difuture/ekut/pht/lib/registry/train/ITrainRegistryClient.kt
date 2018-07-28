package de.difuture.ekut.pht.lib.registry.train

import de.difuture.ekut.pht.lib.registry.train.departure.ITrainDeparture
import de.difuture.ekut.pht.lib.registry.train.departure.tag.TrainTag
import de.difuture.ekut.pht.lib.runtime.RuntimeClient


/**
 * General Interface of a Train Registry
 *
 * @author Lukas Zimmermann
 *
 */
interface ITrainRegistryClient<A : RuntimeClient> {

    /**
     * Lists all Train Departures
     */
    fun listTrainDepartures() : List<ITrainDeparture<A>>

    /**
     * Lists all the train Departures for a certain train Tag.
     */
    fun listTrainDepartures(tag : TrainTag) : List<ITrainDeparture<A>>


}
