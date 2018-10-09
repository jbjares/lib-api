package de.difuture.ekut.pht.lib.train.serializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.node.TextNode
import de.difuture.ekut.pht.lib.train.TrainId
import java.io.IOException

class TrainIdDeserializer @JvmOverloads constructor(vc: Class<*>? = null) : StdDeserializer<TrainId>(vc) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): TrainId {
        val node = jp.codec.readTree<TextNode>(jp)
        return TrainId.of(node.asText())
    }
}
