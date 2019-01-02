package tv.mannyocrity.discordbot.command;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.junit.Rule;
import org.junit.Test;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import tv.mannyocrity.discordbot.model.TimeSlot;
import tv.mannyocrity.discordbot.rules.LogVerify;
import tv.mannyocrity.discordbot.utils.CollectionsHelper;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class ScheduleCommandTest {
    @Rule
    public LogVerify logVerify = new LogVerify() {{
        recordLoggingForType(ScheduleCommand.class);
    }};

    ScheduleCommand toTest;

    @Test
    public void parseDayIllegalDay() {
        // SETUP
        toTest = new ScheduleCommand();

        String day = "foo=10:30pm-1:00am";
        String expectedLogMsg = "Not a valid day of the week. " + CollectionsHelper.getKeysFromMap(ScheduleCommand.getDayMapping());

        // EXECUTE
        TimeSlot result = toTest.parseDay(day, 1);

        // VERIFY
        assertNull(result);

        logVerify.verifyLogMessages(expectedLogMsg, Level.ERROR);
    }

    @Test
    public void runCommand() {
        // SETUP
        final int offset = -5;
        toTest = new ScheduleCommand();
        MessageReceivedEvent messageReceivedEvent = mock(MessageReceivedEvent.class);

        String expectedLogOffsetMsg = "Offset: " + offset;

        List<String> argList = Arrays.asList("offset=" + offset, "mon=10:00pm-1:00am", "wed=10:00pm-1:00am", "fri=10:00pm-1:00am");

        // EXECUTE
        toTest.runCommand(messageReceivedEvent, argList);

        // Verify
        logVerify.verifyLogMessages(expectedLogOffsetMsg, Level.INFO);

    }

}