package de.difuture.ekut.pht.lib.common.docker

import de.difuture.ekut.pht.lib.common.ICanonicalStringRepresentable
import de.difuture.ekut.pht.lib.common.HostPortTuple


/**
 * Represents a valid Docker Repository Name.
 *
 * Currently we allow up to two path components.Each component needs to match the regular expression
 * that the Docker Registry v2 implementation uses for such names.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
data class DockerRepositoryName(

        val component1: String,
        val component2: String? = null,
        val hostPortTuple: HostPortTuple? = null
) : ICanonicalStringRepresentable {

    override val repr: String

    init {

        // Require that the components match the regular expression
        require(componentRegex.matches(component1))
        component2?.apply {
            require(componentRegex.matches(this))
        }

        // Compute the canonical string representation
        // Join components with a '/' character
        val components = component1.plus(component2?.let { "/$it" } ?: "")

        this.repr = if (hostPortTuple != null) {

            "${hostPortTuple.repr}/$components"
        } else {

            components
        }
    }

    /**
     * Resolves this Docker Repository Name against a Docker tag and returns the respective string
     * representation.
     *
     * @param tag [DockerTag] against this [DockerRepositoryName] should be resolved.
     * @return [String] representation of the Docker Name
     */
    fun resolveTag(tag: DockerTag) = "$repr:${tag.repr}"

    companion object {
        private val componentRegex = Regex("[a-z0-9]+(?:[._-][a-z0-9]+)*")
    }
}
