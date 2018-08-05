package de.difuture.ekut.pht.lib.registry.train

import de.difuture.ekut.pht.lib.registry.train.tag.TrainTag
import de.difuture.ekut.pht.lib.runtime.RuntimeClient


/**
 * Super interface for both TrainArrival and TrainDeparture
 *
 * @author Lukas Zimmermann
 *
 */
interface TrainInterface<A : RuntimeClient> {

    val trainId: TrainId
    val trainTag: TrainTag

    fun printSummary(client: A, timeout : Int) : String
}
