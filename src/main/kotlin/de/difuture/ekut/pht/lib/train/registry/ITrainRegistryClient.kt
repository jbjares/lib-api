package de.difuture.ekut.pht.lib.train.registry

import de.difuture.ekut.pht.lib.train.api.interf.arrival.ITrainArrival
import de.difuture.ekut.pht.lib.train.api.interf.departure.ITrainDeparture
import de.difuture.ekut.pht.lib.train.id.ITrainId
import de.difuture.ekut.pht.lib.train.tag.ITrainTag
import de.difuture.ekut.pht.lib.runtime.IRuntimeClient

/**
 * Represents the api of a client to the Train Registry.
 *
 * The train registry is directly dependent on a selected runtime client, as
 * the train registry provides a way to interact with [ITrainArrival].
 *
 * @param A The runtime client required to use [ITrainArrival] instances retrieved from
 * the implementer of the Train Registry.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
interface ITrainRegistryClient<A : IRuntimeClient> {

    /**
     * Lists all [ITrainArrival] instances of this Train Registry.
     *
     * *Contract:* This method should throw an exception if communication with the remote
     * train registry fails.
     *
     * @return List of all [ITrainArrival] instances that this Train Registry contains.
     *
     */
    fun listTrainArrivals(): List<ITrainArrival>

    /**
     * List all [ITrainArrival] instances that have a certain [ITrainTag].
     *
     * *Contract:* This method should throw an exception if communication with the remote
     * train repository fails.
     *
     * @param tag The [ITrainTag] instance that is shared by the returned [ITrainArrival].
     *
     * @return List of [ITrainArrival] instances in the Train Registry that share the same [ITrainTag].
     */
    fun listTrainArrivals(tag: ITrainTag): List<ITrainArrival>

    /**
     *  List TrainArrivals by giving the [ITrainId]
     *
     * *Contract:* This method should throw an exception if communication with the remote
     * train repository fails.
     *
     * @param id The [ITrainId] that should be queries
     *
     * @return List of [ITrainArrival] that all share the same [ITrainId]
     *
     */
    fun listTrainArrivals(id: ITrainId): List<ITrainArrival>

    /**
     * Get [ITrainArrival] by specifying [ITrainId] and [ITrainTag].
     *
     * *Contract:* If the specified [ITrainArrival] does not exist, this method should
     * return null. If the communication with the remote Train Registry fails, this method
     * should fail by throwing an exception.
     *
     * @param id The [ITrainId] of the returned [ITrainArrival].
     * @param tag The [ITrainTag] of the returned [ITrainArrival].
     *
     * @return The [ITrainArrival] with the specified [ITrainId] and [ITrainTag], or null if no such
     * [ITrainArrival] exists.
     *
     */
    fun getTrainArrival(id: ITrainId, tag: ITrainTag): ITrainArrival?

    /**
     * Checks whether the [ITrainArrival]] exists in the Train Registry that is targeted by this registry.
     *
     * @param id The [ITrainId] that should be checked
     * @param tag The [ITrainTag] that should be checked
     *
     * @return Whether the [ITrainId] with the [ITrainTag] exists in the registry.
     *
     */
    fun hasTrainArrival(id: ITrainId, tag: ITrainTag): Boolean

    /**
     * Pushes the specified [ITrainDeparture] to this Train Registry.
     *
     * *Contract:* The method should throw an exception if the [ITrainDeparture] cannot be pushed to the
     * train registry successfully. Pushing to a [ITrainDeparture] that already exits and would not
     * be updated via this push is never a failure.
     *
     * @param departure The [ITrainDeparture] instance that should be pushed to this Train Registry.
     *
     */
    fun push(departure: ITrainDeparture<A>)
}
