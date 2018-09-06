package de.difuture.ekut.pht.lib.trainregistry.tag

/**
 * Represents trainregistry tags with special semantics. This currently only includes the 'test' trainregistry
 * tag.
 *
 * @author Lukas Zimmermann
 * @see ITrainTag
 * @since 0.0.1
 *
 */
enum class SpecialTrainTag(override val repr: String) : ITrainTag {

    TEST("test");

    companion object {
        private val names = SpecialTrainTag.values().map { it.repr }.toSet()

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
