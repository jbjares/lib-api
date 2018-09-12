package de.difuture.ekut.pht.lib.train.api.interf.departure

import de.difuture.ekut.pht.lib.common.docker.DockerImageId
import de.difuture.ekut.pht.lib.runtime.docker.IDockerClient
import de.difuture.ekut.pht.lib.train.id.ITrainId
import de.difuture.ekut.pht.lib.train.tag.ITrainTag

class DockerRegistryTrainDeparture(

        val imageId : DockerImageId,
        override val trainId: ITrainId,
        override val trainTag: ITrainTag,
        override val client: IDockerClient
) : ITrainDeparture<IDockerClient>
