package de.difuture.ekut.pht.lib.common.docker

import de.difuture.ekut.pht.lib.common.ICanonicalStringRepresentable
import de.difuture.ekut.pht.lib.common.containsWhitespace


/**
 * Represents a Docker image tag.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
data class DockerTag(

        override val repr : String

) : ICanonicalStringRepresentable {

    // TODO What Regex does the Docker Tag need to match?
    init {
        require(repr.isNotBlank())
        require( ! repr.containsWhitespace())
    }
}
