package de.difuture.ekut.pht.lib.registry.docker.data

import com.fasterxml.jackson.annotation.JsonProperty

data class DockerRegistryEvents (
        @JsonProperty("events") val events : List<DockerRegistryEvent>
) : Iterable<DockerRegistryEvent> by events
