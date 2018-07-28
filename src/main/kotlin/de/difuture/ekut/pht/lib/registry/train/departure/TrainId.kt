package de.difuture.ekut.pht.lib.registry.train.departure

import de.difuture.ekut.pht.lib.common.StringRepresentable

data class TrainId  (

        override val stringRepresentation : String
): StringRepresentable {

    companion object {

        // The regex that we allow for potential values of the trainId
        val regex = Regex("[a-zA-Z][a-zA-Z0-9_-]*")
    }

    init {

        // Validate that the TrainId satisfies the requirement for the string
        require(regex.matches(this.stringRepresentation)) {

            "String for generating the TrainId does not match the required regular expression."
        }
    }
}
