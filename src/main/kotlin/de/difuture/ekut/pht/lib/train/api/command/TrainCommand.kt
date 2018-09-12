package de.difuture.ekut.pht.lib.train.api.command

/**
 * A formal Train Command that can be applied to a Train.
 *
 * Currently a Train command consists of a [name] represented as [String], which will
 * identical to the [String] that needs to be provided on the command line.
 * Furthermore each [TrainCommand] specifies the canonical [returnType]
 * that is expected after the command that has been applied to the train. This is currently
 * simply represented as a Java class.
 *
 * @param A Return type after the trainCommand has been applied to a train
 *
 * @author Lukas Zimmermann
 * @see ArrivalCommand
 * @see DepartureCommand
 * @since 0.0.3
 *
 */
interface TrainCommand<A> {

    /**
     * The unique name of the Train Command.
     */
    val name: String

    /**
     * The return type of the train trainCommand, represented as Java class
     */
    val returnType: Class<A>
}
