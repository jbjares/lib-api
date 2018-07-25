package de.difuture.ekut.pht.lib.registry.train

import de.difuture.ekut.pht.lib.registry.docker.IDockerRegistryClient
import de.difuture.ekut.pht.lib.registry.train.departure.AbstractTrainDeparture
import de.difuture.ekut.pht.lib.registry.train.departure.tag.TrainTag


/**
 * Train Registry Implementation that uses the DockerRegistry Interface
 * for implementing the interface of the Train Registry Client
 *
 */
class TrainRegistryClient(private val dockerRegistryClient: IDockerRegistryClient) : ITrainRegistryClient {


    private class TrainDeparture(
            trainId: String,
            trainTag: String,
            client : ITrainRegistryClient
    ) : AbstractTrainDeparture(trainId, trainTag, client)


    override fun listTrainDepartures(): List<AbstractTrainDeparture> =
            dockerRegistryClient
                    .listRepositories()
                    .flatMap { repo ->  dockerRegistryClient.listTags(repo).map { TrainDeparture(repo, it, this) } }

    override fun listTrainDepartures(tag: TrainTag): List<AbstractTrainDeparture> =
        this.listTrainDepartures().filter { it.trainTag == tag }

}
