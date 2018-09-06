package de.difuture.ekut.pht.lib.train


import de.difuture.ekut.pht.lib.http.TestHttpClient
import de.difuture.ekut.pht.lib.train.id.ITrainId
import de.difuture.ekut.pht.lib.train.tag.SpecialTrainTag
import de.difuture.ekut.pht.lib.train.tag.ITrainTag
import de.difuture.ekut.pht.lib.train.tag.ModeTrainTag
import de.difuture.ekut.pht.test.lib.SingleExposedPortContainer
import de.difuture.ekut.pht.test.lib.TEST_TRAIN_REGISTRY_REPOSITORY
import jdregistry.client.DockerRegistryGetClient
import org.junit.ClassRule
import org.junit.Test
import org.junit.Assert
import org.junit.Before


class DefaultTrainRegistryClientTests {

    /////////////////////////  Companion  //////////////////////////////////////////////////////////////
    companion object {

        // Container that is used for fetching Docker Registry Notifications
        @ClassRule @JvmField
        val REGISTRY : SingleExposedPortContainer =
                SingleExposedPortContainer(
                        TEST_TRAIN_REGISTRY_REPOSITORY,
                        5000)
    }

    /////////////////////////  The registry client  /////////////////////////////////////////////////////////////
    private lateinit var client : DefaultTrainRegistryClient

    @Before fun before() {

        val drclient = DockerRegistryGetClient.of(REGISTRY.containerIpAddress, REGISTRY.mappedPort, TestHttpClient())
        this.client = DefaultTrainRegistryClient(drclient)
    }

    /////////////////////////  The actual test  //////////////////////////////////////////////////////////////////


    @Test fun test_list_train_arrivals() {

        val arrivals = this.client.listTrainArrivals()
        Assert.assertTrue(arrivals.size > 5)
    }

    @Test fun test_list_train_arrivals_tags() {

        val arrivalsImmediate= this.client.listTrainArrivals(ModeTrainTag.IMMEDIATE)
        val arrivalsTests = this.client.listTrainArrivals(SpecialTrainTag.TEST)

        //Assert.assertTrue(arrivalsImmediate.size > 1)
        Assert.assertTrue(arrivalsTests.size > 1)
        Assert.assertTrue(arrivalsImmediate.all { it.trainTag == ModeTrainTag.IMMEDIATE } )
        Assert.assertTrue(arrivalsTests.all { it.trainTag == SpecialTrainTag.TEST })
    }


    @Test fun test_list_train_arrivals_ids() {

        val id1 = ITrainId.of("train_test_print_summary_1")
        val id2 = ITrainId.of("train_test_print_summary_2")

        val arrivalsId1= this.client.listTrainArrivals(id1)
        val arrivalsId2 = this.client.listTrainArrivals(id2)

        //Assert.assertTrue(arrivalsImmediate.size > 1)
        Assert.assertTrue(arrivalsId1.isNotEmpty())
        Assert.assertTrue(arrivalsId2.isNotEmpty())
    }


    // Select the print_summary trains
    @Test fun select_print_summary_1() {

        val tag = ITrainTag.of("test")

        // These train arrivals should exist
        val arrival1 = this.client.getTrainArrival(
                ITrainId.of("train_test_print_summary_1"), tag)
        val arrival2 = this.client.getTrainArrival(
                ITrainId.of("train_test_print_summary_2"), tag)

        // These train arrivals do not exist
        val arrival3 = this.client.getTrainArrival(
                ITrainId.of("train_foobar"), tag)
        val arrival4 = this.client.getTrainArrival(
                ITrainId.of("train_sfsf"), tag)

        Assert.assertNotNull(arrival1)
        Assert.assertNotNull(arrival2)
        Assert.assertNull(arrival3)
        Assert.assertNull(arrival4)
    }


}
