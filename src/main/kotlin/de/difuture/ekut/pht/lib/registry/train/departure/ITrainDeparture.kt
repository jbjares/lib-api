package de.difuture.ekut.pht.lib.registry.train.departure

import de.difuture.ekut.pht.lib.registry.train.TrainRegistryClient
import de.difuture.ekut.pht.lib.registry.train.departure.tag.TrainTag

/**
 * Interface for Train Departures.
 *
 */
abstract class AbstractTrainDeparture(

        val trainId: TrainId,
        val trainTag: TrainTag,
        client : TrainRegistryClient
) {
    // Convenience constructors
    constructor(trainId : String, trainTag: String, client: TrainRegistryClient)
            : this(TrainId(trainId), TrainTag(trainTag), client)

}
