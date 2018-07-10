package de.difuture.ekut.pht.lib.api

import io.github.benas.randombeans.api.EnhancedRandom
import org.junit.Before
import org.junit.Test


class StationPingTests {

    private lateinit var events: List<StationPing>

    @Before
    fun before() {

        this.events = EnhancedRandom.randomListOf(20, StationPing::class.java)
    }

    @Test
    fun testSerialization() {

        allEqualAfterSerialization(this.events, StationPing::class.java)
    }
}
