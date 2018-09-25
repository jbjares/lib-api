package de.difuture.ekut.pht.lib.train.api.data

import com.fasterxml.jackson.annotation.JsonProperty
import de.difuture.ekut.pht.lib.train.TrainTag

data class RunAlgorithmResponse(

    /**
     * Custom message that the train can communicate when the algorithm terminates.
     * The train is not enforced to provide this message
     */
    @JsonProperty("message")
    val message: String?,

    /**
     * Whether the of the algorithm was successful.
     */
    @JsonProperty("success")
    val success: Boolean,

    /**
     * The trainTag that the newly generated Train Image should be published under.
     */
    @JsonProperty("nextTrainTag")
    val nextTrainTag: TrainTag
)
