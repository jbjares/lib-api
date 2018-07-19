package de.difuture.ekut.pht.lib.id

data class DepartureId(

        val value : String
) {

    companion object {

        val regex = Regex("[a-zA-Z0-9]+")
    }

    init {
        // Validate that the TrainId satisfies the requirement for the string
        require(TrainId.regex.matches(this.value)) {

            "String for generating the DepartureId does not match the required regular expression."
        }
    }
}
