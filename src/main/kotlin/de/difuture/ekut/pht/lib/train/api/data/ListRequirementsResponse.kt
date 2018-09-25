package de.difuture.ekut.pht.lib.train.api.data

import com.fasterxml.jackson.annotation.JsonProperty

data class ListRequirementsResponse(

    /**
     * The environment variables that the train depends on
     */
    @JsonProperty("requirements")
    val requirements: List<String>
)
