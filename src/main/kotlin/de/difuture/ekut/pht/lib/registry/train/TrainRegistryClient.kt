package de.difuture.ekut.pht.lib.registry.train

import de.difuture.ekut.pht.lib.registry.docker.IDockerRegistryClient
import de.difuture.ekut.pht.lib.registry.train.departure.DockerTrainDeparture
import de.difuture.ekut.pht.lib.registry.train.departure.TrainId
import de.difuture.ekut.pht.lib.registry.train.departure.tag.TrainTag
import de.difuture.ekut.pht.lib.runtime.IDockerClient


/**
 * Train Registry Implementation that uses the DockerRegistry Interface
 * for implementing the interface of the Train Registry Client
 *
 */
class TrainRegistryClient(private val dockerRegistryClient: IDockerRegistryClient)
    : ITrainRegistryClient<IDockerClient> {


    override fun listTrainDepartures(): List<DockerTrainDeparture> =
            dockerRegistryClient
                    .listRepositories()
                    .flatMap { repo ->  dockerRegistryClient.listTags(repo).map {

                        DockerTrainDeparture(
                                TrainId(repo),
                                TrainTag.of(it), dockerRegistryClient.uri)
                    } }

    override fun listTrainDepartures(tag: TrainTag): List<DockerTrainDeparture> =
        this.listTrainDepartures().filter { it.trainTag == tag }
}
