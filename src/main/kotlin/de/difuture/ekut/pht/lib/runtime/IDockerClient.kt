package de.difuture.ekut.pht.lib.runtime

/**
 *
 * @author Lukas Zimmermann
 */
interface IDockerClient : RuntimeClient {

    /**
     * Runs Image and returns container ID
     *
     */
    fun run(imageId : String) : String


    /**
     * Removes container and returns whether successful
     *
     */
    fun rm(containerId : String) : Boolean


    /**
     * Pulls repository and reports whether successful
     *
     */
    fun pull(repository: String) : Boolean


    /**
     * Pushes repository and reports whether successful
     */
    fun push(repository: String) : Boolean


    /**
     * Commits container and returns ID of newly created image
     *
     */
    fun commitContainer(containerId: String, repo : String, tag : String) : String
}
