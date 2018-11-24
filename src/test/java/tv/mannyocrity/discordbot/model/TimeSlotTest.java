package tv.mannyocrity.discordbot.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import tv.mannyocrity.discordbot.utils.TimeConversion;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;

@Slf4j
public class TimeSlotTest {

    @Test
    public void setStartTime() throws ParseException {
        // SETUP
        String timezone = "PST";
        TimeSlot toTest = new TimeSlot();
        String startTime = "09:15 PM";
        String expectedTime = "05:15 AM";

        // EXECUTE
        toTest.setStartTime(startTime, timezone);

        // VERIFY
        assertEquals(expectedTime, toTest.getStartTime());
    }

    @Test
    public void setStartTimeParseException() throws ParseException {
        // SETUP
        String timezone = "PST";
        String expectedTime = "10:30 CM";

        String result = null;

        try {
            // EXECUTE
            result = TimeConversion.convertToUTC(expectedTime, timezone);
            fail("DateTimeParseException is never thrown.");
        } catch (DateTimeParseException e) {
            // VERIFY
            assertNull(result);
        }
    }

    @Test
    public void setEndTime() throws ParseException {
        // SETUP
        String timezone = "MST";
        TimeSlot toTest = new TimeSlot();
        String startTime = "03:20 AM";
        String expectedTime = "10:20 AM";

        // EXECUTE
        toTest.setStartTime(startTime, timezone);

        // VERIFY
        assertEquals(expectedTime, toTest.getStartTime());
    }

    @Test
    public void setEndTimeParseException() throws ParseException {
        // SETUP
        String timezone = "PST";
        String expectedTime = "12:30 EST";

        String result = null;

        try {
            // EXECUTE
            result = TimeConversion.convertToUTC(expectedTime, timezone);
            fail("DateTimeParseException is never thrown.");
        } catch (DateTimeParseException e) {
            // VERIFY
            assertNull(result);
        }
    }
}
