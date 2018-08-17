package de.difuture.ekut.pht.lib.runtime


/**
 * Handler that can be passed to runtime commands to determine whether timeout has been reached.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
interface ITimeoutHandler {

    /**
     * Signals whether timeout has been reached
     *
     * @return Whether timeout has been reached
     *
     */
    fun hasTimedOut(): Boolean
}
