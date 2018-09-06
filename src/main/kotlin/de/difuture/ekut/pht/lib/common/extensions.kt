package de.difuture.ekut.pht.lib.common

import java.net.URI

/**
 * A series of extension functions.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */

/**
 * The regular expression for Docker Hash values. The sha256: prefix is optional
 */
private val dockerHashRegex = Regex("(?:sha256:)?[a-z0-9]+")

private val hostnameRegex = Regex("\\p{Alpha}(?:[\\p{Alnum}-]*\\p{Alnum})?(?:\\.\\p{Alpha}(?:[\\p{Alnum}-]*\\p{Alnum})?)*")

fun String.isValidDockerHash() = this.matches(dockerHashRegex)

fun String.isValidHostname() = this.matches(hostnameRegex)

/**
 * Tests whether this [String] denotes a valid IP4 address
 *
 * @return Whether this [String] denotes a valid IP4 Address.
 */
fun String.isValidIP4Address() =

        try {
            val spt = this.split('.').map { it.toInt() }
            spt.size == 4 && spt.all { it in 0..255 }
        } catch (e: NumberFormatException) {

            false
        }

/**
 * Tests whether this [String] contains any whitespace character.
 *
 */
fun String.containsWhitespace() = this.any { it.isWhitespace() }

/**
 * Supposed to test whether this integer denotes a valid port.
 *
 */
fun Int.isValidPort() = this > 0

/**
 * Constructs new [URI] by either replacing or appending more query parameters
 *
 */
fun URI.withQuery(params: Map<String, String>, keepOld: Boolean): URI {

    // Construct  query string from map
    val queryExtend = params.map { "${it.key}=${it.value}" }.joinToString("&")

    // Platform type: String can be null
    val oldQuery: String? = this.query
    val newQuery = if (keepOld && oldQuery != null) {
        "$oldQuery&$queryExtend"
    } else {
        queryExtend
    }

    return URI(this.scheme, this.authority, this.path, newQuery, this.fragment)
}
