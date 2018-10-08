package de.difuture.ekut.pht.lib.train.api.data

import com.fasterxml.jackson.annotation.JsonProperty

data class TrainReponse<T>(

    @JsonProperty("response")
    val response: T?,

    @JsonProperty("trainStdout")
    val trainStdout: String,

    @JsonProperty("trainStderr")
    val trainStderr: String,

    @JsonProperty("error")
    val error: String?,

    @JsonProperty("message")
    val message: String?
)
