package de.difuture.ekut.pht.lib.api.registry

import com.fasterxml.jackson.annotation.JsonProperty


data class DockerRegistryRepositories(

        @JsonProperty("repositories") val repositories : List<String>
) : Iterable<String> by repositories
