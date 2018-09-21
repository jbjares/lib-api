package de.difuture.ekut.pht.lib.train.api.execution.docker

import de.difuture.ekut.pht.lib.data.DockerContainerOutput
import de.difuture.ekut.pht.lib.runtime.docker.DockerRuntimeClient
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.command.TrainCommand
import de.difuture.ekut.pht.lib.train.api.interf.arrival.DockerRegistryTrainArrival
import de.difuture.ekut.pht.lib.train.api.interf.departure.DockerRegistryTrainDeparture

/**
 * Executes the DockerRegistryTrainArrival by pulling the image first.
 *
 */
internal fun <T> execute(
    interf: DockerRegistryTrainArrival,
    client: DockerRuntimeClient,
    command: TrainCommand<*>,
    info: StationInfo,
    f: (DockerContainerOutput) -> T
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
internal fun <T> execute(
    interf: DockerRegistryTrainDeparture,
    command: TrainCommand<*>,
    info: StationInfo,
    f: (DockerContainerOutput) -> T
) =

        DockerOutputSupplier(
                interf.client.run(
                        interf.imageId,
                        info.commandLine.plus(command.name),
                        rm = true),
                extractor = f
        )
