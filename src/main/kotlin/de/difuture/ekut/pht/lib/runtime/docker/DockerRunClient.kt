package de.difuture.ekut.pht.lib.runtime.docker

import de.difuture.ekut.pht.lib.data.DockerContainerId
import de.difuture.ekut.pht.lib.data.DockerImageId
import de.difuture.ekut.pht.lib.data.DockerNetworkId
import de.difuture.ekut.pht.lib.runtime.InterruptHandler

/**
 * Implementation of a [DockerClient] that allows to set other default values of the
 * run parameters if none a provided when run is executed. This is implemented as
 * a Decorator.
 *
 * @author Lukas Zimmermann
 * @see DockerClient
 * @since 0.0.1
 *
 */
class DockerRunClient(
    private val inner: DockerClient,
    private val env: Map<String, String>? = null,
    private val networkId: DockerNetworkId? = null,
    private val warnings: MutableList<String>? = null,
    private val interruptHandler: InterruptHandler<DockerContainerId>? = null
) : DockerClient by inner {

    override fun run(
        imageId: DockerImageId,
        commands: List<String>,
        rm: Boolean,
        env: Map<String, String>?,
        networkId: DockerNetworkId?,
        warnings: MutableList<String>?,
        interruptHandler: InterruptHandler<DockerContainerId>?
    ) =

            this.inner.run(
                imageId,
                commands,
                rm,
                env ?: this.env,
                networkId ?: this.networkId,
                warnings ?: this.warnings,
                interruptHandler ?: this.interruptHandler)
}
