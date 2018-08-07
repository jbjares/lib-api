package de.difuture.ekut.pht.lib.registry.docker.data

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Represents the envelope of events that can be emitted from a Docker Registry
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
data class DockerRegistryEvents (
        @JsonProperty("events") val events : List<DockerRegistryEvent>
) : Iterable<DockerRegistryEvent> by events
