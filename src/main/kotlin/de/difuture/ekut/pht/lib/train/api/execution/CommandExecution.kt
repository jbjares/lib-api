package de.difuture.ekut.pht.lib.train.api.execution

import de.difuture.ekut.pht.lib.runtime.IRuntimeClient
import de.difuture.ekut.pht.lib.train.api.command.TrainCommand

/**
 * This interface chains a [TrainCommand] to a particular [IRuntimeClient] by defining
 * how the [IRuntimeClient] can realize the trainCommand.
 *
 * @param A The Return type of the command that is selected by this [CommandExecution]
 *
 * @author Lukas Zimmermann
 * @see [TrainCommand]
 * @see [IRuntimeClient]
 * @since 0.0.3
 *
 */
interface CommandExecution<A> {

    /**
     * The Train Command for this [CommandExecution]
     */
    val command: TrainCommand<A>
}
