package de.difuture.ekut.pht.lib.train.api.interf

import de.difuture.ekut.pht.lib.train.api.data.TrainId
import de.difuture.ekut.pht.lib.train.api.data.TrainTag

/**
 * Super api for [TrainArrival] and [TrainDeparture]
 *
 * This api encompasses all operations that are common to the [TrainArrival] and
 * [TrainDeparture].
 **
 * @author Lukas Zimmermann
 * @see TrainArrival
 * @see TrainDeparture
 * @since 0.0.1
 *
 */
interface TrainInterface {

    /**
     * The [TrainId] of the Train to which this object refers.
     */
    val trainId: TrainId

    /**
     * The [TrainTag]
     *
     */
    val trainTag: TrainTag
}
