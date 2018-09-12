package de.difuture.ekut.pht.lib.train.api.execution

import de.difuture.ekut.pht.lib.runtime.IRuntimeClient
import de.difuture.ekut.pht.lib.runtime.docker.IDockerClient
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.command.TrainCommand
import de.difuture.ekut.pht.lib.train.api.interf.ITrainInterface
import de.difuture.ekut.pht.lib.train.api.interf.arrival.ITrainArrival

/**
 * The [CommandExecution] for Train Arrivals.
 *
 * @param A The return type of the Arrival Command that is chained by this [ArrivalCommandExecution]
 * @param B The [ITrainInterface] for which this command execution is defined
 * @param C The [IRuntimeClient] that is required for this execution
 *
 */
interface ArrivalCommandExecution<A, B : ITrainArrival, C : IRuntimeClient> : CommandExecution<A> {

    /**
     * Executes the trainCommand for a given object with the Train Interface using
     * a particular Runtime client.
     *
     * @param interf The object implementing the [ITrainInterface]
     * @param client The [IRuntimeClient] for which the CommandExecution should be performed
     * @param info Additional Information that the station needs to provide at runtime
     * @return The value that [TrainCommand] is supposed to return.
     */
    fun execArrival(interf: B, client: C, info: StationInfo): A

    /**
     * Executes the trainCommand for a given object with the Train Interface using
     * a particular Runtime client.
     *
     * @param interf The object implementing the [ITrainInterface]
     * @param client The [IRuntimeClient] for which the CommandExecution should be performed
     * @param info Additional Information that the station needs to provide at runtime
     * @return The value that [TrainCommand] is supposed to return.
     */
    fun <T> execArrivalWrapped(interf: B, client: C, info: StationInfo): Pair<A, T>
}

/**
 * Specialization of the [ArrivalCommandExecution] for [IDockerClient].
 */
typealias DockerArrivalExecution<A, B> = ArrivalCommandExecution<A, B, IDockerClient>
