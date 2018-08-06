package de.difuture.ekut.pht.lib.common.docker

import de.difuture.ekut.pht.lib.common.ICanonicalStringRepresentable
import de.difuture.ekut.pht.lib.common.isValidDockerHash


/**
 * Represents the ID of a Docker Image.
 *
 * The [String] representation of instances must be a a valid Docker image ID.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 */
data class DockerImageId(
        override val canonicalStringRepresentation: String
) : ICanonicalStringRepresentable {

    init {
        require(canonicalStringRepresentation.isValidDockerHash())
    }
}
