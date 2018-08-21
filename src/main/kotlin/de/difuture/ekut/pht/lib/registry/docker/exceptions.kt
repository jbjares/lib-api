package de.difuture.ekut.pht.lib.registry.docker


/**
 * Indicates that the Response from the Docker Registry cannot be handled by the Docker Registry client
 *
 * @author Lukas Zimmermann
 * @see DefaultDockerRegistryClient
 * @since 0.0.1
 *
 */
data class UnhandledDockerRegistryResponseException(val msg: String) : Exception(msg)
