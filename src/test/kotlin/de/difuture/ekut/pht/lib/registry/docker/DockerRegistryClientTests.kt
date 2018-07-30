package de.difuture.ekut.pht.lib.registry.docker

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import de.difuture.ekut.pht.lib.SingleExposedPortContainer
import de.difuture.ekut.pht.lib.http.TestHttpClient
import de.difuture.ekut.pht.lib.registry.docker.data.DockerRegistryEvents
import org.apache.http.HttpStatus
import org.junit.ClassRule
import org.junit.Test
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.junit.Assert
import org.junit.Before


class DockerRegistryClientTests {

    /////////////////////////  Used for Notifications communication  //////////////////////////////////////////
    private data class Group(

        @JsonProperty("action") val action : String,
        @JsonProperty("repository") val repository : String,
        @JsonProperty("tag") val tag : String,
        @JsonProperty("items") val items: List<Int>
    )



    /////////////////////////  Companion  //////////////////////////////////////////////////////////////
    companion object {

        // Trains to be tested
        private const val TRAIN_HOSTNAME = "test_hostname"
        private const val TRAIN_PRINT_HELLO_WORLD = "test_print_hello_world"

        // Container that is used for fetching Docker Registry Notifications
        @ClassRule @JvmField
        val REGISTRY : SingleExposedPortContainer =
                SingleExposedPortContainer(
                        5000,
                        "lukaszimmermann/pht-test-train-registry:latest")

        @ClassRule @JvmField
        val NOTIFICATIONS: SingleExposedPortContainer =
                SingleExposedPortContainer(
                        6000,
                        "lukaszimmermann/pht-test-train-registry-notifications:latest")
    }
    /////////////////////////  The registry client  /////////////////////////////////////////////////////////////
    private lateinit var client : IDockerRegistryClient

    @Before
    fun before() {

        // The Docker Registry Client that should be tested
        this.client = DockerRegistryClient(REGISTRY.getExternalURI(), TestHttpClient())
    }

    /////////////////////////  Tests  /////////////////////////////////////////////////////////////////////
    @Test
    fun notifications() {

        val url  = NOTIFICATIONS.getExternalURI()

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

        val init = "init"
        val tagsHostname = this.client.listTags(TRAIN_HOSTNAME)
        val tagsPrintHelloWorld = this.client.listTags(TRAIN_PRINT_HELLO_WORLD)
        Assert.assertEquals(TRAIN_HOSTNAME, tagsHostname.name)
        Assert.assertEquals(TRAIN_PRINT_HELLO_WORLD, tagsPrintHelloWorld.name)
        Assert.assertTrue(init in tagsHostname.tags)
        Assert.assertTrue(init in tagsPrintHelloWorld.tags)
    }
}
