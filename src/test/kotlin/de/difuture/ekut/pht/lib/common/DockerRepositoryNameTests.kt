package de.difuture.ekut.pht.lib.common

import de.difuture.ekut.pht.lib.common.docker.DockerRepositoryName
import de.difuture.ekut.pht.lib.common.docker.HostPort
import org.junit.Assert
import org.junit.Test


class DockerRepositoryNameTests {

    @Test
    fun test_valid_names() {

        val name1 = DockerRepositoryName("alpine")
        val name2 = DockerRepositoryName("library", "python")
        val name3 = DockerRepositoryName("library", "python", HostPort("localhost",5000))

        Assert.assertEquals("alpine", name1.stringRepresentation)
        Assert.assertEquals("library/python", name2.stringRepresentation)
        Assert.assertEquals("localhost:5000/library/python", name3.stringRepresentation)
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_invalid_name_1() {

        DockerRepositoryName("-")
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_invalid_name_2() {

        DockerRepositoryName("")
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_invalid_name_3() {

        DockerRepositoryName("-sfsbff")
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_invalid_name_4() {

        DockerRepositoryName("sfsbff-")
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_invalid_name_5() {

        DockerRepositoryName("sfsbff/a8fus")
    }
}

