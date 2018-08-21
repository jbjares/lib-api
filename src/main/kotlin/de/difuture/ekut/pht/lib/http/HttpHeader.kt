package de.difuture.ekut.pht.lib.http

import de.difuture.ekut.pht.lib.common.ICanonicalStringRepresentable


/**
 * Represents HttpHeaders that can be sent with the [IHttpGetClient]
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
enum class HttpHeader(override val repr: String) : ICanonicalStringRepresentable {

    /**
     * Header used for authorization
     */
    AUTHORIZATION("Authorization")
}
