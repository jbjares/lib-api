package de.difuture.ekut.pht.lib.train.api.interf

import de.difuture.ekut.pht.lib.train.id.ITrainId
import de.difuture.ekut.pht.lib.runtime.IRuntimeClient
import de.difuture.ekut.pht.lib.train.tag.ITrainTag

/**
 * Super api for [ITrainArrival] and [ITrainDeparture]
 *
 * This api encompasses all operations that are common to the [ITrainArrival] and
 * [ITrainDeparture].
 *
 * @param A The [IRuntimeClient] type to be used for communicating with this Train api.
 *
 * @author Lukas Zimmermann
 * @see ITrainArrival
 * @see ITrainDeparture
 * @since 0.0.1
 *
 */
interface ITrainInterface {

    /**
     * The [ITrainId] of the Train to which this object refers.
     */
    val trainId: ITrainId

    /**
     * The [ITrainTag]
     *
     */
    val trainTag: ITrainTag
}
