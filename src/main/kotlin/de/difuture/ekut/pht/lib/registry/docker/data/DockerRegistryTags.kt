package de.difuture.ekut.pht.lib.registry.docker.data

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @author Lukas Zimmermann
 */
data class DockerRegistryTags(

        @JsonProperty("name") val name : String,
        @JsonProperty("tags") val tags : List<String>
) : Iterable<String> by tags
