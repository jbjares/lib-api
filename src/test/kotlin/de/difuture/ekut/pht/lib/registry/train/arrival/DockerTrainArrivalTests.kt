package de.difuture.ekut.pht.lib.registry.train.arrival

import de.difuture.ekut.pht.lib.http.TestHttpClient
import de.difuture.ekut.pht.lib.registry.docker.DefaultDockerRegistryClient
import de.difuture.ekut.pht.lib.registry.train.DefaultTrainRegistryClient
import de.difuture.ekut.pht.test.lib.SingleExposedPortContainer
import de.difuture.ekut.pht.test.lib.TEST_TRAIN_REGISTRY_REPOSITORY
import org.junit.Assert
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test


class DockerTrainArrivalTests {

    companion object {

        // The summary Trains
        private const val TRAIN_TEST_PRINT_SUMMARY_1 = "train_test_print_summary_1"
        private const val TRAIN_TEST_PRINT_SUMMARY_2 = "train_test_print_summary_2"

        // Container that is used for fetching Docker Registry Notifications
        @ClassRule @JvmField
        val REGISTRY : SingleExposedPortContainer =
                SingleExposedPortContainer(
                        TEST_TRAIN_REGISTRY_REPOSITORY,
                        5000)
    }


    /////////////////////////  The registry client  /////////////////////////////////////////////////////////////
    private lateinit var client : DefaultTrainRegistryClient

    @Before
    fun before() {

        val dockerRegistryClient = DefaultDockerRegistryClient(REGISTRY.getExternalURI(), TestHttpClient())
        this.client = DefaultTrainRegistryClient(dockerRegistryClient)
    }


    /////////////////////////  The test  //////////////////////////////////////////////////////////////////////


    // Tests that the print_summary test trains are there

    @Test
    fun test_summary_trains_are_present() {

        val trainArrivals = this.client.listTrainArrivals().map { it.trainId.repr }.toSet()

        Assert.assertTrue(TRAIN_TEST_PRINT_SUMMARY_1 in trainArrivals)
        Assert.assertTrue(TRAIN_TEST_PRINT_SUMMARY_2 in trainArrivals)
    }
}
