package de.difuture.ekut.pht.lib.train.api.command

import com.fasterxml.jackson.annotation.JsonProperty
import de.difuture.ekut.pht.lib.train.api.data.ListRequirementsResponse

/**
 * The "List Requirements" command for a Train.
 *
 * This command can only be applied to Train Arrivals.
 *
 *
 *
 * @author Lukas Zimmermann
 * @see ArrivalCommand
 * @since 0.0.3
 *
 */
object ListRequirements : ArrivalCommand<ListRequirementsResponse> {

    @JsonProperty("returnType")
    override val returnType = ListRequirementsResponse::class.java

    @JsonProperty("repoName")
    override val name = "list_requirements"
}
