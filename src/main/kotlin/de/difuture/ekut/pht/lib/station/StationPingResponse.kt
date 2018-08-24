package de.difuture.ekut.pht.lib.station

import com.fasterxml.jackson.annotation.JsonProperty

data class StationPingResponse (

        @JsonProperty("message") val status: String
)
