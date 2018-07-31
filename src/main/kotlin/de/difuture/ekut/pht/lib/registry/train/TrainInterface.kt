package de.difuture.ekut.pht.lib.registry.train

import de.difuture.ekut.pht.lib.registry.train.tag.TrainTag

interface TrainInterface {

    val trainId: TrainId
    val trainTag: TrainTag
}
