package de.difuture.ekut.pht.lib.train.tag

/**
 * Represents train tags that encode the mode of a train. Currently, only Immediate is supported.
 *
 * @author Lukas Zimmermann
 * @see ITrainTag
 * @since 0.0.1
 *
 */
enum class ModeTrainTag(override val repr: String) : ITrainTag {

    IMMEDIATE("immediate");

    companion object {
        private val names = ModeTrainTag.values().map { it.repr }.toSet()

        /**
         *  Tests whether the [String] value is a representation of a [SpecialTrainTag]
         *
         *  @param value The [String] value to be tested
         *  @return Whether the provided value represents a [SpecialTrainTag]
         *
         */
        fun isMember(value: String) = value in names
    }
}
