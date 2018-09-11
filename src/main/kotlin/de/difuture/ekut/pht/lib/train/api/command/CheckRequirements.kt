package de.difuture.ekut.pht.lib.train.api.command

import com.fasterxml.jackson.annotation.JsonProperty

object CheckRequirements : ArrivalCommand<Boolean> {

    @JsonProperty("returnType")
    override val returnType = Boolean::class.java

    @JsonProperty("name")
    override val name = "CHECK_REQUIREMENTS"
}
