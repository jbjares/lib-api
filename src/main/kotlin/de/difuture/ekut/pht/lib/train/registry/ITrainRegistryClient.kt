package de.difuture.ekut.pht.lib.train.registry

import de.difuture.ekut.pht.lib.train.TrainId
import de.difuture.ekut.pht.lib.train.TrainTag
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

    /**
     * Lists all the train arrivals that fulfill a certain criterion.
     *
     * @param predicate The predicate to be used for filtering the returned [TrainArrival] instances
     *
     * @return List of [TrainArrival] instances that satisfy the provided predicate
     */
    fun listTrainArrivals(predicate: (TrainArrival) -> Boolean = { true }): List<A>

    /**
     * Selects a particular [TrainArrival] given the [TrainId] and [TrainTag].
     *
     * @param trainId The [TrainId] to select
     * @param trainTag The [trainTag] to select.
     *
     * @return The selected [TrainArrival] or null if this particular TrainArrival does not exist.
     */
    fun getTrainArrival(trainId: TrainId, trainTag: TrainTag): A?
}
