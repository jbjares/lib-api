package de.difuture.ekut.pht.lib.runtime.docker

import de.difuture.ekut.pht.lib.common.docker.DockerContainerId


/**
 * Represents the output of a Docker Container after it has been exited.
 *
 * @author Lukas Zimmermann
 */
data class DockerContainerOutput(

        /**
         * The ID of the container that has been started
         */
        val containerId: DockerContainerId,

        /**
         * The exit code of the exited container
         */
        val exitCode: Int,

        /**
         * Standard output of the container, represented as [String]
         *
         */
        val stdout : String,

        /**
         * Standard error of the container, represented as [String]
         */
        val stderr : String
)
