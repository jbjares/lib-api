package de.difuture.ekut.pht.lib.http

import jdregistry.client.http.IHttpResponse
import org.apache.http.client.methods.CloseableHttpResponse
import java.io.ByteArrayOutputStream

/**
 * Test class for implementing [IHttpResponse].
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
class TestHttpResponse(response: CloseableHttpResponse) : IHttpResponse {

    override val body: String
    override val statusCode = response.statusLine.statusCode

    // We do not use the headers for testing explicitly, hence we set the empty map
    override val authenticate = emptyList<String>()

    init {
        val outstream = ByteArrayOutputStream()
        response.entity.writeTo(outstream)
        response.close()
        outstream.flush()
        outstream.close()
        this.body = outstream.toString()
    }
}
