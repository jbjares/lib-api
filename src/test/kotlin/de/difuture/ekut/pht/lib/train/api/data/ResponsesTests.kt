package de.difuture.ekut.pht.lib.train.api.data

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.difuture.ekut.pht.lib.equalAfterSerialization
import de.difuture.ekut.pht.lib.train.TrainTag
import org.junit.Test

class ResponsesTests {

    @Test
    fun equal_after_serialization_run_algorithm() {

        val response1 = RunAlgorithmResponse("test", success = true, nextTrainTag = TrainTag.of("foo"))
        val response2 = RunAlgorithmResponse("test", success = false, nextTrainTag = TrainTag.of("bar"))
        equalAfterSerialization(response1, jacksonObjectMapper(), RunAlgorithmResponse::class.java)
        equalAfterSerialization(response2, jacksonObjectMapper(), RunAlgorithmResponse::class.java)
    }

    @Test
    fun equal_after_serialization_list_requirements() {

        val response1 = ListRequirementsResponse(emptyList())
        val response2 = ListRequirementsResponse(listOf("PHT_TEST_REQUIREMENTS"))

        equalAfterSerialization(response1, jacksonObjectMapper(), ListRequirementsResponse::class.java)
        equalAfterSerialization(response2, jacksonObjectMapper(), ListRequirementsResponse::class.java)
    }

    @Test
    fun equal_after_serialization_check_requirements() {

        val response1 = CheckRequirementsResponse("foo", emptyList())
        val response2 = CheckRequirementsResponse("bar", listOf("baz"))

        equalAfterSerialization(response1, jacksonObjectMapper(), CheckRequirementsResponse::class.java)
        equalAfterSerialization(response2, jacksonObjectMapper(), CheckRequirementsResponse::class.java)
    }
}
