package de.difuture.ekut.pht.lib.registry.docker

import java.nio.charset.Charset
import java.util.*


fun encodeBase64(auth: BasicAuth, charset: Charset = Charsets.UTF_8 ): String {

    val username = auth.username.trim()
    val password = auth.password.trim()
    return Base64.getEncoder().encodeToString("$username:$password".toByteArray(charset))
}
