package tv.mannyocrity.discordbot.command;

import org.apache.logging.log4j.Level;
import org.junit.Rule;
import org.junit.Test;
import tv.mannyocrity.discordbot.model.TimeSlot;
import tv.mannyocrity.discordbot.rules.LogVerify;
import tv.mannyocrity.discordbot.utils.CollectionsHelper;

import static org.junit.Assert.assertNull;

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
        TimeSlot result = toTest.parseDay(day);

        // VERIFY
        assertNull(result);

        logVerify.verifyLogMessages(expectedLogMsg, Level.ERROR);

    }

}