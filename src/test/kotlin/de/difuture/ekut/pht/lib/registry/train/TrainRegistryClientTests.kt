package de.difuture.ekut.pht.lib.registry.train


import de.difuture.ekut.pht.lib.http.TestHttpClient
import de.difuture.ekut.pht.lib.registry.docker.DockerRegistryClient
import de.difuture.ekut.pht.lib.registry.train.arrival.tag.SpecialTrainTag
import de.difuture.ekut.pht.test.lib.SingleExposedPortContainer
import de.difuture.ekut.pht.test.lib.TEST_TRAIN_REGISTRY_REPOSITORY
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
                        TEST_TRAIN_REGISTRY_REPOSITORY,
                        5000)
    }

    /////////////////////////  The registry client  /////////////////////////////////////////////////////////////
    private lateinit var client : TrainRegistryClient

    @Before
    fun before() {

        val dockerRegistryClient = DockerRegistryClient(REGISTRY.getExternalURI(), TestHttpClient())
        this.client = TrainRegistryClient(dockerRegistryClient)
    }

    /////////////////////////  The actual test  //////////////////////////////////////////////////////////////////


    @Test
    fun test_list_train_arrivals() {

        val arrivals = this.client.listTrainArrivals()
        Assert.assertTrue(arrivals.size > 5)
    }

    @Test
    fun test_list_train_arrivals_tags() {

        val arrivalsImmediate= this.client.listTrainArrivals(SpecialTrainTag.IMMEDIATE)
        val arrivalsTests = this.client.listTrainArrivals(SpecialTrainTag.TEST)

        Assert.assertTrue(arrivalsImmediate.size > 1)
        Assert.assertTrue(arrivalsTests.size > 1)
        Assert.assertTrue(arrivalsImmediate.all { it.trainTag == SpecialTrainTag.IMMEDIATE } )
        Assert.assertTrue(arrivalsTests.all { it.trainTag == SpecialTrainTag.TEST })
    }
}
