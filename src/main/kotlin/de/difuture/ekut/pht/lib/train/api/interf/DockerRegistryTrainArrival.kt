package de.difuture.ekut.pht.lib.train.api.interf

import de.difuture.ekut.pht.lib.common.docker.DockerRepositoryName
import de.difuture.ekut.pht.lib.common.docker.DockerTag
import de.difuture.ekut.pht.lib.runtime.docker.IDockerClient
import de.difuture.ekut.pht.lib.train.api.StationInfo
import de.difuture.ekut.pht.lib.train.id.ITrainId
import de.difuture.ekut.pht.lib.train.tag.ITrainTag


/**
 * A [ITrainArrival] as they are available from Docker Registries
 *
 * @author Lukas Zimmermann
 * @since 0.0.3
 *
 */
data class DockerRegistryTrainArrival(
        val name: DockerRepositoryName,
        val tag: DockerTag
) : ITrainArrival<IDockerClient> {


    override fun runAlgorithm(client: IDockerClient, info: StationInfo): ITrainDeparture<IDockerClient> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val trainTag = ITrainTag.of(tag.repr)

    // If the the second component exists, it is the trainID and the first
    // is the namespace. Else, the first component is the namespace
    override val trainId = name.component2?.let { ITrainId.of(it) } ?: ITrainId.of(name.component1)


}

