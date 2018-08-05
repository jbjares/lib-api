package de.difuture.ekut.pht.lib.common

import java.net.URI


/**
 * Represents tuple of hostname and port, where the port is optional.
 * The specification of the port is optional
 *
 * @author Lukas Zimmermann
 *
 */
data class HostPortTuple(

        val host: String,
        val port : Int? = null
) : CanonicalStringRepresentable {

    init {

        require(host.isNotBlank())
        require(port?.let { it > 0 } ?: true)
    }

    override val canonicalStringRepresentation = host.plus(port?.let { ":$it" }  ?: "")


    companion object {

        /**
         * Converts the provided URI to a HostPortTuple
         */
        fun of(uri : URI) = HostPortTuple(uri.host, uri.port)
    }
}
