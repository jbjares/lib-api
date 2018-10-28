package de.difuture.ekut.pht.lib.train.api.interf.arrival

import de.difuture.ekut.pht.lib.train.api.data.TrainId
import de.difuture.ekut.pht.lib.train.api.data.TrainTag
import jdregistry.client.data.RepositoryName as DockerRepositoryName
import jdregistry.client.data.Tag as DockerTag

/**
 * A [TrainArrival] as they are available from Docker Registries.
 *
 * For a Docker Registry, a Train Arrival is identified by the [DockerRepositoryName]
 * and the associated [DockerTag].
 *
 * @author Lukas Zimmermann
 * @since 0.0.3
 *
 */
data class DockerRegistryTrainArrival(
    val host: String,
    val port: Int,
    val repoName: DockerRepositoryName,
    val dockerTag: DockerTag
) : TrainArrival {

    override val trainTag = TrainTag.of(dockerTag)

    // If the the second component exists, it is the trainID and the first
    // is the namespace. Else, the first component is the namespace
    override val trainId = TrainId.of(
            if (repoName.list.size > 1) repoName.list[1].repr else repoName.list[0].repr
    )
}
