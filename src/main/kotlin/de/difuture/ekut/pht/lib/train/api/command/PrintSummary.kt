package de.difuture.ekut.pht.lib.train.api.command

import com.fasterxml.jackson.annotation.JsonProperty

object PrintSummary : ArrivalCommand<String>, DepartureCommand<String> {

    @JsonProperty("returnType") override val returnType = String::class.java
    @JsonProperty("name") override val name = "PRINT_SUMMARY"
}
