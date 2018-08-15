package de.difuture.ekut.pht.lib.registry.train.api

import de.difuture.ekut.pht.lib.registry.train.tag.ModeTrainTag

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
)
