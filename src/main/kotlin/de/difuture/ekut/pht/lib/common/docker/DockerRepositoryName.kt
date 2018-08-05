package de.difuture.ekut.pht.lib.common.docker

import de.difuture.ekut.pht.lib.common.CanonicalStringRepresentable
import de.difuture.ekut.pht.lib.common.HostPortTuple


/**
 * Represents a valid Docker Repository Name. Currently we allow up to two path components.
 * Each component needs to match the regular expression that the Docker Registry v2 implementation
 * uses for such names.
 *
 * @author Lukas Zimmermann
 *
 */
data class DockerRepositoryName(

        val component1 : String,
        val component2 : String? = null,
        val hostPortTuple : HostPortTuple? = null
) : CanonicalStringRepresentable {

    init {

        // Require that the components match the regular expression
        require(componentRegex.matches(component1))
        component2?.apply {
            require(componentRegex.matches(this))
        }
    }

    override val canonicalStringRepresentation: String
        get()  {

            // Join components with a '/' character
            val components = component1.plus(component2?.let { "/$it" }  ?: "")

            return if (hostPortTuple != null) {

                "${hostPortTuple.canonicalStringRepresentation}/$components"
            } else {

                components
            }
        }


    /**
     * Resolves this Docker Repository Name against a tag and returns the respective string
     * Representation
     */
    fun resolveTag(tag : DockerTag) = "$canonicalStringRepresentation:${tag.canonicalStringRepresentation}"


    companion object {
        private val componentRegex = Regex("[a-z0-9]+(?:[._-][a-z0-9]+)*")
    }
}
