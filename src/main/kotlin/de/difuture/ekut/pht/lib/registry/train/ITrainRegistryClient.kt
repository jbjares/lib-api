package de.difuture.ekut.pht.lib.registry.train

import de.difuture.ekut.pht.lib.registry.train.departure.AbstractTrainDeparture
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
    fun listTrainDepartures() : List<AbstractTrainDeparture<A>>

    /**
     * Lists all the train Departures for a certain train Tag.
     */
    fun listTrainDepartures(tag : TrainTag) : List<AbstractTrainDeparture<A>>
}
