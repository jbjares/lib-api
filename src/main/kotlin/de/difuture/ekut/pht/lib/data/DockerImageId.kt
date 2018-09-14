package de.difuture.ekut.pht.lib.data

import de.difuture.ekut.pht.lib.internal.isValidDockerHash

/**
 * Represents the ID of a Docker Image.
 *
 * The [String] representation of instances must be a a valid Docker image ID.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 */
data class DockerImageId(val repr: String) {

    init {
        require(repr.isValidDockerHash())
    }
}
