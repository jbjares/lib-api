package de.difuture.ekut.pht.lib.runtime.docker

import de.difuture.ekut.pht.lib.common.docker.DockerImageId
import de.difuture.ekut.pht.lib.common.docker.DockerNetworkId
import de.difuture.ekut.pht.lib.runtime.IDockerContainerInterruptHandler

/**
 * Implementation of a [IDockerClient] that allows to set other default values of the
 * run parameters
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
class DockerRunClient(
    private val inner: IDockerClient,
    private val env: Map<String, String>? = null,
    private val networkId: DockerNetworkId? = null,
    private val warnings: MutableList<String>? = null,
    private val interruptHandler: IDockerContainerInterruptHandler? = null
) : IDockerClient by inner {

    override fun run(
        imageId: DockerImageId,
        commands: List<String>,
        rm: Boolean,
        env: Map<String, String>?,
        networkId: DockerNetworkId?,
        warnings: MutableList<String>?,
        interruptHandler: IDockerContainerInterruptHandler?
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
