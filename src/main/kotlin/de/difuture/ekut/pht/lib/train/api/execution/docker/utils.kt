package de.difuture.ekut.pht.lib.train.api.execution.docker

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import de.difuture.ekut.pht.lib.data.DockerContainerOutput
import de.difuture.ekut.pht.lib.runtime.docker.DockerRuntimeClient
import de.difuture.ekut.pht.lib.train.api.TrainCommand
import de.difuture.ekut.pht.lib.train.station.StationInfo
import de.difuture.ekut.pht.lib.train.api.output.TrainResponse
import de.difuture.ekut.pht.lib.train.api.interf.arrival.DockerRegistryTrainArrival
import de.difuture.ekut.pht.lib.train.api.interf.departure.DockerRegistryTrainDeparture
import de.difuture.ekut.pht.lib.train.api.output.TrainOutput
import java.lang.Exception

/**
 * Tries to read the [TrainResponse] from a [DockerContainerOutput] and returns
 * an appropriate [TrainOutput.DockerTrainOutput]
 *
 * @param output The [DockerContainerOutput] from which the [TrainResponse] should be read from
 * @return The [TrainOutput.DockerTrainOutput] generated from the input [DockerContainerOutput]
 */
private inline fun <reified T : TrainResponse> toTrainOutput(
    output: DockerContainerOutput
): TrainOutput.DockerTrainOutput<T> {

    // Tries to read the TrainResponse from the Container Output. Any Exception will result
    // in a null response. The error String will be the message of the thrown exception
    val (response: T?, error: String) = try {

        Pair<T, String>(jacksonObjectMapper().readValue(output.stdout), "")
    } catch (e: Exception) {

        Pair(null, e.message.orEmpty())
    }
    return TrainOutput.DockerTrainOutput(response, error, output)
}

/**
 * Executes a [TrainCommand] on a [DockerRegistryTrainArrival] with the provided [DockerRuntimeClient] with the
 * given [StationInfo]
 *
 * @param
 *
 */
internal inline fun <reified T : TrainResponse> execute(
    command: TrainCommand,
    interf: DockerRegistryTrainArrival,
    client: DockerRuntimeClient,
    info: StationInfo,
    rm: Boolean = true
): TrainOutput.DockerTrainOutput<T> {

    // First, use the Docker Client to pull the Docker image from the Docker Registry
    val imageId = client.pull(interf.repoName, interf.dockerTag, "${interf.host}:${interf.port}")

    // Second, use the client to execute generate the Docker Container Output
    val containerOutput = client.run(imageId, command.resolveWith(info), rm = rm)

    // Extend the DockerContainer Output to a Train Output (by reading the stdout of the container)
    return toTrainOutput(containerOutput)
}

/**
 * Executes the DockerRegistryTrainDeparture
 *
 */
internal inline fun <reified T : TrainResponse> execute(
    command: TrainCommand,
    interf: DockerRegistryTrainDeparture,
    info: StationInfo
): TrainOutput.DockerTrainOutput<T> {

    // Generate the DockerContainerOutput by using the client on the departure interface
    val containerOutput = interf.client.run(interf.imageId, command.resolveWith(info), rm = true)

    return toTrainOutput(containerOutput)
}
