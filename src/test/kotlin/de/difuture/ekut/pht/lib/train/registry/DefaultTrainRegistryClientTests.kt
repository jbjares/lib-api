package de.difuture.ekut.pht.lib.train.registry

import de.difuture.ekut.pht.lib.train.api.data.TrainId
import de.difuture.ekut.pht.lib.train.api.data.TrainTag
import de.difuture.ekut.pht.test.lib.SingleExposedPortContainer
import de.difuture.ekut.pht.test.lib.TEST_TRAIN_REGISTRY_REPOSITORY
import jdregistry.client.api.DockerRegistryGetClient
import jdregistry.client.impl.http.apache.ApacheHttpClient
import org.junit.Assert
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import java.net.URI

class DefaultTrainRegistryClientTests {

    // ///////////////////////  Companion  //////////////////////////////////////////////////////////////
    companion object {

        // Container that is used for fetching Docker Registry Notifications
        @ClassRule
        @JvmField
        val REGISTRY = SingleExposedPortContainer(TEST_TRAIN_REGISTRY_REPOSITORY, 5000)
    }

    // ///////////////////////  The registry client  /////////////////////////////////////////////////////////////
    private lateinit var client: DefaultTrainRegistryClient

    @Before
    fun before() {

        val drclient = DockerRegistryGetClient.of(
                URI.create("http://${REGISTRY.containerIpAddress}:${REGISTRY.mappedPort}"),
                ApacheHttpClient())
        this.client = DefaultTrainRegistryClient(drclient)
    }

    // ///////////////////////  The actual test  //////////////////////////////////////////////////////////////////

    @Test
    fun test_list_train_arrivals() {

        val arrivals = this.client.listTrainArrivals()
        Assert.assertTrue(arrivals.size > 5)
    }

    @Test fun test_list_train_arrivals_tags() {

        val arrivalsImmediate = this.client.listTrainArrivals { it.trainTag == TrainTag.IMMEDIATE }
        val arrivalsTests = this.client.listTrainArrivals { it.trainTag == TrainTag.TEST }

        // Assert.assertTrue(arrivalsImmediate.size > 1)
        Assert.assertTrue(arrivalsTests.size > 1)
        Assert.assertTrue(arrivalsImmediate.all { it.trainTag == TrainTag.IMMEDIATE })
        Assert.assertTrue(arrivalsTests.all { it.trainTag == TrainTag.TEST })
    }

    @Test fun test_list_train_arrivals_ids() {

        val id1 = TrainId.of("train_test_print_summary_1")
        val id2 = TrainId.of("train_test_print_summary_2")

        val arrivalsId1 = this.client.listTrainArrivals { it.trainId == id1 }
        val arrivalsId2 = this.client.listTrainArrivals { it.trainId == id2 }

        // Assert.assertTrue(arrivalsImmediate.size > 1)
        Assert.assertTrue(arrivalsId1.isNotEmpty())
        Assert.assertTrue(arrivalsId2.isNotEmpty())
    }

    // Select the print_summary trains
    @Test fun select_print_summary_1() {

        val tag = TrainTag.of("test")

        // These train arrivals should exist
        val arrival1 = this.client.listTrainArrivals {

            it.trainId == TrainId.of("train_test_print_summary_1") && it.trainTag == tag }

        val arrival2 = this.client.listTrainArrivals {

            it.trainId == TrainId.of("train_test_print_summary_2") && it.trainTag == tag }

        val arrival3 = this.client.listTrainArrivals {

            it.trainId == TrainId.of("train_foobar") && it.trainTag == tag }

        val arrival4 = this.client.listTrainArrivals {

            it.trainId == TrainId.of("train_sfsf") && it.trainTag == tag }

        Assert.assertTrue(arrival1.size == 1)
        Assert.assertTrue(arrival2.size == 1)
        Assert.assertTrue(arrival3.isEmpty())
        Assert.assertTrue(arrival4.isEmpty())
    }
}
