package de.difuture.ekut.pht.lib.registry.train

import de.difuture.ekut.pht.lib.common.docker.HostPort
import de.difuture.ekut.pht.lib.registry.docker.IDockerRegistryClient
import de.difuture.ekut.pht.lib.registry.train.arrival.DockerTrainArrival
import de.difuture.ekut.pht.lib.registry.train.arrival.ITrainArrival
import de.difuture.ekut.pht.lib.registry.train.tag.TrainTag
import de.difuture.ekut.pht.lib.runtime.IDockerClient


/**
 * Train Registry Implementation that uses the DockerRegistry Interface
 * for implementing the interface of the Train Registry Client
 *
 */
class TrainRegistryClient(private val dockerRegistryClient: IDockerRegistryClient)
    : ITrainRegistryClient<IDockerClient> {


    override fun listTrainArrivals(): List<DockerTrainArrival> =
            dockerRegistryClient
                    .listRepositories()
                    .flatMap { repo ->  dockerRegistryClient.listTags(repo).map {

                        DockerTrainArrival(
                                TrainId(repo),
                                TrainTag.of(it), HostPort.of(dockerRegistryClient.uri))
                    } }

    override fun listTrainArrivals(tag: TrainTag): List<DockerTrainArrival> =
        this.listTrainArrivals().filter { it.trainTag == tag }


    override fun getTrainArrival(id: TrainId, tag: TrainTag): ITrainArrival<IDockerClient>? {

        val arrivals = this.listTrainArrivals().filter { it.trainTag == tag && it.trainId == id}

        return when(arrivals.size) {

            0 -> null
            1 -> arrivals[0]
            else -> throw IllegalStateException("Tuple of TrainID and TrainTag must only resolve to one TrainArrival for each Train Registry")
        }
    }
}
