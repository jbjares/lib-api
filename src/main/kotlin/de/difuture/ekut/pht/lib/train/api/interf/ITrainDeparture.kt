package de.difuture.ekut.pht.lib.train.api.interf

import de.difuture.ekut.pht.lib.runtime.docker.IDockerClient
import de.difuture.ekut.pht.lib.runtime.IRuntimeClient

/**
 * The [ITrainDeparture] for this Train instance.
 *
 */
interface ITrainDeparture<A : IRuntimeClient> : ITrainInterface<A> {

    /**
     * The runtime client that is associated with this [ITrainDeparture].
     */
    val client: A

    /**
     * Uses the Train API to print the summary of the train.
     *
     * *Contract:* If the [isValid] method of this [ITrainDeparture] returns false,
     * this method must throw [IllegalStateException].
     *
     * @return [String] value representing the summary of the Train.
     *
     */
    fun printSummary(): String

    /**
     * Checks whether this [ITrainDeparture] is valid, i.e. whether the result of running the algorithm can
     * be accessed.
     *
     * If this method returns false, the corresponding [ITrainArrival] must again run the algorithm.
     *
     * @return Whether this [ITrainDeparture] is still valid.
     *
     */
    fun isValid(): Boolean
}

typealias IDockerTrainDeparture = ITrainDeparture<IDockerClient>
