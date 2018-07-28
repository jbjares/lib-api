package de.difuture.ekut.pht.lib.registry.docker

import de.difuture.ekut.pht.lib.registry.docker.data.DockerRegistryRepositories
import de.difuture.ekut.pht.lib.registry.docker.data.DockerRegistryTags
import java.net.URI


/**
 * Interface for representing client that can
 *
 */
interface IDockerRegistryClient {

    val uri : URI

    fun listRepositories() : DockerRegistryRepositories

    fun listTags(repository: String) : DockerRegistryTags
}
