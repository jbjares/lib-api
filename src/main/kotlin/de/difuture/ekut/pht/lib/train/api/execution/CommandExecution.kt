package de.difuture.ekut.pht.lib.train.api.execution

import de.difuture.ekut.pht.lib.runtime.RuntimeClient
import de.difuture.ekut.pht.lib.train.api.command.TrainCommand

/**
 * This interface chains a [TrainCommand] to a particular [RuntimeClient] by defining
 * how the [RuntimeClient] can realize the trainCommand.
 *
 * @param A The Return type of the command that is selected by this [CommandExecution]
 *
 * @author Lukas Zimmermann
 * @see [TrainCommand]
 * @see [RuntimeClient]
 * @since 0.0.3
 *
 */
interface CommandExecution<A> {

    /**
     * The Train Command for this [CommandExecution]
     */
    val command: TrainCommand<A>
}
