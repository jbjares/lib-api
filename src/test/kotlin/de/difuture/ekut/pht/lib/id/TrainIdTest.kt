package de.difuture.ekut.pht.lib.id

import de.difuture.ekut.pht.lib.registry.train.departure.TrainId
import org.junit.Assert
import org.junit.Test


class TrainIdTest {

    @Test
    fun testValidIds() {

        val id1 = TrainId("abcd")
        Assert.assertEquals("abcd", id1.stringRepresentation)


        val id2 = TrainId("q0123")
        Assert.assertEquals("q0123", id2.stringRepresentation)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidId1() {

        TrainId("")
    }
    @Test(expected = IllegalArgumentException::class)
    fun testInvalidId2() {

        TrainId("_")
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidId3() {

        TrainId("69fkxhzjhfghs")
    }
}
