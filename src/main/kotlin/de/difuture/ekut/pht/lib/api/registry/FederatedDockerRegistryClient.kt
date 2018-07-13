package de.difuture.ekut.pht.lib.api.registry


/**
 * Collects Docker Register responses over multiple repos
 *
 * @author Lukas Zimmermann
 *
 */
interface FederatedDockerRegistryClient<T> {

    fun getClientByIdentifier(identifier : T) : DockerRegistryClient?

    fun listAllRepositories() : Map<T, DockerRegistryRepositories>
}
