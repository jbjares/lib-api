package de.difuture.ekut.pht.lib.registry.train.arrival

import de.difuture.ekut.pht.lib.registry.train.ITrainInterface
import de.difuture.ekut.pht.lib.registry.train.departure.ITrainDeparture
import de.difuture.ekut.pht.lib.runtime.IDockerClient
import de.difuture.ekut.pht.lib.runtime.IRuntimeClient

/**
 * Interface for Train Arrivals.
 *
 */
interface ITrainArrival<A : IRuntimeClient> : ITrainInterface<A> {

    fun checkRequirements(client : A, timeout: Int) : Boolean

    fun runAlgorithm(client: A, timeout: Int) : ITrainDeparture<A>
}

typealias IDockerTrainArrival = ITrainArrival<IDockerClient>
