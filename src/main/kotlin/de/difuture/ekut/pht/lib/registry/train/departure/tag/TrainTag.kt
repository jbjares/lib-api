package de.difuture.ekut.pht.lib.registry.train.departure.tag

interface TrainTag {

    val stringRepresentation : String

    private data class GenericTrainTag(override val stringRepresentation: String) : TrainTag

    companion object {

        fun of(value : String) : TrainTag =
                try {
                    SpecialTrainTag.valueOf(value)
                } catch (e : IllegalArgumentException) {

                    GenericTrainTag(value)
                }
    }
}
