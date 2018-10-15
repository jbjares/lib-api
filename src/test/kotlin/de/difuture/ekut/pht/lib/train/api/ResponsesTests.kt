package de.difuture.ekut.pht.lib.train.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.difuture.ekut.pht.lib.equalAfterSerialization
import de.difuture.ekut.pht.lib.train.TrainTag
import org.junit.Test

class ResponsesTests {

    @Test
    fun equal_after_serialization_run_algorithm() {

        val response1 = RunAlgorithmResponseContent("test", success = true, nextTrainTag = TrainTag.of("foo"))
        val response2 = RunAlgorithmResponseContent("test", success = false, nextTrainTag = TrainTag.of("bar"))
        equalAfterSerialization(response1, jacksonObjectMapper(), RunAlgorithmResponseContent::class.java)
        equalAfterSerialization(response2, jacksonObjectMapper(), RunAlgorithmResponseContent::class.java)
    }

    @Test
    fun equal_after_serialization_list_requirements() {

        val response1 = ListRequirementsResponseContent(emptyList())
        val response2 = ListRequirementsResponseContent(listOf("PHT_TEST_REQUIREMENTS"))

        equalAfterSerialization(response1, jacksonObjectMapper(), ListRequirementsResponseContent::class.java)
        equalAfterSerialization(response2, jacksonObjectMapper(), ListRequirementsResponseContent::class.java)
    }

    @Test
    fun equal_after_serialization_check_requirements() {

        val response1 = CheckRequirementsResponseContent("foo", emptyList())
        val response2 = CheckRequirementsResponseContent("bar", listOf("baz"))

        equalAfterSerialization(response1, jacksonObjectMapper(), CheckRequirementsResponseContent::class.java)
        equalAfterSerialization(response2, jacksonObjectMapper(), CheckRequirementsResponseContent::class.java)
    }
}
