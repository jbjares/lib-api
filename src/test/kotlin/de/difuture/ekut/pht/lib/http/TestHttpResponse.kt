package de.difuture.ekut.pht.lib.http

import org.apache.http.client.methods.CloseableHttpResponse
import java.io.ByteArrayOutputStream

class TestHttpResponse(response : CloseableHttpResponse) : RestHttpResponse {

    override val body: String

    init {

        val outstream = ByteArrayOutputStream()
        response.entity.writeTo(outstream)
        response.close()
        outstream.close()
        this.body = outstream.toString()
    }
}