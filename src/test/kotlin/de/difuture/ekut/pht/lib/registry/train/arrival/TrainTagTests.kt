package de.difuture.ekut.pht.lib.registry.train.arrival

import de.difuture.ekut.pht.lib.common.docker.DockerTag
import de.difuture.ekut.pht.lib.registry.train.arrival.tag.SpecialTrainTag
import de.difuture.ekut.pht.lib.registry.train.arrival.tag.TrainTag
import org.junit.Assert
import org.junit.Test

class TrainTagTests {


    @Test
    fun special_train_tag_from_string() {

        // The special train tags
        val tag1 = TrainTag.of("test")
        val tag2 = TrainTag.of("immediate")
        val tag3 = TrainTag.of("TEST")
        val tag4 = TrainTag.of("IMMEDIATE")
        val tag5 = TrainTag.of("TesT")
        val tag6 = TrainTag.of("ImmEDIATE")

        Assert.assertEquals(SpecialTrainTag.TEST, tag1)
        Assert.assertEquals(SpecialTrainTag.IMMEDIATE, tag2)
        Assert.assertEquals(SpecialTrainTag.TEST, tag3)
        Assert.assertEquals(SpecialTrainTag.IMMEDIATE, tag4)
        Assert.assertEquals(SpecialTrainTag.TEST, tag5)
        Assert.assertEquals(SpecialTrainTag.IMMEDIATE, tag6)
    }

    @Test
    fun generic_train_tag_from_string() {

        val tag1 = TrainTag.of("foo")
        val tag2 = TrainTag.of("bar")
        val tag3 = TrainTag.of("baz")

        Assert.assertNotEquals(SpecialTrainTag.TEST, tag1)
        Assert.assertNotEquals(SpecialTrainTag.IMMEDIATE, tag1)
        Assert.assertNotEquals(SpecialTrainTag.TEST, tag2)
        Assert.assertNotEquals(SpecialTrainTag.IMMEDIATE, tag2)
        Assert.assertNotEquals(SpecialTrainTag.TEST, tag3)
        Assert.assertNotEquals(SpecialTrainTag.IMMEDIATE, tag3)
    }

    @Test
    fun special_train_tag_from_dockertag() {

        // The special train tags
        val tag1 = TrainTag.of(DockerTag("test"))
        val tag2 = TrainTag.of(DockerTag("immediate"))
        val tag3 = TrainTag.of(DockerTag("TEST"))
        val tag4 = TrainTag.of(DockerTag("IMMEDIATE"))
        val tag5 = TrainTag.of(DockerTag("TesT"))
        val tag6 = TrainTag.of(DockerTag("ImmEDIATE"))

        Assert.assertEquals(SpecialTrainTag.TEST, tag1)
        Assert.assertEquals(SpecialTrainTag.IMMEDIATE, tag2)
        Assert.assertEquals(SpecialTrainTag.TEST, tag3)
        Assert.assertEquals(SpecialTrainTag.IMMEDIATE, tag4)
        Assert.assertEquals(SpecialTrainTag.TEST, tag5)
        Assert.assertEquals(SpecialTrainTag.IMMEDIATE, tag6)
    }

    @Test
    fun generic_train_tag_from_dockertag() {

        val tag1 = TrainTag.of(DockerTag("foo"))
        val tag2 = TrainTag.of(DockerTag("bar"))
        val tag3 = TrainTag.of(DockerTag("baz"))

        Assert.assertNotEquals(SpecialTrainTag.TEST, tag1)
        Assert.assertNotEquals(SpecialTrainTag.IMMEDIATE, tag1)
        Assert.assertNotEquals(SpecialTrainTag.TEST, tag2)
        Assert.assertNotEquals(SpecialTrainTag.IMMEDIATE, tag2)
        Assert.assertNotEquals(SpecialTrainTag.TEST, tag3)
        Assert.assertNotEquals(SpecialTrainTag.IMMEDIATE, tag3)
    }
}
