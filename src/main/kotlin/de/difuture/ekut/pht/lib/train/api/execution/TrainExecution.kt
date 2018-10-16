package de.difuture.ekut.pht.lib.train.api.execution

import de.difuture.ekut.pht.lib.train.api.TrainCommand

/**
 * General interface for a Train Execution. It needs to specify as a property what command
 * it refers to
 *
 * @author Lukas Zimmermann
 */
interface TrainExecution {

    val command: TrainCommand
}
