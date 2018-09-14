package de.difuture.ekut.pht.lib.train.registry

import de.difuture.ekut.pht.lib.train.api.interf.arrival.TrainArrival

/**
 * Represents the api of a client to the Train Registry.
 *
 * The train registry is directly dependent on a selected runtime client, as
 * the train registry provides a way to interact with [TrainArrival].
 *
 * @param A The runtime client required to use [TrainArrival] instances retrieved from
 * the implementer of the Train Registry.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
interface ITrainRegistryClient<out A : TrainArrival> {

    fun listTrainArrivals(predicate: (TrainArrival) -> Boolean = { true }): List<A>
}
