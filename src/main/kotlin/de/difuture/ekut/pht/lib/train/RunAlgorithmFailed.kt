package de.difuture.ekut.pht.lib.train

import de.difuture.ekut.pht.lib.runtime.docker.DockerContainerOutput

/**
 * Exception that is thrown when the execution of th run_algorithm fails and no
 * train Departure can be generated
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
data class RunAlgorithmFailed(val msg: String, val ontainerOutput: DockerContainerOutput) : Exception(msg)
