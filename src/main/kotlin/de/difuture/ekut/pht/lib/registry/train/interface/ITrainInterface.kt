package de.difuture.ekut.pht.lib.registry.train.`interface`

import de.difuture.ekut.pht.lib.registry.train.id.ITrainId
import de.difuture.ekut.pht.lib.registry.train.tag.ITrainTag
import de.difuture.ekut.pht.lib.runtime.IRuntimeClient


/**
 * Super interface for [ITrainArrival] and [ITrainDeparture]
 *
 * This interface encompasses all operations that are common to the [ITrainArrival] and
 * [ITrainDeparture].
 *
 * @param A The [IRuntimeClient] type to be used for communicating with this Train interface.
 *
 * @author Lukas Zimmermann
 * @see ITrainArrival
 * @see ITrainDeparture
 * @since 0.0.1
 *
 */
interface ITrainInterface<A : IRuntimeClient> {

    /**
     * The [ITrainId] of the Train to which this object refers.
     */
    val trainId: ITrainId

    /**
     * The [ITrainTag] of the Train to which this object refers.
     */
    val trainTag: ITrainTag

    /**
     * Uses the Train API to print the summary of the train.
     *
     * @param client The [IRuntimeClient] that should be used for accessing the train.
     * @return [String] value representing the summary of the Train.
     *
     */
    fun printSummary(client: A) : String
}
