package de.difuture.ekut.pht.lib.train.api.interf.departure

import de.difuture.ekut.pht.lib.data.DockerImageId
import de.difuture.ekut.pht.lib.runtime.docker.DockerClient
import de.difuture.ekut.pht.lib.train.TrainId
import de.difuture.ekut.pht.lib.train.TrainTag

class DockerRegistryTrainDeparture(

    val imageId: DockerImageId,
    override val trainId: TrainId,
    override val trainTag: TrainTag,
    override val client: DockerClient
) : TrainDeparture<DockerClient> {

    override fun isValid() = imageId in client.images()
}
