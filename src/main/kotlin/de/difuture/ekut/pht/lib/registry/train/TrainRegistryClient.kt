package de.difuture.ekut.pht.lib.registry.train

import de.difuture.ekut.pht.lib.registry.docker.IDockerRegistryClient
import de.difuture.ekut.pht.lib.registry.train.departure.ITrainDeparture
import de.difuture.ekut.pht.lib.registry.train.departure.tag.TrainTag


/**
 * Train Registry Implementation that uses the DockerRegistry Interface
 * for implementing the interface of the Train Registry Client
 *
 */
class TrainRegistryClient(private val dockerRegistryClient: IDockerRegistryClient) : ITrainRegistryClient {



    private data class TrainDeparture(

            trainId: String,
            trainTag: String,
            private val client : ITrainRegistryClient

    ) : ITrainDeparture {



    }


        override fun listTrainDepartures(tag: TrainTag): List<ITrainDeparture> =

            dockerRegistryClient
                    .listRepositories()
                    .flatMap { repo ->  dockerRegistryClient.listTags(repo).map { TrainDeparture(repo, it) } }

}
