package de.difuture.ekut.pht.lib.runtime.docker

import de.difuture.ekut.pht.lib.common.docker.DockerRepositoryName
import de.difuture.ekut.pht.lib.common.docker.DockerTag


fun IDockerClient.pullAndRun(
        name: DockerRepositoryName,
        tag: DockerTag,
        commands: List<String>,
        rm: Boolean) = this.run(this.pull(name, tag), commands, rm)
