package de.difuture.ekut.pht.lib.registry.train

import de.difuture.ekut.pht.test.lib.TRAINS_TEST_ALL
import org.junit.Assert
import org.junit.Test


class TrainIdTest {

    @Test fun valid_ids() {

        listOf("a", "Z").plus(TRAINS_TEST_ALL).forEach {

            Assert.assertEquals(it, TrainId(it).canonicalStringRepresentation)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_1() { TrainId("") }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_2() { TrainId("_") }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_3() { TrainId("69fkxhzjhfghs") }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_4() { TrainId("-") }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_5() { TrainId("-akjfjk0") }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_6() { TrainId("afbsjf-") }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_7() { TrainId("af  bsjf") }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_8() { TrainId("0") }
}
