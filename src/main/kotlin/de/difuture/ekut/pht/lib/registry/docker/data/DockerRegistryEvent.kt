package de.difuture.ekut.pht.lib.registry.docker.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.net.URI
import java.net.URL
import java.util.*


/**
 * Represents a event of a Docker Registry
 *
 * @author Lukas Zimmermann
 */
data class DockerRegistryEvent(

        @JsonProperty("id") val id : UUID,
        @JsonProperty("timestamp") val timestamp : String,
        @JsonProperty("action") val action : Action,
        @JsonProperty("target") val target : Target,
        @JsonProperty("request") val request : Request,
        @JsonProperty("actor") val actor : Actor,
        @JsonProperty("source") val source : Source) {

    enum class Action {
        PULL, PUSH, MOUNT;

        companion object {

            @JsonCreator @JvmStatic
            fun fromString(value : String) : Action = Action.valueOf(value.trim().toUpperCase())
        }
    }

    data class Target(
            @JsonProperty("mediaType") val mediaType : String,
            @JsonProperty("size") val size : Int,
            @JsonProperty("digest") val digest : String,
            @JsonProperty("length") val length : Int,
            @JsonProperty("repository") val repository : String,
            @JsonProperty("url") val url : URL,
            @JsonProperty("tag") val tag : String? // NOTE: Must be nullable
    )

    data class Request(
            @JsonProperty("id") val id : String,
            @JsonProperty("addr") val addr : String,
            @JsonProperty("host") val host : URI,
            @JsonProperty("method") val method : String,
            @JsonProperty("useragent") val useragent : String
    )

    data class Source(
            @JsonProperty("addr") val addr : String,
            @JsonProperty("instanceID") val instanceID : String
    )

    data class Actor(
            @JsonProperty("name") val name : String?
    )
}
