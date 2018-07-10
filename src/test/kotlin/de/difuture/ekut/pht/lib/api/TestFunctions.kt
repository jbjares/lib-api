package de.difuture.ekut.pht.lib.api

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Assert

fun <T> equalAfterSerialization(
        item: T, mapper: ObjectMapper, clazz : Class<T>) {

    val serialized = mapper.writeValueAsString(item)
    println(serialized)
    val item2 = mapper.readValue(serialized, clazz)

    Assert.assertEquals(item, item2)
}


fun <T> allEqualAfterSerialization(items : Iterable<T>, clazz : Class<T>) {

    val mapper = ObjectMapper()
    items.forEach {  equalAfterSerialization(it, mapper, clazz)}
}
