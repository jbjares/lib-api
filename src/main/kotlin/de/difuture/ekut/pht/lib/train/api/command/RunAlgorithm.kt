package de.difuture.ekut.pht.lib.train.api.command

import com.fasterxml.jackson.annotation.JsonProperty
import de.difuture.ekut.pht.lib.train.api.data.RunAlgorithmResponse

/**
 * The "Run Algorithm" command for a Train.
 *
 * This command can only be applied to Train Arrivals.
 *
 * @author Lukas Zimmermann
 * @see ArrivalCommand
 * @since 0.0.3
 *
 */
object RunAlgorithm : ArrivalCommand<RunAlgorithmResponse> {

    @JsonProperty("returnType")
    override val returnType = RunAlgorithmResponse::class.java

    @JsonProperty("name")
    override val name = "run_algorithm"
}
