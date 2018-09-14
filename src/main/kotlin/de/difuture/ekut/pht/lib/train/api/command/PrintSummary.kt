package de.difuture.ekut.pht.lib.train.api.command

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * The "Print Summary" command for a Train.
 *
 * This command can be applied to both Train Arrivals and Train Departures
 *
 * @author Lukas Zimmermann
 * @see ArrivalCommand
 * @see DepartureCommand
 * @since 0.0.3
 *
 */
object PrintSummary : ArrivalCommand<String>, DepartureCommand<String> {

    @JsonProperty("returnType")
    override val returnType = String::class.java

    @JsonProperty("repoName")
    override val name = "print_summary"
}
