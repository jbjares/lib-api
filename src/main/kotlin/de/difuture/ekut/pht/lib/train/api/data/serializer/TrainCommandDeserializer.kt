package de.difuture.ekut.pht.lib.train.api.data.serializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.node.TextNode
import de.difuture.ekut.pht.lib.train.api.TrainCommand
import java.io.IOException

class TrainCommandDeserializer @JvmOverloads constructor(vc: Class<*>? = null) : StdDeserializer<TrainCommand>(vc) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): TrainCommand {
        val node = jp.codec.readTree<TextNode>(jp)
        return TrainCommand.of(node.asText())
    }
}
