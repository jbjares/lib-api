package de.difuture.ekut.pht.lib.station

import de.difuture.ekut.pht.lib.allEqualAfterSerialization
import de.difuture.ekut.pht.lib.payload.StationPing
import io.github.benas.randombeans.api.EnhancedRandom
import org.junit.Before
import org.junit.Test

/**
 * Tests [StationPing]
 *
 * @author Lukas Zimmermann
 * @see [StationPing]
 * @since 0.0.1
 *
 */
class StationPingTests {

    private lateinit var events: List<StationPing>

    @Before
    fun before() {

        this.events = EnhancedRandom.randomListOf(30, StationPing::class.java)
    }

    @Test
    fun testSerialization() {

        allEqualAfterSerialization(this.events, StationPing::class.java)
    }
}
