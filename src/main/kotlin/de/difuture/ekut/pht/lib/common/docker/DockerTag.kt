package de.difuture.ekut.pht.lib.common.docker

import de.difuture.ekut.pht.lib.common.CanonicalStringRepresentable


/**
 * Represents an Docker image tag.
 *
 * @author Lukas Zimmermann
 */
data class DockerTag(

        val value: String

) : CanonicalStringRepresentable {


    // TODO What Regex does the Docker Tag need to match?
    init {
        require(value.isNotBlank())
    }

    override val canonicalStringRepresentation = value
}
