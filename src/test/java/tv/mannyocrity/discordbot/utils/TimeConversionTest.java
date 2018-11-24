package tv.mannyocrity.discordbot.utils;

import org.apache.logging.log4j.Level;
import org.junit.Rule;
import org.junit.Test;
import tv.mannyocrity.discordbot.rules.LogVerify;

import java.text.ParseException;

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
    public void convertFromUTCParseException() {
        // SETUP
        String timezone = "PST";
        String startTime = "10 30 AM";

        String result = null;

        try {
            // EXECUTE
            result = TimeConversion.convertToUTC(startTime, timezone);
            fail("ParseException is never thrown.");
        } catch (ParseException e) {
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
    public void convertToUTCParseException() {
        // SETUP
        String timezone = "PST";
        String startTime = "10:30 CM";

        String result = null;

        try {
            // EXECUTE
            result = TimeConversion.convertToUTC(startTime, timezone);
            fail("ParseException is never thrown.");
        } catch (ParseException e) {
            // VERIFY
            assertNull(result);
            logVerify.verifyLogMessages("Incorrect format '" + startTime + "' needs to be in format '10:30 PM'.",
                    Level.ERROR);
        }
    }
}
