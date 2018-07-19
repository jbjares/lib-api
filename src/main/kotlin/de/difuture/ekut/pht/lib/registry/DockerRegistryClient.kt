package de.difuture.ekut.pht.lib.registry


interface DockerRegistryClient {

    fun listRepositories() : DockerRegistryRepositories

    fun listTags(repository: String) : DockerRegistryTags
}
