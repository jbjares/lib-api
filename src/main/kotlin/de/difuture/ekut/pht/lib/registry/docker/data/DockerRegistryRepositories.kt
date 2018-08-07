package de.difuture.ekut.pht.lib.registry.docker.data

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Represents the response from the Docker Registry when requesting contained repositories.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
data class DockerRegistryRepositories(

        @JsonProperty("repositories") val repositories : List<String>
) : Iterable<String> by repositories
