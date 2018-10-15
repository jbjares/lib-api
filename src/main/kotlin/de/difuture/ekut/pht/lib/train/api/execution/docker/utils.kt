package de.difuture.ekut.pht.lib.train.api.execution.docker

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import de.difuture.ekut.pht.lib.data.DockerContainerOutput
import de.difuture.ekut.pht.lib.runtime.docker.DockerRuntimeClient
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.output.TrainResponse
import de.difuture.ekut.pht.lib.train.api.interf.arrival.DockerRegistryTrainArrival
import de.difuture.ekut.pht.lib.train.api.interf.departure.DockerRegistryTrainDeparture
import de.difuture.ekut.pht.lib.train.api.output.TrainOutput
import java.lang.Exception

/**
 * Tries to read [T] from the stdout of the [DockerContainerOutput]. If the container
 * misbehaves and does not produce the output in the required format, apply a default
 * extractor on the [DockerContainerOutput] to build T
 *
 * @param default The default extractor to apply if reading stdout of container fails
 * @return The read object [T]
 */
private inline fun <reified T : TrainResponse> toTrainOutput(output: DockerContainerOutput): TrainOutput.DockerTrainOutput {

    val (response: T?, error: String) = try {

        Pair<T, String>(jacksonObjectMapper().readValue(output.stdout), "")
    } catch (e: Exception) {

        Pair(null, e.message.orEmpty())
    }
    return TrainOutput.DockerTrainOutput(response, error, output)
}

/**
 * Executes an arrival command on the DockerRegistryTrainArrival by pulling the image first
 *
 * @param interf The [DockerRegistryTrainArrival] on which the command should be executed
 * @param command The command to be executed
 * @param client The [DockerRuntimeClient] that will be used to perform the command
 * @param info The Station context of the command to be executed
 * @param f The extractor to be used
 */
internal inline fun <reified T : TrainResponse> execute(
        interf: DockerRegistryTrainArrival,
        client: DockerRuntimeClient,
        info: StationInfo,
        commandName: String) : TrainOutput.DockerTrainOutput {

    // First, use the Docker Client to pull the Docker image from the Docker Registry
    val imageId = client.pull(interf.repoName, interf.dockerTag, "${interf.host}:${interf.port}")

    // First, use the client to execute generate the dockercontainer Output
    val containerOutput = client.run(imageId, info.commandLine.plus(commandName), rm = true)

    return toTrainOutput<T>(containerOutput)
}

/**
 * Executes the DockerRegistryTrainDeparture
 *
 */
internal inline fun <reified T : Any> execute(
    interf: DockerRegistryTrainDeparture,
    info: StationInfo,
    commandName: String
) : TrainOutput.DockerTrainOutput  {

    // Generate the DockerContainerOutput by using the client on the departure interface
    val containerOutput = interf.client.run(interf.imageId, info.commandLine.plus(command.name) )

}
        DockerOutputSupplier(
                interf.client.run(
                        interf.imageId,
                        info.commandLine.plus(command.name),
                        rm = true),
                extractor = f)
