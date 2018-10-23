package de.difuture.ekut.pht.lib.train.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.difuture.ekut.pht.lib.equalAfterSerialization
import org.junit.Test

class TrainCommandTests {

    @Test
    fun serialize_commands() {

        equalAfterSerialization(TrainCommand.RUN_ALGORITHM, jacksonObjectMapper(), TrainCommand::class.java)
        equalAfterSerialization(TrainCommand.PRINT_SUMMARY, jacksonObjectMapper(), TrainCommand::class.java)
        equalAfterSerialization(TrainCommand.LIST_REQUIREMENTS, jacksonObjectMapper(), TrainCommand::class.java)
        equalAfterSerialization(TrainCommand.CHECK_REQUIREMENTS, jacksonObjectMapper(), TrainCommand::class.java)
    }
}
