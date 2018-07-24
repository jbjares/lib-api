package de.difuture.ekut.pht.lib.registry.train.departure.tag

enum class SpecialTrainTag: TrainTag {

    IMMEDIATE,
    TEST;

    override val stringRepresentation = this.toString()

    companion object {
        private val names = SpecialTrainTag.values().map { it.toString() }.toSet()
    }

}
