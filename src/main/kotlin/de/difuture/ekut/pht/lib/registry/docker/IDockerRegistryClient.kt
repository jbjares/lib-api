package de.difuture.ekut.pht.lib.registry.docker

import de.difuture.ekut.pht.lib.registry.docker.data.DockerRegistryRepositories
import de.difuture.ekut.pht.lib.registry.docker.data.DockerRegistryTags
import java.net.URI


/**
 * Interface for representing client that can communicate with a Docker Registry.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
interface IDockerRegistryClient {

    /**
     *  The [URI] of the Docker Registry.
     */
    val uri : URI

    /**
     *  Lists all the available repositories in the Docker Registry.
     *
     *  @return [DockerRegistryRepositories] object that contains all repositories that the
     *  Docker Registry contains.
     *
     */
    fun listRepositories() : DockerRegistryRepositories

    /**
     * Lists all the tags for a specific repository.
     *
     * @param repository The repository as [String] for which all tags should be listed.
     *
     * @return All tags of the requested repository, represented as [DockerRegistryTags].
     */
    fun listTags(repository: String) : DockerRegistryTags
}
