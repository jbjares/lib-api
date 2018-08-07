package de.difuture.ekut.pht.lib.registry.docker.data

import de.difuture.ekut.pht.lib.allEqualAfterSerialization
import io.github.benas.randombeans.api.EnhancedRandom
import org.junit.Before
import org.junit.Test


/**
 * Tests [DockerRegistryEvent]
 *
 * @author Lukas Zimmermann
 * @see [DockerRegistryEvent]
 * @see [DockerRegistryEvents]
 * @since 0.0.1
 *
 */
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
