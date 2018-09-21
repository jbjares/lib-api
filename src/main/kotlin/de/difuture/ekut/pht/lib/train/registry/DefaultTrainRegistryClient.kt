package de.difuture.ekut.pht.lib.train.registry

import de.difuture.ekut.pht.lib.train.TrainId
import de.difuture.ekut.pht.lib.train.api.interf.arrival.DockerRegistryTrainArrival
import de.difuture.ekut.pht.lib.train.api.interf.arrival.TrainArrival
import jdregistry.client.api.DockerRegistryGetClient

/**
 * Canonical implementation of the [ITrainRegistryClient].
 *
 * This [ITrainRegistryClient] is implemented using an underlying [DockerRegistryGetClient] as
 * engine. Furthermore, the namespace of the remote registry needs to be specified, since
 * an instance of the [DefaultTrainRegistryClient] can only target one namespace at a time.
 *
 * @param dockerRegistryClient The [DockerRegistryGetClient] that should be used for interacting with the train registry
 * @param namespace The namespace of the Docker Registry that is targeted by this client. If the repoName
 * space is null, then the root namespace of the registry is targeted
 *
 * @author Lukas Zimmermann
 * @since 0.1
 *
 */
class DefaultTrainRegistryClient(
    private val dockerRegistryClient: DockerRegistryGetClient,
    private val namespace: String? = null
) : ITrainRegistryClient<DockerRegistryTrainArrival> {

    override fun listTrainArrivals(predicate: (TrainArrival) -> Boolean) = with(dockerRegistryClient) {

        // We have to inspect all repositories in the remote registry
        listRepositories()

                // First, filter down to all repos that have the correct namespace and designate valid Train IDs
                .filter { repo ->
                    when (repo.numberOfComponents) {

                        1 -> namespace == null && TrainId.isValid(repo[0])
                        2 -> repo[0] == namespace && TrainId.isValid(repo[1])
                        else -> false
                    }
                }
                // Map all hits to the Train Arrivals
                .flatMap { repo ->

                    (listTags(repo).tags ?: emptyList()).map { tag ->

                        DockerRegistryTrainArrival(hostname, port, repo, tag)
                    }
                }
                // At last, apply the filter from the predicate
                .filter(predicate)
    }
}
