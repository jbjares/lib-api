package de.difuture.ekut.pht.lib.api.registry

interface DockerRegistryClient {

    fun listRepositories() : DockerRegistryRepositories

    fun listTags(repository: String) : DockerRegistryTags
}
