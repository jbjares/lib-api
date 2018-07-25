package de.difuture.ekut.pht.lib.registry.train

import de.difuture.ekut.pht.lib.registry.train.departure.AbstractTrainDeparture

interface ITrainRegistryClient {

    fun listTrainDepartures() : List<AbstractTrainDeparture>
}
