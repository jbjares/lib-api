package de.difuture.ekut.pht.lib.registry.train.departure

import de.difuture.ekut.pht.lib.SingleExposedPortContainer
import de.difuture.ekut.pht.lib.http.TestHttpClient
import de.difuture.ekut.pht.lib.registry.docker.DockerRegistryClient
import de.difuture.ekut.pht.lib.registry.train.TrainRegistryClient
import org.junit.Assert
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test


class DockerTrainDepartureTests {

    companion object {

        // The summary Trains
        private const val TRAIN_TEST_PRINT_SUMMARY_1 = "train_test_print_summary_1"
        private const val TRAIN_TEST_PRINT_SUMMARY_2 = "train_test_print_summary_2"

        // Container that is used for fetching Docker Registry Notifications
        @ClassRule @JvmField
        val REGISTRY : SingleExposedPortContainer =
                SingleExposedPortContainer(
                        5000,
                        "lukaszimmermann/pht-test-train-registry:latest")
    }


    /////////////////////////  The registry client  /////////////////////////////////////////////////////////////
    private lateinit var client : TrainRegistryClient

    @Before
    fun before() {

        val dockerRegistryClient = DockerRegistryClient(REGISTRY.getExternalURI(), TestHttpClient())
        this.client = TrainRegistryClient(dockerRegistryClient)
    }


    /////////////////////////  The test  //////////////////////////////////////////////////////////////////////


    // Tests that the print_summary test trains are there

    @Test
    fun test_summary_trains_are_present() {

        val trainDepartures = this.client.listTrainDepartures().map { it.trainId.stringRepresentation }.toSet()

        Assert.assertTrue(TRAIN_TEST_PRINT_SUMMARY_1 in trainDepartures)
        Assert.assertTrue(TRAIN_TEST_PRINT_SUMMARY_2 in trainDepartures)
    }
}
