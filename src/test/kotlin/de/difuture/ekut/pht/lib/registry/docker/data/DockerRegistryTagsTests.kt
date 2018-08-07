package de.difuture.ekut.pht.lib.registry.docker.data

import de.difuture.ekut.pht.lib.allEqualAfterSerialization
import io.github.benas.randombeans.api.EnhancedRandom
import org.junit.Before
import org.junit.Test

/**
 * Test [DockerRegistryTags]
 *
 * @author Lukas Zimmermann
 * @see DockerRegistryTags
 * @since 0.0.1
 *
 */
class DockerRegistryTagsTests {

    private lateinit var tags: List<DockerRegistryTags>

    @Before
    fun before() {

        this.tags = EnhancedRandom.randomListOf(30, DockerRegistryTags::class.java)
    }

    @Test
    fun testSerialization() {

        allEqualAfterSerialization(this.tags, DockerRegistryTags::class.java)
    }
}
