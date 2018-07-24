package de.difuture.ekut.pht.lib.registry.docker

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import de.difuture.ekut.pht.lib.http.GetHttpClient
import java.net.URI

class DockerRegistryClient(
        private val uri : URI,
        private val client : GetHttpClient) : IDockerRegistryClient {

    // Catalog URI
    private val catalog = uri.resolve("v2/_catalog")

    private inline fun <reified T : Any> readGetResponse(uri : URI) : T {

        val response = this.client.get(uri)
        return ObjectMapper().readValue(response.content)
    }

    override fun listRepositories() : DockerRegistryRepositories = readGetResponse(this.catalog)

    override fun listTags(repository: String) : DockerRegistryTags =

        readGetResponse(this.uri.resolve("/v2/$repository/tags/list"))
}
