package de.difuture.ekut.pht.lib.train

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
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

    private fun serialize_deserialize_id(repr: String) {

        val id = TrainId.of(repr)
        val idAsString = jacksonObjectMapper().writeValueAsString(id)

        Assert.assertEquals("\"$repr\"", idAsString)
        val id1 = jacksonObjectMapper().readValue<TrainId>(idAsString)
        Assert.assertEquals(id1, id)
    }

    /*
     * Test valid IDs
     */

    @Test
    fun valid_ids() {

        listOf("train_a", "train_Z").plus(TRAINS_TEST_ALL).forEach {

            Assert.assertEquals(it, TrainId.of(it).repr)
        }
    }

    /*
     *  Test invalid train IDs
     */

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

    /*
     *  Test serialization and deserialization
     */

    @Test
    fun serialize_deserialize_train_id() {

        serialize_deserialize_id("train_foo")
        serialize_deserialize_id("train_bar")
    }
}
