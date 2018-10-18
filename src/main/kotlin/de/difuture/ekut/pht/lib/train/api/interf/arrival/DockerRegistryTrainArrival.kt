package de.difuture.ekut.pht.lib.train.api.interf.arrival

import de.difuture.ekut.pht.lib.train.api.data.TrainId
import de.difuture.ekut.pht.lib.train.api.data.TrainTag
import jdregistry.client.data.DockerRepositoryName
import jdregistry.client.data.DockerTag

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
            if (repoName.hasMoreComponents) repoName[1] else repoName[0]
    )
}
