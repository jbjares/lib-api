package de.difuture.ekut.pht.lib.http

import java.io.Closeable
import java.io.InputStream

interface HttpResponse : Closeable, AutoCloseable {

    val content : InputStream
}
