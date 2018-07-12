package de.difuture.ekut.pht.lib.api.station

import com.fasterxml.jackson.annotation.JsonProperty
import java.net.URI

data class StationPing(

        /**
         * The URI of this station. Communication with the station in the context of the
         * Personal Health Train infrastructure must be performed using this station URI.
         */
        @JsonProperty("uri") val uri : URI
)
