package de.difuture.ekut.pht.lib.registry.docker.data

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Represents the Docker Registry response when requesting the tags for a particular repository
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
data class DockerRegistryTags(

        @JsonProperty("name") val name : String,
        @JsonProperty("tags") val tags : List<String>
) : Iterable<String> by tags
