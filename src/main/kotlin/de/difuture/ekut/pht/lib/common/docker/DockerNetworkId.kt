package de.difuture.ekut.pht.lib.common.docker

import de.difuture.ekut.pht.lib.common.ICanonicalStringRepresentable
import de.difuture.ekut.pht.lib.common.isValidDockerHash

/**
 * Represents the ID of a Docker Network.
 *
 * The [String] representation of instances must be a a valid Docker network ID.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 */
data class DockerNetworkId(
    override val repr: String
) : ICanonicalStringRepresentable {

    init {
        require(repr.isValidDockerHash())
    }
}
