package de.difuture.ekut.pht.lib.registry.train.tag


/**
 * Represents train tags with special semantics.
 *
 * @author Lukas Zimmermann
 * @see ITrainTag
 * @since 0.0.1
 *
 */
enum class SpecialTrainTag(override val canonicalStringRepresentation: String): ITrainTag {

    IMMEDIATE("immediate"),
    TEST("test");

    companion object {
        private val names = SpecialTrainTag.values().map { it.canonicalStringRepresentation }.toSet()

        /**
         *  Tests whether the [String] value is a representation of a [SpecialTrainTag]
         *
         *  @param value The [String] value to be tested
         *  @return Whether the provided value represents a [SpecialTrainTag]
         *
         */
        fun isMember(value : String) = value in names
    }
}
