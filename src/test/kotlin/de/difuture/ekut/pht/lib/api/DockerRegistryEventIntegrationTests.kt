package de.difuture.ekut.pht.lib.api

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.palantir.docker.compose.DockerComposeRule
import com.palantir.docker.compose.ImmutableDockerComposeRule
import org.apache.http.HttpStatus
import org.junit.ClassRule
import org.junit.Test
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.junit.Assert


class DockerRegistryEventIntegrationTests {

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
    }

    @Test
    fun notifications() {

        // URL
        val url = rule.containers()
                .container("notifications")
                .port(5000)
                .inFormat("http://\$HOST:\$EXTERNAL_PORT/")

        // Get groups
        val httpResponse = HttpClientBuilder.create().build().execute(HttpGet("$url/groups"))

        // Assert 200 for the groups to be tested
        Assert.assertEquals(httpResponse.statusLine.statusCode, HttpStatus.SC_OK)

        // Deserialize groups
        val mapper = ObjectMapper()
        val groups : List<Group> = mapper.readValue(
                        httpResponse.entity.content,
                        mapper.typeFactory.constructCollectionType(List::class.java, Group::class.java))

        // Test all groups and all items
        for (group in groups) {

            for (item in group.items) {

                val name = "${group.action}_${group.repository}_${group.tag}_$item.json"
                val itemResponse = HttpClientBuilder.create().build().execute(HttpGet("$url/$name"))
                Assert.assertEquals(itemResponse.statusLine.statusCode, HttpStatus.SC_OK)
                val events = mapper.readValue(itemResponse.entity.content, DockerRegistryEvents::class.java)

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
}
