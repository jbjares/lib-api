package de.difuture.ekut.pht.lib

import org.testcontainers.containers.GenericContainer
import java.net.URI

/**
 * Workaround for Kotlin, as described in :
 *
 * https://github.com/testcontainers/testcontainers-java/issues/318
 *
 */
class SingleExposedPortContainer(private val originalPort : Int, imageName: String) : GenericContainer<SingleExposedPortContainer>(imageName) {

    init {
        this.withExposedPorts(originalPort)
    }

    fun getExternalURI() : URI {

        if (":" in this.containerIpAddress) {

            throw IllegalStateException("For some reason, the IP address of the container contains the ':' character!")
        }
        return URI.create("http://" + this.containerIpAddress + ":" + this.getMappedPort(this.originalPort))
    }
}
