package de.difuture.ekut.pht.lib.train.api.output

import com.fasterxml.jackson.annotation.JsonProperty
import de.difuture.ekut.pht.lib.train.TrainTag

/**
 * Container class for responses that are produced by a train.
 *
 */
sealed class TrainResponse {

    data class CheckRequirementsResponse(

        @JsonProperty("unmet")
        val unmet: List<String>
    ) : TrainResponse()

    data class ListRequirementsResponse(

        @JsonProperty("requirements")
        val requirements: List<String>
    ) : TrainResponse()

    data class RunAlgorithmResponse(

        @JsonProperty("success")
        val success: Boolean,

        @JsonProperty("message")
        val message: String,

        @JsonProperty("nextTrainTag")
        val nextTrainTag: TrainTag

    ) : TrainResponse()

    data class PrintSummaryResponse(

        @JsonProperty("content")
        val content: String
    ) : TrainResponse()
}
