package de.difuture.ekut.pht.lib.train.api.command

/**
 * A formal command that can be applied to a Train.
 *
 * @param A Return type after the command has been applied to a train
 *
 * @author Lukas Zimmermann
 * @since 0.0.3
 *
 */
interface TrainCommand<A> {

    /**
     * The unique name of the command
     */
    val name: String

    /**
     * The return type of the train command, represented as Java class
     */
    val returnType: Class<A>
}
