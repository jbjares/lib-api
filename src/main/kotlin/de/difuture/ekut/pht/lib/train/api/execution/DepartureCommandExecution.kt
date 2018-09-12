package de.difuture.ekut.pht.lib.train.api.execution

import de.difuture.ekut.pht.lib.runtime.IRuntimeClient
import de.difuture.ekut.pht.lib.runtime.docker.IDockerClient
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.command.TrainCommand
import de.difuture.ekut.pht.lib.train.api.interf.ITrainInterface
import de.difuture.ekut.pht.lib.train.api.interf.departure.ITrainDeparture

/**
 * The [CommandExecution] for Train Departures.
 *
 * @param A The return type of the Arrival Command that is chained by this [ArrivalCommandExecution]
 * @param B The [ITrainInterface] for which this command execution is defined
 * @param C The [IRuntimeClient] that is required for this execution
 *
 */
interface DepartureCommandExecution<A, B : ITrainDeparture<C>, C : IRuntimeClient> : CommandExecution<A> {

    /**
     * Executes the trainCommand for a given object with the Train Interface using
     * a particular Runtime client.
     *
     * @param interf The object implementing the [ITrainInterface]
     * @param info Additional Information that the station needs to provide at runtime
     * @return The value that [TrainCommand] is supposed to return.
     */
    fun execDeparture(interf: B, info: StationInfo): A

}

/**
 * The specialization of [DepartureCommandExecution] for [IDockerClient]
 */
typealias DockerDepartureExecution<A, B> = DepartureCommandExecution<A, B, IDockerClient>
