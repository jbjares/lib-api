package de.difuture.ekut.pht.lib.common

import java.net.URI

/**
 * Represents tuple of hostname and port, where the port is optional.
 *
 * Currently, the host  must bei either a valid hostname or a valid IP4 address.
 *
 * This class is mainly used as an replacement for [URI] which is considered as too complex for many purposes.
 *
 * @param host The hostname of this tuple, represented as [String]
 * @param port The port of the hostname, can be null if the port is unspecified.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
data class HostPortTuple(

    val host: String,
    val port: Int? = null
) : ICanonicalStringRepresentable {

    init {
        require(host.isValidHostname() || host.isValidIP4Address())
        require(port?.isValidPort() ?: true)
    }
    override val repr = host.plus(port?.let { ":$it" } ?: "")
    constructor(uri: URI) : this(uri.host, uri.port)
}
