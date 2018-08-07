package de.difuture.ekut.pht.lib.runtime


/**
 * Top-most api of runtime clients implemented by the station.
 *
 * Represents the Client that will run trains at the station. Implementers of this api use
 * and depend on the resources offered by the infrastructure of the station. As an example, the
 * [IDockerClient] depends on the host system offering a running Docker daemon and the user being
 * able to communicate with the daemon.
 *
 * @author Lukas Zimmermann
 * @see IDockerClient
 * @since 0.0.1
 *
 */
interface IRuntimeClient : AutoCloseable
