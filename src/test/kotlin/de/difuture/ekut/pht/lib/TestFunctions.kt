package de.difuture.ekut.pht.lib

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Assert

fun <T> equalAfterSerialization(
        item: T, mapper: ObjectMapper, clazz : Class<T>) {

    val serialized = mapper.writeValueAsString(item)
    val item2 = mapper.readValue(serialized, clazz)
    Assert.assertEquals(item, item2)
}


fun <T> allEqualAfterSerialization(items : Iterable<T>, clazz : Class<T>) {

    val mapper = ObjectMapper()
    items.forEach { equalAfterSerialization(it, mapper, clazz) }
}


fun <A,B> cross(iter1 : Iterable<A>, iter2 : Iterable<B>) : Iterable<Pair<A,B>>
    = iter1.map { x -> iter2.map { y -> Pair(x,y) }  }.flatten()
