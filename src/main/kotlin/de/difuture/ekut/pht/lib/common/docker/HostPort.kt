package de.difuture.ekut.pht.lib.common.docker

import de.difuture.ekut.pht.lib.common.StringRepresentable
import java.net.URI


/**
 * Represents tuple of hostname and port, where the port is optional. This is usually used to
 * refer to remote Docker Registries
 *
 * @author Lukas Zimmermann
 *
 */
data class HostPort(

        val host: String,
        val port : Int? = null
) : StringRepresentable {

    init {

        require(host.isNotBlank())
        require(port?.let { it > 0 } ?: true)
    }

    override val stringRepresentation = host.plus(port?.let { ":$it" }  ?: "")


    companion object {

        fun of(uri : URI) = HostPort(uri.host, uri.port)
    }
}
