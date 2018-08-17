package de.difuture.ekut.pht.lib.runtime

import de.difuture.ekut.pht.lib.common.docker.DockerContainerId


/**
 * Handler that can be passed to runtime commands to determine whether timeout has been reached.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
interface ITimeoutHandler<A> {

    /**
     * Signals whether timeout has been reached
     *
     * @return Whether timeout has been reached
     *
     */
    fun hasTimedOut(obj: A): Boolean


    /**
     * Performs action to handle the timeout on the object A that has timed out
     *
     * @param obj Object for which the timeout should be handled
     *
     */
    fun handleTimeout(obj : A)
}

typealias IDockerContainerTimeoutHandler = ITimeoutHandler<DockerContainerId>
