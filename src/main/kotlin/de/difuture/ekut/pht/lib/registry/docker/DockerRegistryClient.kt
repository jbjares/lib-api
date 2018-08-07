package de.difuture.ekut.pht.lib.registry.docker

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import de.difuture.ekut.pht.lib.http.IHttpGetClient
import de.difuture.ekut.pht.lib.registry.docker.data.DockerRegistryRepositories
import de.difuture.ekut.pht.lib.registry.docker.data.DockerRegistryTags
import java.net.URI


/**
 * Implementation of the Docker Registry Client Interface for V2 Docker Registry.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
class DockerRegistryClient(
        override val uri : URI,
        private val client : IHttpGetClient) : IDockerRegistryClient {

    // Catalog URI
    private val catalog = uri.resolve("/v2/_catalog")
    private val mapper = ObjectMapper()

    private inline fun <reified T : Any> readGetResponse(uri : URI) : T =

        this.mapper.readValue(this.client.get(uri).body)


    override fun listRepositories() : DockerRegistryRepositories = readGetResponse(this.catalog)

    override fun listTags(repository: String) : DockerRegistryTags =

        readGetResponse(this.uri.resolve("/v2/$repository/tags/list"))
}
