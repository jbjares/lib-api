package de.difuture.ekut.pht.lib.train.api

import de.difuture.ekut.pht.lib.train.TrainTag

/**
 *
 * Represents the set of information that is passed to the train durig runtime.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
data class StationInfo(

    val stationID: Int,
    val mode: TrainTag.Mode
) {

    /**
     * Encodes the content of [StationInfo] as trainCommand line parameters, as it would be passed to `data run`.
     *
     */
    val commandLine = listOf(

        "--stationid", stationID.toString(),
        "--mode", mode.repr
    )
}
