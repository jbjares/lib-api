package de.difuture.ekut.pht.lib.train.api

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import de.difuture.ekut.pht.lib.train.api.data.serializer.TrainCommandDeserializer
import de.difuture.ekut.pht.lib.train.api.data.serializer.TrainCommandSerializer
import de.difuture.ekut.pht.lib.train.station.StationInfo

/**
 * Enum of all the known Train Commands. Also specified how the are translated to a command line
 * call given the required [StationInfo].
 *
 * @author Lukas Zimmermann
 * @since 0.1.3
 *
 */
@JsonSerialize(using = TrainCommandSerializer::class)
@JsonDeserialize(using = TrainCommandDeserializer::class)
enum class TrainCommand(val repr: String) {

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
        fun of(repr: String): TrainCommand = when (repr) {

            CHECK_REQUIREMENTS.repr -> CHECK_REQUIREMENTS
            CHECK_REQUIREMENTS.toString() -> CHECK_REQUIREMENTS

            LIST_REQUIREMENTS.repr -> LIST_REQUIREMENTS
            LIST_REQUIREMENTS.toString() -> LIST_REQUIREMENTS

            PRINT_SUMMARY.repr -> PRINT_SUMMARY
            PRINT_SUMMARY.toString() -> PRINT_SUMMARY

            RUN_ALGORITHM.repr -> RUN_ALGORITHM
            RUN_ALGORITHM.toString() -> RUN_ALGORITHM
            else -> throw IllegalArgumentException("Not a Train Command: $repr")
        }
    }
}
