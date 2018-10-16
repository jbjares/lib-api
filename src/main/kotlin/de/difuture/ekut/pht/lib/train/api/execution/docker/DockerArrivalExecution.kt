package de.difuture.ekut.pht.lib.train.api.execution.docker

import de.difuture.ekut.pht.lib.runtime.docker.DockerRuntimeClient
import de.difuture.ekut.pht.lib.train.api.execution.ArrivalExecution
import de.difuture.ekut.pht.lib.train.api.interf.arrival.DockerRegistryTrainArrival
import de.difuture.ekut.pht.lib.train.api.output.TrainOutput
import de.difuture.ekut.pht.lib.train.api.output.TrainResponse

/**
 * Specialization of the [ArrivalExecution] for [DockerRuntimeClient], [DockerRegistryTrainArrival]
 * and [TrainOutput.DockerTrainOutput]
 *
 */
interface DockerArrivalExecution<A : TrainResponse>
    : ArrivalExecution<DockerRegistryTrainArrival, DockerRuntimeClient, TrainOutput.DockerTrainOutput<A>, A>
