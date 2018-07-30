package de.difuture.ekut.pht.lib.common.docker

import de.difuture.ekut.pht.lib.common.StringRepresentable


/**
 * Represents an image tag
 *
 */
data class DockerTag(

        val value: String

) : StringRepresentable {


    // TODO What Regex does the Docker Tag need to match?
    init {
        require(value.isNotBlank())
    }

    override val stringRepresentation = value
}
