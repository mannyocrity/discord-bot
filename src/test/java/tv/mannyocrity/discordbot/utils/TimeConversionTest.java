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
    public void convertToUTC() throws ParseException {
        // SETUP
        String startTime = "10:30 PM";
        String expectedTime = "06:30 AM";

        // EXECUTE
        String result = TimeConversion.convertToUTC(startTime, "PST");

        // VERIFY
        assertEquals(expectedTime, result);
    }

    @Test
    public void convertToUTCParseException() {
        // SETUP
        String expectedTime = "10:30 CM";

        String result = null;

        try {
            // EXECUTE
            result = TimeConversion.convertToUTC(expectedTime, "PST");
            fail("ParseException is never thrown.");
        } catch (ParseException e) {
            // VERIFY
            assertNull(result);
            logVerify.verifyLogMessages("Incorrect format " + expectedTime + " needs to be in format '10:30 PM'.", Level.ERROR);
        }
    }
}
