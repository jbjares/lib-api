package de.difuture.ekut.pht.lib.http

import org.apache.http.client.methods.CloseableHttpResponse
import java.io.ByteArrayOutputStream

class TestHttpResponse(response : CloseableHttpResponse) : IHttpResponse {

    override val body: String
    override val statusCode = response.statusLine.statusCode

    init {
        val outstream = ByteArrayOutputStream()
        response.entity.writeTo(outstream)
        response.close()
        outstream.flush()
        outstream.close()
        this.body = outstream.toString()
    }
}
