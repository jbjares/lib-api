package de.difuture.ekut.pht.lib.train.api.output

import com.fasterxml.jackson.annotation.JsonProperty
import de.difuture.ekut.pht.lib.data.DockerContainerOutput

/**
 * The output that is extracted from the an exited train instance
 *
 * @author Lukas Zimmermann
 * @since 0.1.3
 *
 */
sealed class TrainOutput(
        open val response: TrainResponse?,
        open val error: String?
) {

    data class DockerTrainOutput(
            @JsonProperty("response")
            override val response: TrainResponse?,

            @JsonProperty("error")
            override val error: String,

            @JsonProperty("containerOutput")
            val containerOutput: DockerContainerOutput

    ) : TrainOutput(response, error)
}
