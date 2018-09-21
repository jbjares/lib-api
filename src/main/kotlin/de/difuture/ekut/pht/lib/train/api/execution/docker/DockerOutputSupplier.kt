package de.difuture.ekut.pht.lib.train.api.execution.docker

import de.difuture.ekut.pht.lib.data.DockerContainerOutput
import java.util.function.Supplier

class DockerOutputSupplier<T>(
    val output: DockerContainerOutput,
    private val extractor: (DockerContainerOutput) -> T
) : Supplier<T> {

    override fun get() = this.extractor(this.output)
}
