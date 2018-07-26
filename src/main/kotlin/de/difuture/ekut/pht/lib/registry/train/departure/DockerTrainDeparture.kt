package de.difuture.ekut.pht.lib.registry.train.departure

import de.difuture.ekut.pht.lib.registry.train.ITrainRegistryClient
import de.difuture.ekut.pht.lib.runtime.DockerExecution
import de.difuture.ekut.pht.lib.runtime.IDockerClient

class DockerTrainDeparture(
        trainId: String,
        trainTag: String,
        client : ITrainRegistryClient<IDockerClient>
) : AbstractTrainDeparture<IDockerClient>(trainId, trainTag, client) {

    override fun runAlgorithm(): DockerExecution {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun printSummary(): DockerExecution {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
