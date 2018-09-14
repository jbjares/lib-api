package de.difuture.ekut.pht.lib.train.api.command

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * The "Check Requirements" command for a Train.
 *
 * This command can only be applied to Train Arrivals.
 *
 * @author Lukas Zimmermann
 * @see ArrivalCommand
 * @since 0.0.3
 *
 */
object CheckRequirements : ArrivalCommand<Boolean> {

    @JsonProperty("returnType")
    override val returnType = Boolean::class.java

    @JsonProperty("name")
    override val name = "check_requirements"
}
