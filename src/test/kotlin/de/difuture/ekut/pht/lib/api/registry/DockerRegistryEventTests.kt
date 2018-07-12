package de.difuture.ekut.pht.lib.api.registry

import de.difuture.ekut.pht.lib.api.allEqualAfterSerialization
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
