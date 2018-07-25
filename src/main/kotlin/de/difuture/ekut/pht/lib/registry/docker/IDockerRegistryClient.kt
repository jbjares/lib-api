package de.difuture.ekut.pht.lib.registry.docker

import de.difuture.ekut.pht.lib.registry.docker.data.DockerRegistryRepositories
import de.difuture.ekut.pht.lib.registry.docker.data.DockerRegistryTags


interface IDockerRegistryClient {

    fun listRepositories() : DockerRegistryRepositories

    fun listTags(repository: String) : DockerRegistryTags
}
