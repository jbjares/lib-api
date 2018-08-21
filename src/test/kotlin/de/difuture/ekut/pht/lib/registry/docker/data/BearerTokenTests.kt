package de.difuture.ekut.pht.lib.registry.docker.data

import de.difuture.ekut.pht.lib.allEqualAfterSerialization
import io.github.benas.randombeans.api.EnhancedRandom
import org.junit.Before
import org.junit.Test


/**
 * Tests [BearerToken]
 *
 * @author Lukas Zimmermann
 * @see [BearerToken]
 * @since 0.0.1
 *
 */
class BearerTokenTests {

    private lateinit var events: List<BearerToken>

    @Before
    fun before() {

        this.events = EnhancedRandom.randomListOf(30, BearerToken::class.java)
    }

    @Test
    fun testSerialization() {

        allEqualAfterSerialization(this.events, BearerToken::class.java)
    }
}
