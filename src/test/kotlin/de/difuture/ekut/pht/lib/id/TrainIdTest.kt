package de.difuture.ekut.pht.lib.id

import org.junit.Assert
import org.junit.Test


class TrainIdTest {

    @Test
    fun testValidIds() {

        val id1 = TrainId("abcd")
        Assert.assertEquals("abcd", id1.value)


        val id2 = TrainId("0123")
        Assert.assertEquals("0123", id2.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidId1() {

        TrainId("")
    }
    @Test(expected = IllegalArgumentException::class)
    fun testInvalidId2() {

        TrainId("_")
    }
}
