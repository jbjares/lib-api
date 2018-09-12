package de.difuture.ekut.pht.lib.train.api.interf.arrival

import de.difuture.ekut.pht.lib.train.api.interf.ITrainInterface


/**
 * Interface for Train Arrivals.
 *
 * @author Lukas Zimmmermann
 * @since 0.0.1
 *
 */
interface ITrainArrival : ITrainInterface {

//    fun <B> execWithClient(client: A, execution: ArrivalCommandExecution<B, A>) =
//
//        execution.execArrival(this, client)
//
//    /**
//     *  Runs the algorithm and returns the corresponding [ITrainDeparture]
//     *
//     *  @param client The runtime client which should be used to compute the [ITrainDeparture]
//     *  @return The [ITrainDeparture] for this execution of the algorithm.
//     *
//     */
//    fun runAlgorithm(client: A, info: StationInfo): ITrainDeparture<A>
}
