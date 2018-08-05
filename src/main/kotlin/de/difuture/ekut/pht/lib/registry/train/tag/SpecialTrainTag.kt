package de.difuture.ekut.pht.lib.registry.train.tag

enum class SpecialTrainTag: TrainTag {

    IMMEDIATE,
    TEST;

    override val canonicalStringRepresentation = this.toString()

    companion object {
        private val names = SpecialTrainTag.values().map { it.toString() }.toSet()

        // Case insensitive
        fun isMember(value : String) = value in names
    }
}
