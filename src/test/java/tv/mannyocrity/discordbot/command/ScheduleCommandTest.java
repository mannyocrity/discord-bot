package tv.mannyocrity.discordbot.command;

import org.apache.commons.cli.ParseException;
import org.junit.Test;
import tv.mannyocrity.discordbot.exception.TimeConversionException;
import tv.mannyocrity.discordbot.model.TimeSlot;

public class ScheduleCommandTest {
    ScheduleCommand toTest;

    @Test
    public void parseDay() throws ParseException, TimeConversionException {
        // SETUP
        String day = "mon=10:30pm-1:00am";
        TimeSlot expected = new TimeSlot();
        expected.setStreamDay("10:30pm", "01:30am", 1);

        toTest = new ScheduleCommand();

        // EXECUTE
        TimeSlot result = toTest.parseDay(day);

        // VERIFY
//        assertEquals(result);
    }
}
