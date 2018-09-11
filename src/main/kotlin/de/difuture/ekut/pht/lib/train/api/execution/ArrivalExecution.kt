package de.difuture.ekut.pht.lib.train.api.execution

import de.difuture.ekut.pht.lib.runtime.IRuntimeClient
import de.difuture.ekut.pht.lib.runtime.docker.IDockerClient
import de.difuture.ekut.pht.lib.train.api.interf.ITrainArrival
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.api.command.ArrivalCommand

interface ArrivalExecution<A, B: IRuntimeClient> {

    // The Arrival Command for which the execution is defined
    val command : ArrivalCommand<A>

    fun execute(interf: ITrainArrival<B>, client: B, info: StationInfo) : A
}

typealias DockerArrivalExecution<A> = ArrivalExecution<A, IDockerClient>
