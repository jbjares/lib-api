package de.difuture.ekut.pht.lib.registry.docker

import de.difuture.ekut.pht.lib.allEqualAfterSerialization
import de.difuture.ekut.pht.lib.registry.docker.data.DockerRegistryEvent
import io.github.benas.randombeans.api.EnhancedRandom
import org.junit.Before
import org.junit.Test


class DockerRegistryEventTests {

    private lateinit var events: List<DockerRegistryEvent>

    @Before
    fun before() {

        this.events = EnhancedRandom.randomListOf(30, DockerRegistryEvent::class.java)
    }

    @Test
    fun testSerialization() {

        allEqualAfterSerialization(this.events, DockerRegistryEvent::class.java)
    }
}
