package de.difuture.ekut.pht.lib.train

import jdregistry.client.data.DockerTag

/**
 * Represents the Tag of a Train.
 *
 * @author Lukas Zimmermann
 * @see SpecialTrainTag
 * @since 0.0.1
 *
 */
interface TrainTag {

    val repr: String

    private data class GenericTrainTag(override val repr: String) : TrainTag

    enum class Mode(override val repr: String) : TrainTag {

        IMMEDIATE("immediate")
    }

    enum class Special(override val repr: String) : TrainTag {

        TEST("test")
    }

    companion object {

        // Short-wire tags
        val IMMEDIATE = Mode.IMMEDIATE
        val TEST = Special.TEST

        /**
         * Creates a [TrainTag] of the provided [String] value
         *
         * @param value The value that should be converted to an [TrainTag] instance
         * @return The [TrainTag] from this [String]
         */
        fun of(value: String) = when (value) {

            IMMEDIATE.repr -> IMMEDIATE
            TEST.repr -> TEST
            else -> GenericTrainTag(value)
        }

        /**
         * Converts a [DockerTag] to a [TrainTag] instance
         *
         * @param tag The [DockerTag] that needs to be converted to the [TrainTag].
         * @return The [TrainTag] instance that results from this [DockerTag]
         *
         */
        fun of(tag: DockerTag) = of(tag.repr)
    }
}
