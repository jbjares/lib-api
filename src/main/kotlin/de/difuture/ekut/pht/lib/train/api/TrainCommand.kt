package de.difuture.ekut.pht.lib.train.api

import com.fasterxml.jackson.annotation.JsonCreator
import de.difuture.ekut.pht.lib.train.station.StationInfo
import java.lang.IllegalArgumentException

/**
 * Enum of all the known Train Commands. Also specified how the are translated to a command line
 * call given the required [StationInfo].
 *
 * @author Lukas Zimmermann
 * @since 0.1.3
 *
 */
enum class TrainCommand(private val repr: String) {

    CHECK_REQUIREMENTS("check_requirements"),
    LIST_REQUIREMENTS("list_requirements"),
    PRINT_SUMMARY("print_summary"),
    RUN_ALGORITHM("run_algorithm");

    /**
     * Resolves the provided [StationInfo] on `this` [TrainCommand] to produce the list
     * of command line tokens
     *
     * @param info The [StationInfo] needed to produce the command line tokens
     * @return The list of command line tokens for `this` [TrainCommand]
     */
        fun resolveWith(info: StationInfo) = listOf(

            "--stationid", info.stationId.toString(),
            "--mode", info.mode.repr,
            this.repr
        )

    companion object {

        @JsonCreator
        fun of(repr: String) = when(repr) {

            CHECK_REQUIREMENTS.repr -> CHECK_REQUIREMENTS
            LIST_REQUIREMENTS.repr -> LIST_REQUIREMENTS
            PRINT_SUMMARY.repr -> PRINT_SUMMARY
            RUN_ALGORITHM.repr -> RUN_ALGORITHM
            else -> throw IllegalArgumentException("Not a Train Command: $repr")
        }
    }
}
