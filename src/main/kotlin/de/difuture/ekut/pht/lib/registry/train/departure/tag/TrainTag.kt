package de.difuture.ekut.pht.lib.registry.train.departure.tag

interface TrainTag {

    val stringRepresentation : String

    private data class GenericTrainTag(override val stringRepresentation: String) : TrainTag

    companion object {

        fun of(value : String) : TrainTag =

                if (SpecialTrainTag.isMember(value)) {
                    SpecialTrainTag.valueOf(value.toUpperCase())
                } else {
                    GenericTrainTag(value)
                }
    }
}
