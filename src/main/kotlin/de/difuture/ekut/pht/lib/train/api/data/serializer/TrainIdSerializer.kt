package de.difuture.ekut.pht.lib.train.api.data.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import de.difuture.ekut.pht.lib.train.api.data.TrainId
import java.io.IOException

class TrainIdSerializer @JvmOverloads constructor(t: Class<TrainId>? = null) : StdSerializer<TrainId>(t) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun serialize(
        value: TrainId,
        jgen: JsonGenerator,
        provider: SerializerProvider
    ) {

        jgen.writeString(value.repr)
    }
}
