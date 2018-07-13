package de.difuture.ekut.pht.lib.api.registry

import java.net.URI

fun federatedClient(vararg clients : URIDockerRegistryClient) : FederatedDockerRegistryClient<URI> =

    DefaultFederatedDockerRegistryClient(clients.map { it.uri to it}.toMap())

