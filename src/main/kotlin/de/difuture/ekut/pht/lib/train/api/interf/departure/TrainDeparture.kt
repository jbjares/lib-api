package de.difuture.ekut.pht.lib.train.api.interf.departure

import de.difuture.ekut.pht.lib.runtime.RuntimeClient
import de.difuture.ekut.pht.lib.train.api.interf.TrainInterface

/**
 * The [TrainDeparture] for this Train instance.
 *
 * @author Lukas Zimmermann
 * @since 0.0.3
 *
 */
interface TrainDeparture<A : RuntimeClient> : TrainInterface {

    /**
     * The runtime client that is associated with this [TrainDeparture].
     */
    val client: A

    /**
     * Checks whether the [TrainDeparture] is valid in the sense that the [client] can
     * still access all necessary properties of the [TrainDeparture]
     *
     * @return Whether `this` [TrainDeparture] is still valid
     */
    fun isValid(): Boolean
}
