package de.difuture.ekut.pht.lib.train.api.execution.docker

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import de.difuture.ekut.pht.lib.data.DockerContainerOutput
import de.difuture.ekut.pht.lib.runtime.docker.DockerRuntimeClient
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.command.ArrivalCommand
import de.difuture.ekut.pht.lib.train.api.command.DepartureCommand
import de.difuture.ekut.pht.lib.train.api.interf.arrival.DockerRegistryTrainArrival
import de.difuture.ekut.pht.lib.train.api.interf.departure.DockerRegistryTrainDeparture

/**
 * The default extractor just tries to to read the response model class from the
 * standard output of the [DockerContainerOutput]
 *
 * @param output The [DockerContainerOutput] from which the response should be generated
 * @return The read object [T]
 */
private inline fun <reified T : Any> defaultExtractor(output: DockerContainerOutput): T =
    jacksonObjectMapper().readValue(output.stdout)

/**
 * Executes an arrival command on the DockerRegistryTrainArrival by pulling the image first
 *
 * @param interf The [DockerRegistryTrainArrival] on which the command should be executed
 * @param command The command to be executed
 * @param client The [DockerRuntimeClient] that will be used to perform the command
 * @param info The Station context of the command to be executed
 * @param f The extractor to be used
 */
internal inline fun <reified T : Any> execute(
    interf: DockerRegistryTrainArrival,
    command: ArrivalCommand<*>,
    client: DockerRuntimeClient,
    info: StationInfo,
    noinline f: (DockerContainerOutput) -> T = ::defaultExtractor
) =
    DockerOutputSupplier(
            client.run(
                    client.pull(interf.repoName, interf.dockerTag, "${interf.host}:${interf.port}"),
                    info.commandLine.plus(command.name),
                    rm = true),
            extractor = f
    )

/**
 * Executes the DockerRegistryTrainDeparture
 *
 */
internal inline fun <reified T : Any> execute(
    interf: DockerRegistryTrainDeparture,
    command: DepartureCommand<*>,
    info: StationInfo,
    noinline f: (DockerContainerOutput) -> T = ::defaultExtractor
) =
        DockerOutputSupplier(
                interf.client.run(
                        interf.imageId,
                        info.commandLine.plus(command.name),
                        rm = true),
                extractor = f
        )
