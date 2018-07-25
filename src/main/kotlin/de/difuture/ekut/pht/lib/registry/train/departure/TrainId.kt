package de.difuture.ekut.pht.lib.registry.train.departure

data class TrainId (

        val value : String
) {

    companion object {

        // The regex that we allow for potential values of the trainId
        val regex = Regex("[a-zA-Z][a-zA-Z0-9_-]*")
    }

    init {

        // Validate that the TrainId satisfies the requirement for the string
        require(regex.matches(this.value)) {

            "String for generating the TrainId does not match the required regular expression."
        }
    }
}
