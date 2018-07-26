package de.difuture.ekut.pht.lib.runtime

interface Execution<T : RuntimeClient> {

    fun perform(client : T)
}
