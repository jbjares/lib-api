package de.difuture.ekut.pht.lib.train

import jdregistry.client.data.DockerTag
import org.junit.Assert
import org.junit.Test

/**
 * Tests the [TrainTag] api
 *
 * @author Lukas Zimmermann
 * @see TrainTag
 * @see SpecialTrainTag
 * @since 0.0.1
 *
 */
class TrainTagTests {

    @Test fun special_train_tag_from_string() {

        mapOf(
                "test" to TrainTag.TEST
        ).forEach { (s, tag) ->

            Assert.assertEquals(TrainTag.of(s), tag)
        }

        // Case sensitive
        mapOf(
                "TEST" to TrainTag.TEST
        ).forEach { (s, tag) ->

            Assert.assertNotEquals(TrainTag.of(s), tag)
        }
    }

    @Test fun mode_train_tag_from_string() {

        mapOf(
                "immediate" to TrainTag.IMMEDIATE
        ).forEach { (s, tag) ->

            Assert.assertEquals(TrainTag.of(s), tag)
        }

        // Case sensitive
        mapOf(
                "IMMEDIATE" to TrainTag
        ).forEach { (s, tag) ->

            Assert.assertNotEquals(TrainTag.of(s), tag)
        }
    }

    @Test
    fun generic_train_tag_from_string() {

        val tag1 = TrainTag.of("foo")
        val tag2 = TrainTag.of("bar")
        val tag3 = TrainTag.of("baz")

        Assert.assertNotEquals(TrainTag.TEST, tag1)
        Assert.assertNotEquals(TrainTag.IMMEDIATE, tag1)
        Assert.assertNotEquals(TrainTag.TEST, tag2)
        Assert.assertNotEquals(TrainTag.IMMEDIATE, tag2)
        Assert.assertNotEquals(TrainTag.TEST, tag3)
        Assert.assertNotEquals(TrainTag.IMMEDIATE, tag3)
    }

    @Test fun special_train_tag_from_dockertag() {

        // The special train tags
        val tag1 = TrainTag.of(DockerTag.of("test"))
        val tag2 = TrainTag.of(DockerTag.of("immediate"))
        val tag3 = TrainTag.of(DockerTag.of("TEST"))
        val tag4 = TrainTag.of(DockerTag.of("IMMEDIATE"))
        val tag5 = TrainTag.of(DockerTag.of("TesT"))
        val tag6 = TrainTag.of(DockerTag.of("ImmEDIATE"))

        Assert.assertEquals(TrainTag.TEST, tag1)
        Assert.assertEquals(TrainTag.IMMEDIATE, tag2)
        Assert.assertNotEquals(TrainTag.TEST, tag3)
        Assert.assertNotEquals(TrainTag.IMMEDIATE, tag4)
        Assert.assertNotEquals(TrainTag.TEST, tag5)
        Assert.assertNotEquals(TrainTag.IMMEDIATE, tag6)
    }

    @Test fun generic_train_tag_from_dockertag() {

        val tag1 = TrainTag.of(DockerTag.of("foo"))
        val tag2 = TrainTag.of(DockerTag.of("bar"))
        val tag3 = TrainTag.of(DockerTag.of("baz"))

        Assert.assertNotEquals(TrainTag.TEST, tag1)
        Assert.assertNotEquals(TrainTag.IMMEDIATE, tag1)
        Assert.assertNotEquals(TrainTag.TEST, tag2)
        Assert.assertNotEquals(TrainTag.IMMEDIATE, tag2)
        Assert.assertNotEquals(TrainTag.TEST, tag3)
        Assert.assertNotEquals(TrainTag.IMMEDIATE, tag3)
    }
}
