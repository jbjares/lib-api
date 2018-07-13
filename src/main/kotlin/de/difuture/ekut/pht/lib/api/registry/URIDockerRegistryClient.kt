package de.difuture.ekut.pht.lib.api.registry

import java.net.URI

interface URIDockerRegistryClient : DockerRegistryClient {

    val uri : URI
}
