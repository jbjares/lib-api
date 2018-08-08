package de.difuture.ekut.pht.lib.registry.train.id

import de.difuture.ekut.pht.lib.common.ICanonicalStringRepresentable


/**
 * Represents the ID of a train.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 */
interface ITrainId : ICanonicalStringRepresentable {

    private data class GenericTrainId(override val repr: String) : ITrainId

    companion object {

        // The regex that we allow for potential values of the trainId
        private val regex = Regex("[a-zA-Z](?:[a-zA-Z0-9_-]*[a-z0-9])?")

        fun of(value : String) : ITrainId {

            require(value.matches(regex))
            return GenericTrainId(value)
        }
    }
}
