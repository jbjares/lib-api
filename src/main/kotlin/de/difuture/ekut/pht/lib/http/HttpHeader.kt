package de.difuture.ekut.pht.lib.http

import de.difuture.ekut.pht.lib.common.ICanonicalStringRepresentable

enum class HttpHeader(override val repr: String) : ICanonicalStringRepresentable {


    AUTHORIZATION("Authorization")
}
