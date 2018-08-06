package de.difuture.ekut.pht.lib.common

/**
 * A series of extension functions.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */

private val dockerHashRegex = Regex("[a-z0-9]+")
private val hostnameRegex
        = Regex("\\p{Alpha}(?:[\\p{Alnum}-]*\\p{Alnum})?(?:\\.\\p{Alpha}(?:[\\p{Alnum}-]*\\p{Alnum})?)*")


fun String.isValidDockerHash() = this.matches(dockerHashRegex)
fun String.isValidHostname() = this.matches(hostnameRegex)
fun String.isValidIP4Address() =

    try {
        val spt = this.split('.').map { it.toInt()}
        spt.size == 4 && spt.all { it in 0..255 }

    } catch( e: NumberFormatException) {

        false
    }

fun String.containsWhitespace() = this.any {it.isWhitespace()}
fun Int.isValidPort() = this > 0
