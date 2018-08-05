package de.difuture.ekut.pht.lib.common


/**
 * Interface denoting objects that can be represented as a [String]. This is different
 * from [toString], since the returned String is not meant to be a human readable representation of the
 * object, but rather the canonical String representation of the object.
 *
 * @author Lukas Zimmermann
 *
 */
interface CanonicalStringRepresentable {

    /**
     * The canonical String representation of the object
     */
    val canonicalStringRepresentation : String
}
