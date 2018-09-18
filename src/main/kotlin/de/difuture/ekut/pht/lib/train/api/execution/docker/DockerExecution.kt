@file:JvmName("DockerExecution")

package de.difuture.ekut.pht.lib.train.api.execution.docker

import de.difuture.ekut.pht.lib.train.api.command.PrintSummary

private val commands = mapOf(
        "check_requirements" to CheckRequirements,
        "run_algorithm" to RunAlgorithm,
        "print_summary" to PrintSummary
)

fun get(execution: String) = commands[execution]
