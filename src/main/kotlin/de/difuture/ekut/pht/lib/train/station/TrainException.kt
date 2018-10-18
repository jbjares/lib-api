package de.difuture.ekut.pht.lib.train.station

import de.difuture.ekut.pht.lib.train.api.output.TrainOutput
import de.difuture.ekut.pht.lib.train.api.output.TrainResponse
import kotlin.Exception

sealed class TrainException(msg: String?) : Exception(msg) {

    /**
     * Exception to be thrown when the TrainOutput contains an error message
     */
    data class Output(val output: TrainOutput<*>) : TrainException(output.error)

    /**
     * Exception to be thrown when the 'success' field of the RunAlgorithmResponse failed
     */
    data class RunAlgorithmFailed(val output: TrainOutput<TrainResponse.RunAlgorithmResponse>)
        : TrainException(output.response?.message)
}
