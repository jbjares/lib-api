package de.difuture.ekut.pht.lib.train

/**
 * Represents the ID of a train.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 */
interface TrainId {

    val repr: String

    private data class GenericTrainId(override val repr: String) : TrainId

    companion object {

        // The regex that we allow for potential values of the trainId
        // A train needs to start with the train_ prefix
        private val regex = Regex("train_[a-zA-Z](?:[a-zA-Z0-9_-]*[a-z0-9])?")

        fun of(value: String): TrainId {

            require(value.matches(regex))
            return GenericTrainId(value)
        }

        /**
         * Checks whether the given [String] value is a proper trainId
         *
         * @param value The [String] that should represent a [ITrainId]
         *
         * @return Whether the [String] denotes a valid trainId
         *
         */
        fun isValid(value: String): Boolean = value.matches(regex)
    }
}
