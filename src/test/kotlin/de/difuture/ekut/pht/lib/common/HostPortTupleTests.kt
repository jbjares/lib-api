package de.difuture.ekut.pht.lib.common

import org.junit.Assert
import org.junit.Test


class HostPortTupleTests {

    @Test
    fun valid_host_ports() {

        val hostPort1 = HostPortTuple("localhost", 80)
        val hostPort2 = HostPortTuple("localhost", 5000)
        val hostPort3 = HostPortTuple("localhost")
        val hostPort4 = HostPortTuple("docker.io")
        val hostPort5 = HostPortTuple("docker.io", port = 80)

        Assert.assertEquals("localhost:80", hostPort1.canonicalStringRepresentation)
        Assert.assertEquals("localhost:5000", hostPort2.canonicalStringRepresentation)
        Assert.assertEquals("localhost", hostPort3.canonicalStringRepresentation)
        Assert.assertEquals("docker.io", hostPort4.canonicalStringRepresentation)
        Assert.assertEquals("docker.io:80", hostPort5.canonicalStringRepresentation)
    }


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

        HostPortTuple("")
    }
}
