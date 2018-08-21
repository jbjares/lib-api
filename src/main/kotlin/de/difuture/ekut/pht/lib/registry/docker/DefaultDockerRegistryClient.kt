package de.difuture.ekut.pht.lib.registry.docker

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import de.difuture.ekut.pht.lib.common.withQuery
import de.difuture.ekut.pht.lib.http.HttpHeader
import de.difuture.ekut.pht.lib.http.HttpStatusCode
import de.difuture.ekut.pht.lib.http.IHttpGetClient
import de.difuture.ekut.pht.lib.http.IHttpResponse
import de.difuture.ekut.pht.lib.registry.docker.data.BearerToken
import de.difuture.ekut.pht.lib.registry.docker.data.DockerRegistryRepositories
import de.difuture.ekut.pht.lib.registry.docker.data.DockerRegistryTags
import java.net.URI
import java.nio.charset.Charset


/**
 * Implementation of the Docker Registry Client Interface for V2 Docker Registry.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
class DefaultDockerRegistryClient(
        override val uri: URI,
        private val client: IHttpGetClient,
        private val auth: BasicAuth? = null) : IDockerRegistryClient {


    // Catalog URI
    private val catalog = uri.resolve("/v2/_catalog")
    private val mapper = ObjectMapper()


    private fun authHeader(scheme: String, value: String) = mapOf(HttpHeader.AUTHORIZATION to "$scheme $value")


    // Encodes the authentication in a Base 64 string
    private fun basicAuthHeaders(charset: Charset = Charsets.UTF_8): Map<HttpHeader, String> {

        if (auth == null) {

            throw IllegalStateException("basicAuthHeader")
        }
        return authHeader("Basic", encodeBase64(auth, charset))
    }


    private fun bearerAuthHeaders(token: String): Map<HttpHeader, String> {

        if (auth == null) {

            throw IllegalStateException("EncodeAuthBase64 was called")
        }
        return authHeader("Bearer", token)
    }


    private inline fun <reified T : Any> performAuthenticationChallenge(
            response: IHttpResponse, originalURI: URI): T {

        if (auth == null) {

            throw UnhandledDockerRegistryResponseException(
                    "Docker Registry requires authentication, but username/password was not provided")
        }

        // Extract the Authentication challenge from the response headers
        val challenge = response.headers["Www-Authenticate"]
                ?: throw UnhandledDockerRegistryResponseException(
                        "Docker Registry returned 401, but no authentication challenge was provided!")

        // Fold the Value String of the response headers to the challenge attributes
        val challengeAttributes: MutableMap<String, String> = challenge
                .map(::extractKeyValuePairs)
                .fold(mutableMapOf()) { acc, m -> acc.putAll(m); acc }

        // Get the realm and remove from the challenge attribute
        val realm = challengeAttributes[REALM_KEY]?.let { URI.create(it) }
                ?: throw UnhandledDockerRegistryResponseException(
                        "Authenticate Header is present, but no realm was specified")
        challengeAttributes.remove(REALM_KEY)

        // TODO The challenge Attributes can contain advice for the charset of the Base64 encoding

        val challengeResponse = this.client.get(
                realm.withQuery(challengeAttributes, keepOld = true),
                basicAuthHeaders())

        // If the response status is not 200, we could not get a bearer token
        if (challengeResponse.statusCode != HttpStatusCode.OK) {

            throw UnhandledDockerRegistryResponseException("Failed to retrieve bearer token")
        }
        val token = mapper.readValue<BearerToken>(challengeResponse.body)

        // Retry the original URI with the auth token
        val newReponse = this.client.get(originalURI, bearerAuthHeaders(token.token))

        return mapper.readValue(newReponse.body)
    }


    private inline fun <reified T : Any> readGetResponse(uri: URI): T {

        val response = client.get(uri)
        val statusCode = response.statusCode

        return when (statusCode) {

            HttpStatusCode.OK -> return mapper.readValue(response.body)

            // Here we implement the protocol of the Docker Registry to solve the authentication challenge
            HttpStatusCode.UNAUTHORIZED -> performAuthenticationChallenge(response, uri)

            else -> throw UnhandledDockerRegistryResponseException("Cannot handle status code $statusCode")
        }
    }

    override fun listRepositories(): DockerRegistryRepositories = readGetResponse(this.catalog)

    override fun listTags(repository: String): DockerRegistryTags =

            readGetResponse(this.uri.resolve("/v2/$repository/tags/list"))


    companion object {

        // Regex used to extract key value pairs from header strings
        private val extractRegex = Regex("([a-z]+)=\"([^\"]*)\"")

        private const val REALM_KEY = "realm"

        private fun extractKeyValuePairs(input: String) =

                extractRegex.findAll(input).map {
                    val groupValues = it.groupValues
                    groupValues[1] to groupValues[2]
                }.toMap()
    }
}
