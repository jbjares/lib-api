package de.difuture.ekut.pht.lib.http

import org.apache.http.client.methods.CloseableHttpResponse
import java.io.InputStream

class TestHttpResponse(response : CloseableHttpResponse) : HttpResponse {

    override val content: InputStream = response.entity.content

    override fun close() {
        this.content.close()
    }
}