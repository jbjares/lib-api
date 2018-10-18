package de.difuture.ekut.pht.lib.train.station

import de.difuture.ekut.pht.lib.runtime.RuntimeClient
import de.difuture.ekut.pht.lib.train.api.execution.ArrivalExecution
import de.difuture.ekut.pht.lib.train.api.execution.DepartureExecution
import de.difuture.ekut.pht.lib.train.api.interf.arrival.TrainArrival
import de.difuture.ekut.pht.lib.train.api.interf.departure.TrainDeparture
import de.difuture.ekut.pht.lib.train.api.output.TrainOutput
import de.difuture.ekut.pht.lib.train.api.output.TrainResponse

/**
 * A TrainStation is a client of the TrainAPI that consistently uses
 * the provided [RuntimeClient]. Instances of this class hence serve as an entrypoint
 * for running commands on train arrivals or departures or to generate new departures from
 * arrivals
 *
 * @author Lukas Zimmermann
 * @since 0.1.7
 *
 */
open class TrainStation<T : RuntimeClient>(

    protected open val client: T,
    protected open val stationInfo: StationInfo
) {

    /**
     * Executes an [ArrivalExecution] on an [TrainArrival]
     *
     * @param exec The [ArrivalExecution] that should be performed
     * @param arrival The [TrainArrival] that is the target of the execution
     *
     * @return The [TrainOutput] returned by executing the TrainExecution
     *
     */
    fun <A : TrainArrival, B : TrainResponse> execute(
        exec: ArrivalExecution<A, T, TrainOutput<B>, B>,
        arrival: A
    ) = exec.execArrival(arrival, this.client, this.stationInfo)

    /**
     * Executes on [DepartureExecution] on a [TrainDeparture].
     *
     * @param exec The [DepartureExecution] that should be applied on the [TrainDeparture]
     * @param departure The [TrainDeparture] that is the target of the execution
     *
     * @return The [TrainOutput] that is returned by the train execution
     *
     */
    fun <A : TrainDeparture<T>, B : TrainResponse> execute(
        exec: DepartureExecution<A, T, TrainOutput<B>, B>,
        departure: A
    ) = exec.execDeparture(departure, this.stationInfo)
}
