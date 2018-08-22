package de.difuture.ekut.pht.lib.registry.train

import de.difuture.ekut.pht.lib.registry.train.id.ITrainId
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

            Assert.assertEquals(it, ITrainId.of(it).repr)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_1() {
        ITrainId.of("")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_2() {
        ITrainId.of("_")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_3() {
        ITrainId.of("69fkxhzjhfghs")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_4() {
        ITrainId.of("-")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_5() {
        ITrainId.of("-akjfjk0")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_6() {
        ITrainId.of("afbsjf-")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_7() {
        ITrainId.of("af  bsjf")
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalid_8() {
        ITrainId.of("0")
    }
}
