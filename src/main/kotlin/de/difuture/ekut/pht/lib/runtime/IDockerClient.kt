package de.difuture.ekut.pht.lib.runtime

import java.net.URI

/**
 *
 * @author Lukas Zimmermann
 */
interface IDockerClient : RuntimeClient {

    /**
     * Runs Image and returns container ID
     *
     */
    fun run(imageId : String, commands : List<String>) : String?


    /**
     * Removes container and returns whether successful
     *
     */
    fun rm(containerId : String) : Boolean


    /**
     *  Pulls the repository and returns the
     *  image ID if available, otherwise null
     */
    fun pull(registryURI : URI, repository: String, tag: String) : String?


    /**
     * Returns the log of the running container
     *
     */
    fun log(containerId: String) : String?
}
