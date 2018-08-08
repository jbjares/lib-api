package de.difuture.ekut.pht.lib.common.docker

import org.junit.Assert
import org.junit.Test


class DockerTagTests {

    @Test
    fun valid_tags() {

        listOf(
                "ajfks",
                "latest",
                "python3.8-alpine3.8"
        ).forEach {

            Assert.assertEquals(it, DockerTag(it).repr)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_tag_1() { DockerTag("") }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_tag_2() { DockerTag("   ") }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_tag_3() { DockerTag("kjf  akf") }
}
