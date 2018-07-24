package de.difuture.ekut.pht.lib.registry.train

import de.difuture.ekut.pht.lib.registry.train.departure.TrainDeparture
import de.difuture.ekut.pht.lib.registry.train.departure.tag.TrainTag

interface ITrainRegistry {

    /**
     * Lists all train departures with a certain Tag
     */
    fun listTrainDepartures(tag : TrainTag) : List<TrainDeparture>
}
