package de.difuture.ekut.pht.lib.train.api.interf.departure

import de.difuture.ekut.pht.lib.runtime.IRuntimeClient
import de.difuture.ekut.pht.lib.train.api.interf.ITrainInterface


/**
 * The [ITrainDeparture] for this Train instance.
 *
 * @author Lukas Zimmermann
 * @since 0.0.3
 *
 */
interface ITrainDeparture<A : IRuntimeClient> : ITrainInterface {

    /**
     * The runtime client that is associated with this [ITrainDeparture].
     */
    val client: A

    /**
     * Checks whether the [ITrainDeparture] is valid in the sense that the [client] can
     * still access all necessary properties of the [ITrainDeparture]
     *
     * @return Whether `this` [ITrainDeparture] is still valid
     */
    fun isValid(): Boolean
}
