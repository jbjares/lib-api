package de.difuture.ekut.pht.lib.common.docker


/**
 * Represents the output of a Docker Container after having been started
 *
 * @author Lukas Zimmermann
 */
data class DockerContainerOutput(

        val containerId: String,
        val exitCode: Int,
        val stdout : String,
        val stderr : String
)
