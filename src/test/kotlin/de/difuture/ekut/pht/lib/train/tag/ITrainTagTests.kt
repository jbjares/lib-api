package de.difuture.ekut.pht.lib.train.tag

import de.difuture.ekut.pht.lib.common.docker.DockerTag
import org.junit.Assert
import org.junit.Test


/**
 * Tests the [ITrainTag] api
 *
 * @author Lukas Zimmermann
 * @see ITrainTag
 * @see SpecialTrainTag
 * @since 0.0.1
 *
 */
class ITrainTagTests {


    @Test fun special_train_tag_from_string() {

        mapOf(
                "test" to SpecialTrainTag.TEST
        ).forEach { (s, tag) ->

            Assert.assertTrue(SpecialTrainTag.isMember(s))
            Assert.assertEquals(ITrainTag.of(s), tag)
        }

        // Case sensitive
        mapOf(
                "TEST" to SpecialTrainTag.TEST
        ).forEach { (s, tag) ->

            Assert.assertFalse(SpecialTrainTag.isMember(s))
            Assert.assertNotEquals(ITrainTag.of(s), tag)
        }
    }

    @Test fun mode_train_tag_from_string() {

        mapOf(
                "immediate" to ModeTrainTag.IMMEDIATE
        ).forEach { (s, tag) ->

            Assert.assertTrue(ModeTrainTag.isMember(s))
            Assert.assertEquals(ITrainTag.of(s), tag)
        }

        // Case sensitive
        mapOf(
                "IMMEDIATE" to ModeTrainTag
        ).forEach { (s, tag) ->

            Assert.assertFalse(ModeTrainTag.isMember(s))
            Assert.assertNotEquals(ITrainTag.of(s), tag)
        }
    }


    @Test
    fun generic_train_tag_from_string() {

        val tag1 = ITrainTag.of("foo")
        val tag2 = ITrainTag.of("bar")
        val tag3 = ITrainTag.of("baz")

        Assert.assertNotEquals(SpecialTrainTag.TEST, tag1)
        Assert.assertNotEquals(ModeTrainTag.IMMEDIATE, tag1)
        Assert.assertNotEquals(SpecialTrainTag.TEST, tag2)
        Assert.assertNotEquals(ModeTrainTag.IMMEDIATE, tag2)
        Assert.assertNotEquals(SpecialTrainTag.TEST, tag3)
        Assert.assertNotEquals(ModeTrainTag.IMMEDIATE, tag3)
    }

    @Test fun special_train_tag_from_dockertag() {

        // The special train tags
        val tag1 = ITrainTag.of(DockerTag("test"))
        val tag2 = ITrainTag.of(DockerTag("immediate"))
        val tag3 = ITrainTag.of(DockerTag("TEST"))
        val tag4 = ITrainTag.of(DockerTag("IMMEDIATE"))
        val tag5 = ITrainTag.of(DockerTag("TesT"))
        val tag6 = ITrainTag.of(DockerTag("ImmEDIATE"))

        Assert.assertEquals(SpecialTrainTag.TEST, tag1)
        Assert.assertEquals(ModeTrainTag.IMMEDIATE, tag2)
        Assert.assertNotEquals(SpecialTrainTag.TEST, tag3)
        Assert.assertNotEquals(ModeTrainTag.IMMEDIATE, tag4)
        Assert.assertNotEquals(SpecialTrainTag.TEST, tag5)
        Assert.assertNotEquals(ModeTrainTag.IMMEDIATE, tag6)
    }

    @Test fun generic_train_tag_from_dockertag() {

        val tag1 = ITrainTag.of(DockerTag("foo"))
        val tag2 = ITrainTag.of(DockerTag("bar"))
        val tag3 = ITrainTag.of(DockerTag("baz"))

        Assert.assertNotEquals(SpecialTrainTag.TEST, tag1)
        Assert.assertNotEquals(ModeTrainTag.IMMEDIATE, tag1)
        Assert.assertNotEquals(SpecialTrainTag.TEST, tag2)
        Assert.assertNotEquals(ModeTrainTag.IMMEDIATE, tag2)
        Assert.assertNotEquals(SpecialTrainTag.TEST, tag3)
        Assert.assertNotEquals(ModeTrainTag.IMMEDIATE, tag3)
    }
}
