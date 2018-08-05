package de.difuture.ekut.pht.lib.common

import de.difuture.ekut.pht.lib.common.docker.HostPort
import org.junit.Assert
import org.junit.Test


class HostPortTests {

    @Test
    fun valid_host_ports() {

        val hostPort1 = HostPort("localhost", 80)
        val hostPort2 = HostPort("localhost", 5000)
        val hostPort3 = HostPort("localhost")
        val hostPort4 = HostPort("docker.io")
        val hostPort5 = HostPort("docker.io", port=80)

        Assert.assertEquals("localhost:80", hostPort1.canonicalStringRepresentation)
        Assert.assertEquals("localhost:5000", hostPort2.canonicalStringRepresentation)
        Assert.assertEquals("localhost", hostPort3.canonicalStringRepresentation)
        Assert.assertEquals("docker.io", hostPort4.canonicalStringRepresentation)
        Assert.assertEquals("docker.io:80", hostPort5.canonicalStringRepresentation)
    }


    @Test(expected = IllegalArgumentException::class)
    fun invalid_host_port_1() {

        HostPort("")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_host_port_2() {

        HostPort("sfsf", -10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_host_port_3() {

        HostPort("", -10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_host_port_4() {

        HostPort("   ")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_host_port_5() {

        HostPort("")
    }
}
