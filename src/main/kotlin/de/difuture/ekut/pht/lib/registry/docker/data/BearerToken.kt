package de.difuture.ekut.pht.lib.registry.docker.data

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * The Bearer token issued by a Docker Registry Authentication service.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
data class BearerToken(

        @JsonProperty("token") val token: String,
        @JsonProperty("expires_in") val expires_in: Int,
        @JsonProperty("issued_at") val issued_at: String
)
