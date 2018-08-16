package de.difuture.ekut.pht.lib.runtime

import de.difuture.ekut.pht.lib.common.docker.*

/**
 * Docker client api that a station needs to implement for using the library components.
 *
 * Interface for implementing Docker Clients that can be used for Train interfaces. The api
 * was designed with two key considerations in mind:
 * * The api represents the minimal set of operations the implementing Docker client needs to support to be used
 *   in a PHT setting.
 * * Each method reflects an operation and a subset of the corresponding options of the docker CLI.
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
interface IDockerClient : IRuntimeClient {

    /**
     * Runs (create and start) the Docker image with the given commands, waits for the resulting container to exit.
     *
     * This method reflects a simplified version of the `docker run` command, which only supports specifying
     * the commands to be passed to the Docker image and optionally whether the exited container should be removed
     * after it has exited (reflecting the `--rm` option of `docker run`).
     *
     * *Contract:* If the container exits, the statusCode code has to be reflected in the returned [DockerContainerOutput]
     * object. If something prevents an exited container to be created, the method needs to fail by throwing an
     * exception. Specifically, if the specified Docker Image does not exist, the method should throw
     * [NoSuchDockerImageException].
     *
     * @param imageId The Image ID of the Docker Image that should be run.
     * @param commands The list of Strings that is passed to the image.
     * @param rm Whether the Docker client should remove the container after it has been exited.
     * @param env The environment variables that should be made available to the container
     * @param networkId The [DockerNetworkId] that the run container will attach to.
     *
     * @return [DockerContainerOutput] object describing the output of the Docker run command.
     *
     */
    fun run(imageId : DockerImageId,
            commands : List<String>,
            rm : Boolean,
            env : Map<String, String>? = null,
            networkId : DockerNetworkId? = null,
            warnings : MutableList<String>? = null) : DockerContainerOutput

    /**
     * Removes container with specified ID.
     *
     * This command resembles the `docker rm` command.
     *
     * *Contract:* If something prevents the container to be removed, the method needs to fail by throwing an exception.
     *  Specifically, if the specified container does not exist,
     *  the method should throw [NoSuchDockerContainerException].
     *
     * @param containerId The [DockerContainerId] of the container to be removed.
     *
     */
    fun rm(containerId : DockerContainerId)

    /**
     * Pulls the repository specified by [DockerRepositoryName] and [DockerTag].
     *
     * Resembles the `docker pull` command. Unlike the Docker CLI, the tag `latest` is never implied, as
     * it does not bear any meaning in the PHT context. Hence, the tag is a required parameter.
     *
     * *Contract:* If the targeted image cannot be created locally for some reason, the method needs to fail by throwing
     * and exception. Note that trying to pull an image which already exists locally is never a failure. This is
     * compatible with the `docker pull` command. Furthermore, if the selected repository and tag do not point to a
     * valid Docker image, the method should throw [NoSuchDockerRepositoryException].
     *
     * @param repo The [DockerRepositoryName] of the repository to be pulled.
     * @param tag The [DockerTag] of the repository to be pulled.
     *
     * @return [DockerImageId] of the image retrieved via pulling.
     *
     */
    fun pull(repo : DockerRepositoryName, tag: DockerTag) : DockerImageId

    /**
     * Pushes the specified docker image via the provided [DockerRepositoryName] and [DockerTag].
     *
     * Resembles the `docker push` command. Unlike the Docker CLI, the tag `latest` is never implied, as it does not
     * bear any meaning in the PHT context. Hence, the tag is a required parameter.
     *
     * *Contract:* If anything prevents the image to be pushed to the registry (like networking errors), the
     * method needs to fail by throwing an exception. Pushing to a repository that already exits and would not
     * be updated via push is never a failure. This is compatible with the `docker push` command.
     *
     * @param repo The [DockerRepositoryName] that should be pushed to.
     * @param tag The [DockerTag] that should be pushed to.
     *
     */
    fun push(repo : DockerRepositoryName, tag : DockerTag)

    /**
     * Commits the Docker container and creates new image.
     *
     * Resembles the `docker commit` command.
     *
     * *Contract:* If the container selected via the [DockerContainerId] parameter does not exit, the method
     * should throw an [NoSuchDockerContainerException]. Otherwise, if anything else the prevents the target repo
     * to be created, the method should also throw an exception.
     *
     * @param containerId The [DockerContainerId] of the container to be commited.
     * @param targetRepo The [DockerRepositoryName] of the Docker repository to commit to.
     * @param targetTag The [DockerTag] that the resulting image should be tagged with.
     * @param author
     * @return The [DockerImageId] that points to the newly created image.
     *
     */
    fun commit(containerId: DockerContainerId,
               targetRepo: DockerRepositoryName,
               targetTag: DockerTag,
               author : String? = null,
               comment : String? = null) : DockerImageId

    /**
     * Lists the [DockerImageId] that this [IDockerClient] has access to.
     *
     * Resembles the `docker images -q` command.
     *
     * *Contract:* The method should fail by throwing an exception if something prevents listing the available
     * images.
     *
     * @return The list of [DockerImageId] that this [IDockerClient] has access to.
     *
     */
    fun images() : List<DockerImageId>
}
