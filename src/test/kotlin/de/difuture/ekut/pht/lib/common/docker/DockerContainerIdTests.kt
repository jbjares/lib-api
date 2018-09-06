package de.difuture.ekut.pht.lib.common.docker

import org.junit.Assert
import org.junit.Test

class DockerContainerIdTests {

    @Test
    fun valid_container_ids() {

        // without prefix
        listOf(

            "a8c94ff83800", "0", "a", "nd632dw82e", "adabjhdt2"
        ).forEach {
            Assert.assertEquals(it, DockerContainerId(it).repr)
        }

        // with prefix
        listOf(

                "sha256:a8c94ff83800", "sha256:0", "sha256:a", "sha256:nd632dw82e", "sha256:adabjhdt2"
        ).forEach {
            Assert.assertEquals(it, DockerContainerId(it).repr)
        }
    }
}
