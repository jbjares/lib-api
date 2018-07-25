package de.difuture.ekut.pht.lib.registry.train


import de.difuture.ekut.pht.lib.SingleExposedPortContainer
import de.difuture.ekut.pht.lib.http.TestHttpClient
import de.difuture.ekut.pht.lib.registry.docker.DockerRegistryClient
import de.difuture.ekut.pht.lib.registry.train.departure.tag.SpecialTrainTag
import org.junit.ClassRule
import org.junit.Test
import org.junit.Assert
import org.junit.Before


class TrainRegistryClientTests {

    /////////////////////////  Companion  //////////////////////////////////////////////////////////////
    companion object {

        // Trains to be tested
        private const val TRAIN_HOSTNAME = "test_hostname"
        private const val TRAIN_PRINT_HELLO_WORLD = "test_print_hello_world"

        // Container that is used for fetching Docker Registry Notifications
        @ClassRule @JvmField
        val REGISTRY : SingleExposedPortContainer =
                SingleExposedPortContainer(
                        5000,
                        "lukaszimmermann/pht-test-train-registry:latest")
    }

    /////////////////////////  The registry client  /////////////////////////////////////////////////////////////
    private lateinit var client : ITrainRegistryClient

    @Before
    fun before() {

        val dockerRegistryClient = DockerRegistryClient(REGISTRY.getExternalURI(), TestHttpClient())
        this.client = TrainRegistryClient(dockerRegistryClient)
    }

    @Test
    fun test_list_train_departures() {

        val departures = this.client.listTrainDepartures()
        Assert.assertTrue(departures.size > 5)
    }

    @Test
    fun test_list_train_departures_tags() {

        val departuresImmediate= this.client.listTrainDepartures(SpecialTrainTag.IMMEDIATE)
        val departuresTest = this.client.listTrainDepartures(SpecialTrainTag.TEST)

        Assert.assertTrue(departuresImmediate.size > 1)
        Assert.assertTrue(departuresTest.size > 1)
        Assert.assertTrue(departuresImmediate.all { it.trainTag == SpecialTrainTag.IMMEDIATE } )
        Assert.assertTrue(departuresTest.all { it.trainTag == SpecialTrainTag.TEST })
    }
}
