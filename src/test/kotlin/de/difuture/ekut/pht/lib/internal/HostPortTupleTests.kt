package de.difuture.ekut.pht.lib.internal

import de.difuture.ekut.pht.lib.cross
import org.junit.Assert
import org.junit.Test

class HostPortTupleTests {

    // Valid HostPortTuple instances
    @Test fun valid_host_ports() {

        val hostnames = listOf(
                "localhost",
                "data.io",
                "en.wikipedia.org",
                "a.b.c.d",
                "foo-bar",
                "sf-09.a-d",
                "192.168.2.4",
                "0.0.0.0"
        )
        val ports = listOf(
                null,
                80,
                5000,
                127,
                8080,
                442
        )

        cross(hostnames, ports).forEach { (host, port) ->

            val portString = port?.let { ":$it" } ?: ""
            Assert.assertEquals(

                    HostPortTuple(host, port).repr, host.plus(portString))
        }
    }

    // Invalid HostPortTuple instances

    @Test(expected = IllegalArgumentException::class)
    fun invalid_host_port_1() {
        HostPortTuple("")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_host_port_2() {
        HostPortTuple("sfsf", -10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_host_port_3() {
        HostPortTuple("", -10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_host_port_4() {
        HostPortTuple("   ")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_host_port_5() {
        HostPortTuple("--")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_host_port_6() {
        HostPortTuple("0skfsu")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_host_port_7() {
        HostPortTuple("qruw-")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_host_port_8() {
        HostPortTuple("sufh..autfu")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_host_port_9() {
        HostPortTuple("192.187.123.213.123")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_host_port_10() {
        HostPortTuple("192.187.123.500")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_host_port_11() {
        HostPortTuple("192.187.ads.500")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_host_port_12() {
        HostPortTuple("192.187..500")
    }
}
