package de.difuture.ekut.pht.lib.registry.train.departure

import de.difuture.ekut.pht.lib.registry.train.departure.tag.TrainTag

data class TrainDeparture(

        val trainId : TrainId,
        val trainTag: TrainTag
)
