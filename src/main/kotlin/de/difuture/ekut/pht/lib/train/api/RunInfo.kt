package de.difuture.ekut.pht.lib.train.api

import de.difuture.ekut.pht.lib.train.tag.ModeTrainTag

/**
 *
 * Represents the set of information that is passed to the train durig runtime.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
data class RunInfo(

        val stationID : Int,
        val mode : ModeTrainTag
) {

    /**
     * Encodes the content of [RunInfo] as command line parameters, as it would be passed to `docker run`.
     *
     */
    val commandLine = listOf(

        "--stationid", stationID.toString(),
        "--mode", mode.repr
    )
}
