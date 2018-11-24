package tv.mannyocrity.discordbot.utils;

import org.apache.logging.log4j.Level;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import tv.mannyocrity.discordbot.rules.LogVerify;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;

public class TimeConversionTest {
    @Rule
    public LogVerify logVerify = new LogVerify() {{
        recordLoggingForType(TimeConversion.class);
    }};

    @Test
    public void convertFromUTC() throws ParseException {
        // SETUP
        String timezone = "PST";
        String startTime = "06:30 AM";
        String expectedTime = "10:30 PM";

        // EXECUTE
        String result = TimeConversion.convertFromUTC(startTime, timezone);

        // VERIFY
        assertEquals(expectedTime, result);
    }

    @Test
    @Ignore
    public void convertFromUTCParseException() throws ParseException {
        // SETUP
        String timezone = "PST";
        String startTime = "10 30 AM";

        String result = null;

        try {
            // EXECUTE
            result = TimeConversion.convertToUTC(startTime, timezone);
            fail("DateTimeParseException is never thrown.");
        } catch (DateTimeParseException e) {
            // VERIFY
            assertNull(result);
            logVerify.verifyLogMessages("Incorrect format '" + startTime + "' needs to be in format '10:30 PM'.",
                    Level.ERROR);
        }
    }

    @Test
    public void convertToUTC() throws ParseException {
        // SETUP
        String timezone = "CST";
        String startTime = "10:30 PM";
        String expectedTime = "04:30 AM";

        // EXECUTE
        String result = TimeConversion.convertToUTC(startTime, timezone);

        // VERIFY
        assertEquals(expectedTime, result);
    }

    @Test
    @Ignore
    public void convertToUTCParseException() throws ParseException {
        // SETUP
        String timezone = "PST";
        String startTime = "10:30 CM";

        String result = null;

        try {
            // EXECUTE
            result = TimeConversion.convertToUTC(startTime, timezone);
            fail("DateTimeParseException is never thrown.");
        } catch (DateTimeParseException e) {
            // VERIFY
            assertNull(result);
            logVerify.verifyLogMessages("Incorrect format '" + startTime + "' needs to be in format '10:30 PM'.",
                    Level.ERROR);
        }
    }

    @Test
    public void validateTime() {
        // SETUP
        String time = "10:30 PM";

        // EXECUTE
        TimeConversion.validateTime(time);

        // Verfiy
        // No exception is thrown.
    }

    @Test(expected = DateTimeParseException.class)
    public void validateTimeInvalidHour() {
        // SETUP
        String time = "15:30 PM";

        // EXECUTE
        TimeConversion.validateTime(time);

        // Verfiy
        // DateTimeParseException expected
    }

    @Test(expected = DateTimeParseException.class)
    public void validateTimeInvalidMinute() {
        // SETUP
        String time = "09:61 PM";

        // EXECUTE
        TimeConversion.validateTime(time);

        // Verfiy
        // DateTimeParseException expected
    }

    @Test(expected = DateTimeParseException.class)
    public void validateTimeInvalidMeridies () {
        // SETUP
        String time = "09:15 CM";

        // EXECUTE
        TimeConversion.validateTime(time);

        // Verfiy
        // DateTimeParseException expected
    }
}
