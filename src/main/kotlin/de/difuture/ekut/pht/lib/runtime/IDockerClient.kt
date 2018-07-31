package de.difuture.ekut.pht.lib.runtime

import de.difuture.ekut.pht.lib.common.docker.DockerContainerOutput
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
     * Runs the image with the given commands, waits for the container to exit.
     *
     */
    fun run(imageId : String,
            commands : List<String>,
            rm : Boolean,
            timeout : Int) : DockerContainerOutput

    /**
     * Tags the Docker image with the respective ID with the repository and
     * the target tag
     *
     */
    fun tag(sourceImageId: String,
            targetRepo : DockerRepositoryName,
            targetTag : DockerTag)

    /**
     * Removes container
     *
     */
    fun rm(containerId : String)


    /**
     *  Pulls the repository and returns
     */
    fun pull(repo : DockerRepositoryName, tag: DockerTag) : String


    /**
     * Pushes the specified docker Image
     */
    fun push(repo : DockerRepositoryName, tag : DockerTag)

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
    fun rmi(imageId: String, force: Boolean)
}
