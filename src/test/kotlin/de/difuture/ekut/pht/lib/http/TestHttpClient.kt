package de.difuture.ekut.pht.lib.http

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import java.net.URI

class TestHttpClient : IHttpGetClient {

    private val client = HttpClientBuilder.create().build()

    override fun get(uri: URI): IHttpResponse = TestHttpResponse(client.execute(HttpGet(uri)))
}
