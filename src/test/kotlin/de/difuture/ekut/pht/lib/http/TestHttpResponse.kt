package de.difuture.ekut.pht.lib.http

import org.apache.http.client.methods.CloseableHttpResponse
import java.io.ByteArrayOutputStream


/**
 * Test class for implementing [IHttpResponse].
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
class TestHttpResponse(response : CloseableHttpResponse) : IHttpResponse {

    override val body: String
    override val statusCode = HttpStatusCode.of(response.statusLine.statusCode)?.let { it } ?:
            throw IllegalStateException("Returned Status Code not supported!")

    // We do not use the headers for testing explicitly, hence we set the empty map
    override val headers = emptyMap<String, String>()

    init {
        val outstream = ByteArrayOutputStream()
        response.entity.writeTo(outstream)
        response.close()
        outstream.flush()
        outstream.close()
        this.body = outstream.toString()
    }
}
