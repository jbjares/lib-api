package de.difuture.ekut.pht.lib.registry.train.departure

import de.difuture.ekut.pht.lib.registry.train.ITrainRegistryClient
import de.difuture.ekut.pht.lib.registry.train.departure.tag.TrainTag
import de.difuture.ekut.pht.lib.runtime.Execution
import de.difuture.ekut.pht.lib.runtime.RuntimeClient

/**
 * Interface for Train Departures.
 *
 */
abstract class AbstractTrainDeparture<A : RuntimeClient>(

        val trainId: TrainId,
        val trainTag: TrainTag,
        client: ITrainRegistryClient<A>
) {
    // Convenience constructors
    constructor(trainId : String, trainTag: String, client: ITrainRegistryClient<A>)
            : this(TrainId(trainId), TrainTag.of(trainTag), client)

    constructor(trainId : TrainId, trainTag: String, client: ITrainRegistryClient<A>)
            : this(trainId, TrainTag.of(trainTag), client)

    constructor(trainId : String, trainTag: TrainTag, client: ITrainRegistryClient<A>)
            : this(TrainId(trainId), trainTag, client)

    //
    abstract fun runAlgorithm() : Execution<A>
    abstract fun printSummary() : Execution<A>
}
