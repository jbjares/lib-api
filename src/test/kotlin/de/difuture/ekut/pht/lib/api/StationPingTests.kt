package de.difuture.ekut.pht.lib.api

import io.github.benas.randombeans.api.EnhancedRandom
import org.junit.Before
import org.junit.Test


class StationPingTests {

    private lateinit var pings: List<StationPing>

    @Before
    fun before() {

        this.pings = EnhancedRandom.randomListOf(30, StationPing::class.java)
    }

    @Test
    fun testSerialization() {

        allEqualAfterSerialization(this.pings, StationPing::class.java)
    }
}
