package de.difuture.ekut.pht.lib.train.api.execution

import de.difuture.ekut.pht.lib.runtime.IRuntimeClient
import de.difuture.ekut.pht.lib.train.api.interf.ITrainInterface
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.command.TrainCommand


/**
 * This interface chains a [TrainCommand] to a particular [IRuntimeClient] by defining
 * how the [IRuntimeClient] can realize the command.
 *
 * @author Lukas Zimmermann
 * @see [TrainCommand]
 * @see [IRuntimeClient]
 * @since 0.0.3
 *
 */
interface Execution<A, B : IRuntimeClient> {

    /**
     * The Train Command for this Execution
     *
     */
    val command: TrainCommand<A>

    /**
     * Executes the command for a given object with the Train Interface using
     * a particular Runtime client.
     *
     * @param interf The object implementing the [ITrainInterface]
     * @param client The [IRuntimeClient] for which the Execution should be performed
     * @param info Additional Information that the station needs to provide at runtime
     * @return The value that [TrainCommand] is supposed to return.
     */
    fun execute(interf: ITrainInterface, client: B, info: StationInfo): A
}
