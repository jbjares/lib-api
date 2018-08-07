package de.difuture.ekut.pht.lib.registry.train

import de.difuture.ekut.pht.lib.common.ICanonicalStringRepresentable


/**
 * Represents the ID of a train.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 */
data class TrainId  (

        override val canonicalStringRepresentation : String
): ICanonicalStringRepresentable {

    init {

        // Validate that the TrainId satisfies the requirement for the string
        require(canonicalStringRepresentation.matches(regex)) {

            "String for generating the TrainId does not match the required regular expression."
        }
    }
    companion object {

        // The regex that we allow for potential values of the trainId
        private val regex = Regex("[a-zA-Z](?:[a-zA-Z0-9_-]*[a-z0-9])?")
    }
}
