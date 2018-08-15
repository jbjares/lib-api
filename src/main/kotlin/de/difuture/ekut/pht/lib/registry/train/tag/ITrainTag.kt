package de.difuture.ekut.pht.lib.registry.train.tag

import de.difuture.ekut.pht.lib.common.ICanonicalStringRepresentable
import de.difuture.ekut.pht.lib.common.containsWhitespace
import de.difuture.ekut.pht.lib.common.docker.DockerTag


/**
 * Represents the Tag of a Train.
 *
 * @author Lukas Zimmermann
 * @see SpecialTrainTag
 * @since 0.0.1
 *
 */
interface ITrainTag : ICanonicalStringRepresentable {

    private data class GenericTrainTag(override val repr: String) : ITrainTag

    companion object {

        /**
         * Creates a [ITrainTag] of the provided [String] value
         *
         * @param value The value that should be converted to an [ITrainTag] instance
         * @return The [ITrainTag] from this [String]
         */
        fun of(value : String) : ITrainTag {

            require(value.isNotBlank())
            require( ! value.containsWhitespace())
            return when {
                SpecialTrainTag.isMember(value) -> SpecialTrainTag.valueOf(value.toUpperCase())
                ModeTrainTag.isMember(value) -> ModeTrainTag.valueOf(value.toUpperCase())
                else -> GenericTrainTag(value)
            }
        }

        /**
         * Converts a [DockerTag] to a [ITrainTag] instance
         *
         * @param tag The [DockerTag] that needs to be converted to the [ITrainTag].
         * @return The [ITrainTag] instance that results from this [DockerTag]
         *
         */
        fun of(tag : DockerTag) = of(tag.repr)
    }
}
