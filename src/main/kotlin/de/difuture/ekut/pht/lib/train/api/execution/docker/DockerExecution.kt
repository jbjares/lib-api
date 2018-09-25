@file:JvmName("DockerExecution")

package de.difuture.ekut.pht.lib.train.api.execution.docker

private val commands = mapOf(
        de.difuture.ekut.pht.lib.train.api.command.CheckRequirements.name to CheckRequirements,
        de.difuture.ekut.pht.lib.train.api.command.RunAlgorithm.name to RunAlgorithm,
        de.difuture.ekut.pht.lib.train.api.command.PrintSummary.name to PrintSummary,
        de.difuture.ekut.pht.lib.train.api.command.ListRequirements.name to ListRequirements
)
fun getDockerExecution(execution: String) = commands[execution]
