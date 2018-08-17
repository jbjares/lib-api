package de.difuture.ekut.pht.lib.runtime


/**
 * Exception intended to be thrown by [IDockerClient] implementations.
 *
 * @author Lukas Zimmermann
 * @see IDockerClient
 * @since 0.0.1
 *
 */
open class DockerClientException(msg : String?) : Exception(msg) {

    constructor(ex : Exception) : this(ex.message)
}


/**
 * Exception to be thrown when the selected Docker Container does not exits.
 *
 * Typical methods that use this exception are [IDockerClient.rm] and [IDockerClient.commit].
 *
 * @author Lukas Zimmermann
 * @see IDockerClient
 * @since 0.0.1
 *
 */
data class NoSuchDockerContainerException(val msg : String?) : DockerClientException(msg) {

    constructor(ex : Exception) : this(ex.message)
}


/**
 * Exception to be thrown when the selected Docker Image does not exist.
 *
 * Typical methods that use this exception are and [IDockerClient.run].
 *
 * @author Lukas Zimmermann
 * @see IDockerClient
 * @since 0.0.1
 *
 */
data class NoSuchDockerImageException(val msg : String?) : DockerClientException(msg) {

    constructor(ex : Exception) : this(ex.message)
}


/**
 * Exception to tbe thrown when the selected repository does not exits.
 *
 * Typically thrown by[IDockerClient.pull].
 *
 * @author Lukas Zimmermann
 * @see IDockerClient
 * @since 0.0.1
 *
 */
data class NoSuchDockerRepositoryException(val msg : String?) : DockerClientException(msg) {

    constructor(ex : Exception) : this(ex.message)
}


/**
 * Exception that can be thrown to signal that the creation of a Docker container has failed
 *
 * Exception can be used for [IDockerClient.run]
 *
 * @author Lukas Zimmermann
 * @see IDockerClient
 * @since 0.0.1
 *
 */
data class CreateDockerContainerFailedException(val msg: String?) : DockerClientException(msg) {

    constructor(ex : Exception) : this(ex.message)
}
