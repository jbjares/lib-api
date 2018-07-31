package de.difuture.ekut.pht.lib.registry.docker.data

import de.difuture.ekut.pht.lib.allEqualAfterSerialization
import io.github.benas.randombeans.api.EnhancedRandom
import org.junit.Before
import org.junit.Test


class DockerRegistryRepositoriesTests {

    private lateinit var repositories: List<DockerRegistryRepositories>

    @Before
    fun before() {

        this.repositories = EnhancedRandom.randomListOf(30, DockerRegistryRepositories::class.java)
    }

    @Test
    fun testSerialization() {

        allEqualAfterSerialization(this.repositories, DockerRegistryRepositories::class.java)
    }
}
