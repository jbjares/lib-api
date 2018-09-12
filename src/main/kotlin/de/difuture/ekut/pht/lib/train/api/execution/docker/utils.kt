package de.difuture.ekut.pht.lib.train.api.execution.docker

import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.command.TrainCommand

/**
 * Joins the specified [StationInfo] with the train command represented as [String] to
 * a command line call
 *
 * @param info The [StationInfo] to be used for constructing the command line call
 * @param command The [TrainCommand] to be used to join to a command line.
 * @return The [List] of command line elements.
 */
internal fun commandLine(info: StationInfo, command: TrainCommand<*>) = info.commandLine.plus(command.name)
