package de.difuture.ekut.pht.lib.common.docker

import org.junit.Assert
import org.junit.Test

class DockerImageIdTests {


    @Test
    fun valid_image_ids() {

        listOf(

            "a8c94ff83800", "0", "a", "nd632dw82e", "adabjhdt2"
        ).forEach {
            Assert.assertEquals(it, DockerImageId(it).canonicalStringRepresentation)
        }
    }
}
