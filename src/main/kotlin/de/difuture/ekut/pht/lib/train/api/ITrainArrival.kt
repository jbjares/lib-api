package de.difuture.ekut.pht.lib.train.api

import de.difuture.ekut.pht.lib.runtime.docker.IDockerClient
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
    fun checkRequirements(client : A, info: RunInfo) : Boolean

    /**
     *  Runs the algorithm and returns the corresponding [ITrainDeparture]
     *
     *  @param client The runtime client which should be used to compute the [ITrainDeparture]
     *  @return The [ITrainDeparture] for this execution of the algorithm.
     *
     */
    fun runAlgorithm(client: A, info: RunInfo) : ITrainDeparture<A>

    /**
     * Uses the Train API to print the summary of the train.
     *
     * @param client The [IRuntimeClient] that should be used for accessing the train.
     * @return [String] value representing the summary of the Train.
     *
     */
    fun printSummary(client: A, info: RunInfo) : String
}

typealias IDockerTrainArrival = ITrainArrival<IDockerClient>
