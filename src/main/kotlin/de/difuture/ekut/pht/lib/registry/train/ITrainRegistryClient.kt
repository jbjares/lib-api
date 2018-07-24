package de.difuture.ekut.pht.lib.registry.train

import de.difuture.ekut.pht.lib.registry.train.departure.ITrainDeparture
import de.difuture.ekut.pht.lib.registry.train.departure.tag.TrainTag

interface ITrainRegistryClient {

    /**
     * Lists all train departures with a certain Tag.
     * Conceptionally, the Train Departures are backed by object on which this method was called.
     */
    fun listTrainDepartures(tag : TrainTag) : List<ITrainDeparture>
}
