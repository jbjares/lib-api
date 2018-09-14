package de.difuture.ekut.pht.lib.train

import de.difuture.ekut.pht.test.lib.TRAINS_TEST_ALL
import org.junit.Assert
import org.junit.Test

/**
 * Tests [ITrainId]
 *
 * @author Lukas Zimmermann
 * @see ITrainId
 * @since 0.0.1
 *
 */
class TrainIdTest {

    @Test fun valid_ids() {

        listOf("train_a", "train_Z").plus(TRAINS_TEST_ALL).forEach {

            Assert.assertEquals(it, TrainId.of(it).repr)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_1() {
        TrainId.of("")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_2() {
        TrainId.of("_")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_3() {
        TrainId.of("69fkxhzjhfghs")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_4() {
        TrainId.of("-")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_5() {
        TrainId.of("-akjfjk0")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_6() {
        TrainId.of("afbsjf-")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_7() {
        TrainId.of("af  bsjf")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_8() {
        TrainId.of("0")
    }
}