package de.difuture.ekut.pht.lib.registry.docker


interface DockerRegistryClient {

    fun listRepositories() : DockerRegistryRepositories

    fun listTags(repository: String) : DockerRegistryTags
}
