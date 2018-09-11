package de.difuture.ekut.pht.lib.train.api.execution

import de.difuture.ekut.pht.lib.runtime.IRuntimeClient
import de.difuture.ekut.pht.lib.runtime.docker.IDockerClient
import de.difuture.ekut.pht.lib.train.api.interf.ITrainDeparture

interface DepartureExecution<A, B: IRuntimeClient> {

    fun execute(interf: ITrainDeparture<B>) : A
}

typealias DockerDepartureExecution<A> = DepartureExecution<A, IDockerClient>
