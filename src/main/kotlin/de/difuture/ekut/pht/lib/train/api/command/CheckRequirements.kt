package de.difuture.ekut.pht.lib.train.api.command

import com.fasterxml.jackson.annotation.JsonProperty
import de.difuture.ekut.pht.lib.train.api.data.CheckRequirementsResponse

/**
 * The "Check Requirements" command for a Train.
 *
 * This command can only be applied to Train Arrivals.
 *
 * This command checks whether the payload has met all requirements when
 * submitting the train.
 *
 * @author Lukas Zimmermann
 * @see ArrivalCommand
 * @since 0.0.3
 *
 */
object CheckRequirements : ArrivalCommand<CheckRequirementsResponse> {

    @JsonProperty("returnType")
    override val returnType = CheckRequirementsResponse::class.java

    @JsonProperty("repoName")
    override val name = "check_requirements"
}
