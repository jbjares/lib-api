package de.difuture.ekut.pht.lib.train.api.execution.docker

import de.difuture.ekut.pht.lib.runtime.docker.DockerRuntimeClient
import de.difuture.ekut.pht.lib.train.api.execution.DepartureExecution
import de.difuture.ekut.pht.lib.train.api.interf.departure.DockerRegistryTrainDeparture
import de.difuture.ekut.pht.lib.train.api.output.TrainOutput
import de.difuture.ekut.pht.lib.train.api.output.TrainResponse

/**
 * Specialization of the [DepartureExecution] for [DockerRuntimeClient], [DockerRegistryTrainDeparture]
 * and [TrainOutput.DockerTrainOutput]
 *
 */
interface DockerDepartureExecution<A : TrainResponse>
    : DepartureExecution<DockerRegistryTrainDeparture, DockerRuntimeClient, TrainOutput.DockerTrainOutput<A>, A>
