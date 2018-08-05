package de.difuture.ekut.pht.lib.registry.train.tag

import de.difuture.ekut.pht.lib.common.CanonicalStringRepresentable
import de.difuture.ekut.pht.lib.common.docker.DockerTag

interface TrainTag : CanonicalStringRepresentable {

    override val canonicalStringRepresentation : String

    private data class GenericTrainTag(override val canonicalStringRepresentation: String) : TrainTag

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
