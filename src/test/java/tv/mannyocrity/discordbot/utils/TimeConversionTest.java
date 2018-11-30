package tv.mannyocrity.discordbot.utils;

import org.apache.logging.log4j.Level;
import org.junit.Rule;
import org.junit.Test;

import tv.mannyocrity.discordbot.exception.TimeConversionException;
import tv.mannyocrity.discordbot.rules.LogVerify;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TimeConversionTest {

    @Rule
    public LogVerify logVerify = new LogVerify() {{
        recordLoggingForType(TimeConversion.class);
    }};

    @Test
    public void convertFromUTC() throws TimeConversionException {
        // SETUP
        int offset = -8;
        String startTime = "06:30 AM";
        String expectedTime = "10:30 PM";

        // EXECUTE
        String result = TimeConversion.convertFromUTC(startTime, offset);

        // VERIFY
        assertEquals(expectedTime, result);
    }

    @Test
    public void convertToUTC() throws TimeConversionException {
        // SETUP
        int offset = -6;
        String startTime = "10:30 PM";
        String expectedTime = "04:30 AM";

        // EXECUTE
        String result = TimeConversion.convertToUTC(startTime, offset);

        // VERIFY
        assertEquals(expectedTime, result);
    }

    @Test
    public void convertTimezone() throws TimeConversionException {
        // SETUP
        String time = "10:30 PM";

        // EXECUTE
        TimeConversion.convertTimezone(time, -5, 0);

        // VERIFY
        // No exception is thrown.
    }

    @Test
    public void convertTimezoneInvalidHour() {
        // SETUP
        String time = "15:30 PM";

        try {
            // EXECUTE
            TimeConversion.convertTimezone(time, 0, 0);
            fail("TimeConversionException is not thrown.");
            // VERIFY
        } catch (TimeConversionException e) {
            logVerify.verifyLogMessages(e.getMessage(), Level.ERROR);
        }
    }

    @Test
    public void convertTimezoneInvalidMinute() {
        // SETUP
        String time = "09:61 PM";

        try {
            // EXECUTE
            TimeConversion.convertTimezone(time, 0, 0);
            fail("TimeConversionException is not thrown.");
            // VERIFY
        } catch (TimeConversionException e) {
            logVerify.verifyLogMessages(e.getMessage(), Level.ERROR);
        }
    }

    @Test
    public void convertTimezoneInvalidMeridies() {
        // SETUP
        String time = "09:15 CM";

        try {
            // EXECUTE
            TimeConversion.convertTimezone(time, 0, 0);
            fail("TimeConversionException is not thrown.");
            // VERIFY
        } catch (TimeConversionException e) {
            logVerify.verifyLogMessages(e.getMessage(), Level.ERROR);
        }
    }

    @Test
    public void convertTimezoneInvalidFormat() {
        // SETUP
        String time = "09 15 AM";

        try {
            // EXECUTE
            TimeConversion.convertTimezone(time, 0, 0);
            fail("TimeConversionException is not thrown.");
            // VERIFY
        } catch (TimeConversionException e) {
            logVerify.verifyLogMessages(e.getMessage(), Level.ERROR);
        }
    }

    @Test
    public void validateOffsetInvalidOffsetToLow() {
        // SETUP
        String time = "09:15 AM";
        int offset = -19;

        try {
            // EXECUTE
            TimeConversion.convertTimezone(time, offset, 0);
            fail("TimeConversionException is not thrown.");
            // VERIFY
        } catch (TimeConversionException e) {
            logVerify.verifyLogMessages(e.getMessage(), Level.ERROR);
        }
    }

    @Test
    public void validateOffsetInvalidOffsetToHigh() {
        // SETUP
        String time = "09:15 AM";
        int offset = 19;

        try {
            // EXECUTE
            TimeConversion.convertTimezone(time, offset, 0);
            fail("TimeConversionException is not thrown.");
            // VERIFY
        } catch (TimeConversionException e) {
            logVerify.verifyLogMessages(e.getMessage(), Level.ERROR);
        }
    }
}
