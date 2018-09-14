package de.difuture.ekut.pht.lib.data

import de.difuture.ekut.pht.lib.internal.isValidDockerHash

/**
 * Represents the ID of a Docker Container.
 *
 * The [String] representation of instances must be a a valid Docker container ID.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 */
data class DockerContainerId(val repr: String) {

    init {
        // Docker Container ID must be a valid Docker Hash
        require(repr.isValidDockerHash())
    }
}
