package de.difuture.ekut.pht.lib.registry.train.departure

import de.difuture.ekut.pht.lib.registry.train.ITrainRegistryClient
import de.difuture.ekut.pht.lib.registry.train.departure.tag.TrainTag

/**
 * Interface for Train Departures.
 *
 */
abstract class AbstractTrainDeparture(

        val trainId: TrainId,
        val trainTag: TrainTag,
        client: ITrainRegistryClient
) {
    // Convenience constructors
    constructor(trainId : String, trainTag: String, client: ITrainRegistryClient)
            : this(TrainId(trainId), TrainTag.of(trainTag), client)

    constructor(trainId : TrainId, trainTag: String, client: ITrainRegistryClient)
            : this(trainId, TrainTag.of(trainTag), client)

    constructor(trainId : String, trainTag: TrainTag, client: ITrainRegistryClient)
            : this(TrainId(trainId), trainTag, client)
}
