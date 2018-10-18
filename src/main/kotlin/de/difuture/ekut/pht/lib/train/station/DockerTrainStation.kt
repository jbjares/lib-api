package de.difuture.ekut.pht.lib.train.station

import de.difuture.ekut.pht.lib.runtime.docker.DockerRuntimeClient
import de.difuture.ekut.pht.lib.train.api.execution.docker.RunAlgorithm
import de.difuture.ekut.pht.lib.train.api.interf.arrival.DockerRegistryTrainArrival
import de.difuture.ekut.pht.lib.train.api.interf.departure.DockerRegistryTrainDeparture
import jdregistry.client.data.DockerTag

class DockerTrainStation(

    override val client: DockerRuntimeClient,
    override val stationInfo: StationInfo
) : TrainStation<DockerRuntimeClient>(client, stationInfo) {

    fun departWithAlgorithm(arrival: DockerRegistryTrainArrival): DockerRegistryTrainDeparture {

        val output = RunAlgorithm.execArrival(arrival, this.client, this.stationInfo)
        val response = output.response

        // The Output has an error if there either is an error or the response is null
        if (output.error != null || response == null) {

            throw TrainException.Output(output)
        }

        if (!response.success) {

            throw TrainException.RunAlgorithmFailed(output)
        }
        val nextTrainTag = response.nextTrainTag

        // Now the container needs to be commited to create the imageId for the
        val imageId = this.client.commit(
                output.containerOutput.containerId,
                arrival.repoName,
                DockerTag.of(nextTrainTag.repr))

        // Return the train departure. The trainId is not going to change, the new TrainTag
        // is communicated via the nextTrainTag
        return DockerRegistryTrainDeparture(
                imageId,
                arrival.trainId,
                nextTrainTag,
                this.client)
    }
}
