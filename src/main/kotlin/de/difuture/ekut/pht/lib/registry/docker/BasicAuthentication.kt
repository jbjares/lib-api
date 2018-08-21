package de.difuture.ekut.pht.lib.registry.docker

/**
 * Information needed for performing Http Basic Authentication
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
data class BasicAuthentication(

        val username: String,
        val password: String
)
