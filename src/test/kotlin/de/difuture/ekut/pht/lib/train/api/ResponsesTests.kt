package de.difuture.ekut.pht.lib.train.api

import de.difuture.ekut.pht.lib.allEqualAfterSerialization
import de.difuture.ekut.pht.lib.train.TrainTag
import de.difuture.ekut.pht.lib.train.api.output.TrainResponse
import org.junit.Test

class ResponsesTests {

    @Test
    fun equal_after_serialization_run_algorithm() {

        val responses = listOf(
                TrainResponse.RunAlgorithmResponse(success = true, nextTrainTag = TrainTag.of("foo")),
                TrainResponse.RunAlgorithmResponse(success = false, nextTrainTag = TrainTag.of("bar"))
        )
        allEqualAfterSerialization(responses, TrainResponse.RunAlgorithmResponse::class.java)
    }

    @Test
    fun equal_after_serialization_list_requirements() {

        val responses = listOf(
                TrainResponse.ListRequirementsResponse(emptyList()),
                TrainResponse.ListRequirementsResponse(listOf("PHT_TEST_REQUIREMENTS"))
        )
        allEqualAfterSerialization(responses, TrainResponse.ListRequirementsResponse::class.java)
    }

    @Test
    fun equal_after_serialization_check_requirements() {

        val responses = listOf(
                TrainResponse.CheckRequirementsResponse(emptyList()),
                TrainResponse.CheckRequirementsResponse(listOf("baz"))
        )
        allEqualAfterSerialization(responses, TrainResponse.CheckRequirementsResponse::class.java)
    }
}
