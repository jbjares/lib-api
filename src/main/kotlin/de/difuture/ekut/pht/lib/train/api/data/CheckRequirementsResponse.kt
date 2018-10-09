package de.difuture.ekut.pht.lib.train.api.data

import com.fasterxml.jackson.annotation.JsonProperty

data class CheckRequirementsResponse(

    /**
     * Custom message that the train can communicate when the algorithm terminates
     * The train is not required to provide this message
     */
    @JsonProperty("message")
    val message: String?,

    /**
     * List of unmet dependencies. An unmet dependency is an environment variable
     * that the train could not find in the environment that is was provided by the payload.
     */
    @JsonProperty("unmet")
    val unmet: List<String>
)
