package de.difuture.ekut.pht.lib.api.registry

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.palantir.docker.compose.DockerComposeRule
import com.palantir.docker.compose.ImmutableDockerComposeRule
import com.sun.xml.internal.ws.policy.AssertionSet
import de.difuture.ekut.pht.lib.api.train.TRAIN_TAG_INIT
import org.apache.http.HttpStatus
import org.junit.ClassRule
import org.junit.Test
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.junit.Assert
import org.junit.Before
import java.net.URI


class DockerRegistryIntegrationTests {

    private data class Group(

        @JsonProperty("action") val action : String,
        @JsonProperty("repository") val repository : String,
        @JsonProperty("tag") val tag : String,
        @JsonProperty("items") val items: List<Int>
    )

    companion object {

        // Set up the JUnit rule for firing up the test services
        @ClassRule @JvmField
        val rule : ImmutableDockerComposeRule = DockerComposeRule.builder()
                .pullOnStartup(true)
                .file("src/test/resources/docker-compose.yml")
                .build()

        // Trains to be tested
        private const val TRAIN_HOSTNAME = "test_hostname"
        private const val TRAIN_PRINT_HELLO_WORLD = "test_print_hello_world"
    }

    // The single Registry Client
    private lateinit var client : URIDockerRegistryClient

    // The federated Registry Client
    private lateinit var federatedClient : FederatedDockerRegistryClient<URI>

    @Before
    fun before() {

        val uri = URI.create(
                rule.containers()
                .container("registry")
                .port(5000)
                .inFormat("http://\$HOST:\$EXTERNAL_PORT/"))
        this.client = DefaultURIDockerRegistryClient(uri)

        this.federatedClient = federatedClient(this.client)
    }

    @Test
    fun notifications() {

        // URL
        val url = rule.containers()
                .container("notifications")
                .port(6000)
                .inFormat("http://\$HOST:\$EXTERNAL_PORT/")

        // Get groups
        val httpResponse = HttpClientBuilder.create().build().execute(HttpGet("$url/groups"))

        // Assert 200 for the groups to be tested
        Assert.assertEquals(httpResponse.statusLine.statusCode, HttpStatus.SC_OK)

        // Deserialize groups
        val mapper = ObjectMapper()
        val groups : List<Group> = mapper.readValue(
                        httpResponse.entity.content)

        // Test all groups and all items
        for (group in groups) {

            for (item in group.items) {

                val name = "${group.action}_${group.repository}_${group.tag}_$item.json"
                val itemResponse = HttpClientBuilder.create().build().execute(HttpGet("$url/$name"))
                Assert.assertEquals(itemResponse.statusLine.statusCode, HttpStatus.SC_OK)
                val events : DockerRegistryEvents = mapper.readValue(itemResponse.entity.content)

                // Test that each event envelope only contains one event
                Assert.assertEquals(1, events.events.size)
                val event = events.first()

                // Tag of event is either null or the group tag // TODO Hamcrest?
                Assert.assertTrue( event.target.tag == null  || event.target.tag == group.tag)

                // Repository must be group repository
                Assert.assertEquals(group.repository, event.target.repository)
            }
        }
    }


    @Test
    fun client_list_trains() {

        val repos = this.client.listRepositories().repositories
        Assert.assertTrue(TRAIN_HOSTNAME in repos && TRAIN_PRINT_HELLO_WORLD in repos)
    }

    @Test
    fun client_list_tags() {

        val tagsHostname = this.client.listTags(TRAIN_HOSTNAME)
        val tagsPrintHelloWorld = this.client.listTags(TRAIN_PRINT_HELLO_WORLD)
        Assert.assertEquals(TRAIN_HOSTNAME, tagsHostname.name)
        Assert.assertEquals(TRAIN_PRINT_HELLO_WORLD, tagsPrintHelloWorld.name)
        Assert.assertTrue(TRAIN_TAG_INIT in tagsHostname.tags)
        Assert.assertTrue(TRAIN_TAG_INIT in tagsPrintHelloWorld.tags)
    }


    @Test
    fun federated_client_listAllRepositories() {

        val resp = this.federatedClient.listAllRepositories()

        // URI of the single client must be a key in the returned map
        Assert.assertTrue(this.client.uri in resp)
        Assert.assertEquals(1, resp.size)
        val resp2 = this.federatedClient.getClientByIdentifier(this.client.uri)?.listRepositories() ?: Assert.fail()
        Assert.assertEquals(resp2, resp[this.client.uri])
    }
}
