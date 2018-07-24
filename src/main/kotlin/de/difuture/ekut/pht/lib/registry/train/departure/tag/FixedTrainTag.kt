package de.difuture.ekut.pht.lib.registry.train.departure.tag

enum class FixedTrainTag(override val stringRepresentation: String) : TrainTag {

    /**
     * Tag that is solely used for departures that are used for testing the train
     * infrastructure
     *
     */
    TEST("TEST"),

    /**
     * Tag that signals to stations that the departure should be processed immediately, without
     * prior inspection of the train config
     *
     */
    IMMEDIATE("IMMEDIATE")
}
