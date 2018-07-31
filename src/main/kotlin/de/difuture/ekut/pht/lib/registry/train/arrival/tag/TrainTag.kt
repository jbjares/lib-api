package de.difuture.ekut.pht.lib.registry.train.arrival.tag

import de.difuture.ekut.pht.lib.common.StringRepresentable
import de.difuture.ekut.pht.lib.common.docker.DockerTag

interface TrainTag : StringRepresentable {

    override val stringRepresentation : String

    private data class GenericTrainTag(override val stringRepresentation: String) : TrainTag

    companion object {

        fun of(value : String) : TrainTag =

                if (SpecialTrainTag.isMember(value)) {
                    SpecialTrainTag.valueOf(value)
                } else {
                    GenericTrainTag(value)
                }
        fun of(tag : DockerTag) : TrainTag = of(tag.value)
    }
}
