package de.difuture.ekut.pht.lib.registry.docker


interface IDockerRegistryClient {

    fun listRepositories() : DockerRegistryRepositories

    fun listTags(repository: String) : DockerRegistryTags
}
