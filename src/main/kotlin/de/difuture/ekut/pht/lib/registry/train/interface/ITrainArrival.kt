package de.difuture.ekut.pht.lib.registry.train.`interface`

import de.difuture.ekut.pht.lib.runtime.IDockerClient
import de.difuture.ekut.pht.lib.runtime.IRuntimeClient

/**
 * Interface for Train Arrivals.
 *
 * @author Lukas Zimmmermann
 * @since 0.0.1
 *
 */
interface ITrainArrival<A : IRuntimeClient> : ITrainInterface<A> {

    /**
     * Uses the [IRuntimeClient] to check whether all requirements are met such that the Train
     * can run the algorithm.
     *
     * @param client The [IRuntimeClient] that is used for checking the requirements of the Train.
     * @return Whether all requirements are met such that the Train can run its algorithm.
     *
     */
    fun checkRequirements(client : A) : Boolean

    /**
     *  Runs the algorithm and returns the corresponding [ITrainDeparture]
     *
     *  @param client The runtime client which should be used to compute the [ITrainDeparture]
     *  @return The [ITrainDeparture] for this execution of the algorithm.
     *
     */
    fun runAlgorithm(client: A) : ITrainDeparture<A>
}

typealias IDockerTrainArrival = ITrainArrival<IDockerClient>
