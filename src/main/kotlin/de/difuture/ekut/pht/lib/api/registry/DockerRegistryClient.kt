package de.difuture.ekut.pht.lib.api.registry

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import java.net.URI

class DockerRegistryClient(val uri : URI) {

    // Actions
    private val catalog = uri.resolve("v2/_catalog")

    // Client
    private val client = HttpClientBuilder.create().build()

    private inline fun <reified T : Any> readGetResponse(uri : URI) : T {

        val response = this.client.execute(HttpGet(uri))
        return ObjectMapper().readValue(response.entity.content)
    }

    fun listRepositories() : DockerRegistryRepositories = readGetResponse(this.catalog)


    fun listTags(repository: String) : DockerRegistryTags =

        readGetResponse(this.uri.resolve("/v2/$repository/tags/list"))
}
