package de.difuture.ekut.pht.lib.payload

import com.fasterxml.jackson.annotation.JsonProperty

data class StationPingResponse(

    @JsonProperty("status")
    val status: String
)
