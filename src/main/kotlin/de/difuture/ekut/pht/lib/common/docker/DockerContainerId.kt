package de.difuture.ekut.pht.lib.common.docker

import de.difuture.ekut.pht.lib.common.ICanonicalStringRepresentable
import de.difuture.ekut.pht.lib.common.isValidDockerHash


/**
 * Represents the ID of a Docker Container.
 *
 * The [String] representation of instances must be a a valid Docker container ID.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 */
data class DockerContainerId(
        override val canonicalStringRepresentation: String
) : ICanonicalStringRepresentable {

    init {
        // Docker Container ID must be a valid Docker Hash
        require(canonicalStringRepresentation.isValidDockerHash())
    }
}
