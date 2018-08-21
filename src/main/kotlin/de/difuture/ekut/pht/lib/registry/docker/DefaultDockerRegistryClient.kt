package de.difuture.ekut.pht.lib.registry.docker

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import de.difuture.ekut.pht.lib.http.HttpHeader
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
class DefaultDockerRegistryClient(
        override val uri : URI,
        private val client : IHttpGetClient,
        token : String? = null) : IDockerRegistryClient {

    // Catalog URI
    private val catalog = uri.resolve("/v2/_catalog")
    private val mapper = ObjectMapper()

    // Header for GET requests
    private val header = token?.let { mapOf( HttpHeader.AUTHORIZATION to "token $it")}


    private inline fun <reified T : Any> readGetResponse(uri : URI) : T =

        this.mapper.readValue(client.get(uri, header).body)



    override fun listRepositories() : DockerRegistryRepositories = readGetResponse(this.catalog)

    override fun listTags(repository: String) : DockerRegistryTags =

        readGetResponse(this.uri.resolve("/v2/$repository/tags/list"))
}
