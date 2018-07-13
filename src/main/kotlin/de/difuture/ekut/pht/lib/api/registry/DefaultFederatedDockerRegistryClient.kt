package de.difuture.ekut.pht.lib.api.registry

class DefaultFederatedDockerRegistryClient<T>
constructor(private val clients: Map<T, DockerRegistryClient>)
    : FederatedDockerRegistryClient<T> {

    override fun getClientByIdentifier(identifier: T) = this.clients[identifier]
    override fun listAllRepositories() = this.clients.mapValues { it.value.listRepositories() }
}
