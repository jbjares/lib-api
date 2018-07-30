package de.difuture.ekut.pht.lib.common

import de.difuture.ekut.pht.lib.common.docker.DockerRepositoryName
import de.difuture.ekut.pht.lib.common.docker.DockerTag
import de.difuture.ekut.pht.lib.common.docker.HostPort
import de.difuture.ekut.pht.lib.cross
import org.junit.Assert
import org.junit.Test


class DockerRepositoryNameTests {

    @Test
    fun valid_names() {

        val name1 = DockerRepositoryName("alpine")
        val name2 = DockerRepositoryName("library", "python")
        val name3 = DockerRepositoryName("library", "python", HostPort("localhost",5000))

        Assert.assertEquals("alpine", name1.stringRepresentation)
        Assert.assertEquals("library/python", name2.stringRepresentation)
        Assert.assertEquals("localhost:5000/library/python", name3.stringRepresentation)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_name_1() {

        DockerRepositoryName("-")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_name_2() {

        DockerRepositoryName("")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_name_3() {

        DockerRepositoryName("-sfsbff")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_name_4() {

        DockerRepositoryName("sfsbff-")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_name_5() {

        DockerRepositoryName("sfsbff/a8fus")
    }


    @Test
    fun resolve_tag() {

        // Hosts
        val localhost = HostPort("localhost", 5000)
        val dockerio = HostPort("docker.io")

        val names = listOf(
                DockerRepositoryName("alpine"),
                DockerRepositoryName("alpine", hostPort = localhost),
                DockerRepositoryName("alpine", hostPort = dockerio) ,
                DockerRepositoryName("library", "python") ,
                DockerRepositoryName("library", "python", hostPort = localhost) ,
                DockerRepositoryName("library", "python", hostPort = dockerio)
        )
        val tags = listOf(
                DockerTag("latest"),
                DockerTag("24-242"),
                DockerTag("3.6.6-alpine3.8")
        )
        val expected = listOf(

                "alpine:latest",
                "alpine:24-242",
                "alpine:3.6.6-alpine3.8",
                "localhost:5000/alpine:latest",
                "localhost:5000/alpine:24-242",
                "localhost:5000/alpine:3.6.6-alpine3.8",
                "docker.io/alpine:latest",
                "docker.io/alpine:24-242",
                "docker.io/alpine:3.6.6-alpine3.8",
                "library/python:latest",
                "library/python:24-242",
                "library/python:3.6.6-alpine3.8",
                "localhost:5000/library/python:latest",
                "localhost:5000/library/python:24-242",
                "localhost:5000/library/python:3.6.6-alpine3.8",
                "docker.io/library/python:latest",
                "docker.io/library/python:24-242",
                "docker.io/library/python:3.6.6-alpine3.8"
        )

        cross(names, tags).withIndex().forEach {

            val idx = it.index
            val name = it.value.first
            val tag = it.value.second
            Assert.assertEquals(expected[idx], name.resolveTag(tag))
        }
    }
}

