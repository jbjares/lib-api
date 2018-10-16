package de.difuture.ekut.pht.lib.train.api

import de.difuture.ekut.pht.lib.train.TrainTag

/**
 *
 * Represents the set of information that is passed to the train during runtime.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
data class StationInfo(

    /**
     * The numeric station id
     */
    val stationId: Int,

    /**
     * The mode in which the train was executed
     */
    val mode: TrainTag.Mode
)
