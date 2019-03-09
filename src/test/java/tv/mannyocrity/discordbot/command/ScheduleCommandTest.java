package tv.mannyocrity.discordbot.command;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.junit.Rule;
import org.junit.Test;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import tv.mannyocrity.discordbot.exception.TimeConversionException;
import tv.mannyocrity.discordbot.model.Activity;
import tv.mannyocrity.discordbot.model.TimeSlot;
import tv.mannyocrity.discordbot.rules.LogVerify;
import tv.mannyocrity.discordbot.utils.CollectionsHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

@Slf4j
public class ScheduleCommandTest {
    @Rule
    public LogVerify logVerify = new LogVerify() {{
        recordLoggingForType(ScheduleCommand.class);
    }};

    private ScheduleCommand toTest;

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
    public void parseDayCapitalPM() throws TimeConversionException {
        // SETUP
        toTest = new ScheduleCommand();
        TimeSlot expectedTimeSlot = new TimeSlot();
        expectedTimeSlot.setStreamDay(DayOfWeek.MONDAY, "10:30pm", "1:00am", 1);
        String day = "mon=10:30pM-1:00am";

        // EXECUTE
        TimeSlot result = toTest.parseDay(day, 1);

        // VERIFY
        assertEquals(expectedTimeSlot, result);
    }

    @Test
    public void parseDayOffDay() {
        // SETUP
        toTest = new ScheduleCommand();
        TimeSlot expectedTimeSlot = new TimeSlot();
        expectedTimeSlot.setOffDay(DayOfWeek.MONDAY);
        String day = "mon=off";

        // EXECUTE
        TimeSlot result = toTest.parseDay(day, 1);

        // VERIFY
        assertEquals(expectedTimeSlot, result);
    }

    @Test
    public void parseDaySupportDay() {
        // SETUP
        toTest = new ScheduleCommand();
        TimeSlot expectedTimeSlot = new TimeSlot();
        expectedTimeSlot.setSupportDay(DayOfWeek.FRIDAY);
        String day = "fri=support";

        // EXECUTE
        TimeSlot result = toTest.parseDay(day, 1);

        // VERIFY
        assertEquals(expectedTimeSlot, result);
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