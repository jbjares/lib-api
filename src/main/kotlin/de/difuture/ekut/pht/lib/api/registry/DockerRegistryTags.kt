package de.difuture.ekut.pht.lib.api.registry

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @author Lukas Zimmermann
 */
data class DockerRegistryTags(

        @JsonProperty("name") val name : String,
        @JsonProperty("tags") val tags : List<String>
)
