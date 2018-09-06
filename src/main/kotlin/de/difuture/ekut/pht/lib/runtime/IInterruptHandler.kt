package de.difuture.ekut.pht.lib.runtime

import de.difuture.ekut.pht.lib.common.docker.DockerContainerId

/**
 * Handler that can be passed to runtime commands to determine whether timeout has been reached.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
interface IInterruptHandler<A> {

    /**
     * Signals whether object has been interrupted
     *
     * @return Whether object [A] has been interrupted
     *
     */
    fun wasInterrupted(obj: A): Boolean

    /**
     * Performs action to handle the interrupt on the object A that has been interrupted
     *
     * @param obj Object for which the interrupt should be handled
     *
     */
    fun handleInterrupt(obj: A)
}

typealias IDockerContainerInterruptHandler = IInterruptHandler<DockerContainerId>
