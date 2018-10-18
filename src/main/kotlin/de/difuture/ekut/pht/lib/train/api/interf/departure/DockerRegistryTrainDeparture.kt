package de.difuture.ekut.pht.lib.train.api.interf.departure

import de.difuture.ekut.pht.lib.data.DockerContainerId
import de.difuture.ekut.pht.lib.data.DockerImageId
import de.difuture.ekut.pht.lib.runtime.docker.DockerRuntimeClient
import de.difuture.ekut.pht.lib.train.api.data.TrainId
import de.difuture.ekut.pht.lib.train.api.data.TrainTag

class DockerRegistryTrainDeparture(

    val imageId: DockerImageId,
    override val trainId: TrainId,
    override val trainTag: TrainTag,
    override val client: DockerRuntimeClient
) : TrainDeparture<DockerRuntimeClient> {

    /**
     * The [DockerRegistryTrainDeparture] is valid if the client can find the
     * the [DockerContainerId].
     *
     */
    override fun isValid() = imageId in client.images()
}
