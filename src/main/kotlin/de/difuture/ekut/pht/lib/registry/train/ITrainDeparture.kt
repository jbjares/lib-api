package de.difuture.ekut.pht.lib.registry.train.departure

import de.difuture.ekut.pht.lib.registry.train.ITrainInterface
import de.difuture.ekut.pht.lib.runtime.IRuntimeClient

interface ITrainDeparture<A : IRuntimeClient> : ITrainInterface<A> {

    fun print_train_tag(client : A, timeout : Int) : String
}
