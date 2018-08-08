package de.difuture.ekut.pht.lib.common


/**
 * Interface denoting classes whose instances can be represented as a [String].
 *
 * This is different from [toString], since the canonical [String] is not meant to be a human readable
 * representation of the object, but rather the canonical [String] representation of the object as used in the
 * outside world. For example, the [String] "sft672r" would be the canonical [String] representation of
 * a [de.difuture.ekut.pht.lib.common.docker.DockerContainerId].
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
interface ICanonicalStringRepresentable {

    /**
     * The canonical String representation of the object
     */
    val repr : String
}
