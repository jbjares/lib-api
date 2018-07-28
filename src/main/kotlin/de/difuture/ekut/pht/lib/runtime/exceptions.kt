package de.difuture.ekut.pht.lib.runtime

data class DockerClientException(override val message : String) : Exception(message)
