package de.difuture.ekut.pht.lib.registry.docker

import org.junit.Assert
import org.junit.Test


/**
 * Tests for the Utils.
 *
 * @author Lukas Zimmermann]
 * @since 0.0.1
 *
 */
class UtilsTests {

    @Test fun base64Encoding() {

        // TODO More Base64 encoding test cases
        mapOf(
            BasicAuth("Aladdin", "open sesame") to "QWxhZGRpbjpvcGVuIHNlc2FtZQ=="

        ).forEach {

            Assert.assertEquals(encodeBase64(it.key), it.value)
        }
    }
}
