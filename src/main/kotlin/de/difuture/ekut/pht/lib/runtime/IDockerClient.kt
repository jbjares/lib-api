package de.difuture.ekut.pht.lib.runtime

import de.difuture.ekut.pht.lib.common.docker.DockerRepositoryName
import de.difuture.ekut.pht.lib.common.docker.DockerTag

/**
 *
 *   Interface for implementing Docker Clients to be used with the PHT
 *   infrastructure. The interface is designed such that all methods
 *   throw Exceptions if errors occur. Thus, all return types are not nullable
 *
 * @author Lukas Zimmermann
 */
interface IDockerClient : RuntimeClient {

    /**
     * Runs Image and returns container ID
     *
     */
    fun run(imageId : String, commands : List<String>) : String


    /**
     * Removes container
     *
     */
    fun rm(containerId : String)


    /**
     *  Pulls the repository and returns the
     *  image ID if available, otherwise null
     */
    fun pull(repo : DockerRepositoryName, tag: DockerTag) : String


    /**
     * Returns the log of the running container
     *
     */
    fun log(containerId: String) : String

    /**
     * Removes a Docker image from the client given the specified ID. The 'sha256:' prefix should also
     * be allowed.
     *
     */
    fun rmi(imageId: String)
}
