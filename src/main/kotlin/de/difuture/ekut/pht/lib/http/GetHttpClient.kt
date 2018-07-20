package de.difuture.ekut.pht.lib.http

import java.net.URI


/**
 * Interface for HttpClient that only support the HTTP GET method on the provided URI.
 *
 * @author Lukas Zimmermann
 *
 */
interface GetHttpClient {

    fun get(uri : URI) : HttpResponse
}
