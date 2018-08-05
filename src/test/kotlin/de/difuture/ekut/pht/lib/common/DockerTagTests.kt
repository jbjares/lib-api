package de.difuture.ekut.pht.lib.common

import de.difuture.ekut.pht.lib.common.docker.DockerTag
import org.junit.Assert
import org.junit.Test


class DockerTagTests {

    @Test
    fun valid_tags() {

        val tag1 = DockerTag("ajfks")
        val tag2 =  DockerTag("latest")

        Assert.assertEquals("ajfks", tag1.canonicalStringRepresentation)
        Assert.assertEquals("latest", tag2.canonicalStringRepresentation)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_tag_1() {

        DockerTag("")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_tag_2() {

        DockerTag("   ")
    }
}
