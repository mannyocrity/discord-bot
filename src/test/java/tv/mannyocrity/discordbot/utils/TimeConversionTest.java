package tv.mannyocrity.discordbot.utils;

import org.apache.logging.log4j.Level;
import org.junit.Rule;
import org.junit.Test;
import tv.mannyocrity.discordbot.exception.TimeConversionException;
import tv.mannyocrity.discordbot.rules.LogVerify;

import java.time.ZoneOffset;

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
        String startTime = "06:30AM";
        String expectedTime = "10:30PM";

        // EXECUTE
        String result = TimeConversion.convertFromUTC(startTime, offset);

        // VERIFY
        assertEquals(expectedTime, result);
    }

    @Test
    public void convertToUTC() throws TimeConversionException {
        // SETUP
        int offset = -6;
        String startTime = "10:30PM";
        String expectedTime = "4:30AM";

        // EXECUTE
        String result = TimeConversion.convertToUTC(startTime, offset);

        // VERIFY
        assertEquals(expectedTime, result);
    }

    @Test
    public void convertTimezone() throws TimeConversionException {
        // SETUP
        String time = "10:30PM";
        String expectedTime = "3:30AM";

        ZoneOffset zoneOffset = ZoneOffset.ofHours(-5);

        // EXECUTE
        String result = TimeConversion.convertTimezone(time, zoneOffset, ZoneOffset.UTC);

        // VERIFY
        assertEquals(expectedTime, result);
    }

    @Test
    public void convertTimezoneTimeWithLeadingZero() throws TimeConversionException {
        // SETUP
        String time = "06:00am";
        String expectedTime = "5:00AM";

        ZoneOffset zoneOffset = ZoneOffset.ofHours(1);

        // EXECUTE
        String result = TimeConversion.convertTimezone(time, zoneOffset, ZoneOffset.UTC);

        // VERIFY
        assertEquals(expectedTime, result);
    }

    @Test
    public void convertTimezoneLowercaseMeridies() throws TimeConversionException {
        // SETUP
        String time = "10:30pm";
        String expectedTime = "3:30AM";
        ZoneOffset zoneOffset = ZoneOffset.ofHours(-5);

        // EXECUTE
        String result = TimeConversion.convertTimezone(time, zoneOffset, ZoneOffset.UTC);

        // VERIFY
        assertEquals(expectedTime, result);
    }

    @Test
    public void convertTimezoneInvalidHour() {
        // SETUP
        String time = "13:30PM";

        try {
            // EXECUTE
            TimeConversion.convertTimezone(time, ZoneOffset.UTC, ZoneOffset.UTC);
            fail("TimeConversionException is not thrown.");
            // VERIFY
        } catch (TimeConversionException e) {
            logVerify.verifyLogMessages(e.getMessage(), Level.ERROR);
        }
    }

    @Test
    public void convertTimezoneInvalidMinute() {
        // SETUP
        String time = "09:60PM";

        try {
            // EXECUTE
            TimeConversion.convertTimezone(time, ZoneOffset.UTC, ZoneOffset.UTC);
            fail("TimeConversionException is not thrown.");
            // VERIFY
        } catch (TimeConversionException e) {
            logVerify.verifyLogMessages(e.getMessage(), Level.ERROR);
        }
    }

    @Test
    public void convertTimezoneInvalidMeridies() {
        // SETUP
        String time = "09:15CM";

        try {
            // EXECUTE
            TimeConversion.convertTimezone(time, ZoneOffset.UTC, ZoneOffset.UTC);
            fail("TimeConversionException is not thrown.");
            // VERIFY
        } catch (TimeConversionException e) {
            logVerify.verifyLogMessages(e.getMessage(), Level.ERROR);
        }
    }

    @Test
    public void convertTimezoneInvalidFormat() {
        // SETUP
        String time = "09 15AM";

        try {
            // EXECUTE
            TimeConversion.convertTimezone(time, ZoneOffset.UTC, ZoneOffset.UTC);
            fail("TimeConversionException is not thrown.");
            // VERIFY
        } catch (TimeConversionException e) {
            logVerify.verifyLogMessages(e.getMessage(), Level.ERROR);
        }
    }

    @Test
    public void validateOffset() throws TimeConversionException {
        // SETUP
        int offset = -5;
        ZoneOffset expectedZoneOffset = ZoneOffset.ofHours(offset);

        // EXECUTE
        ZoneOffset result = TimeConversion.validateOffset(offset);

        // VERIFY
        assertEquals(expectedZoneOffset, result);
    }

    @Test
    public void validateOffsetInvalidOffsetToLow() {
        // SETUP
        int offset = -19;

        try {
            // EXECUTE
            TimeConversion.validateOffset(offset);
            fail("TimeConversionException is not thrown.");
            // VERIFY
        } catch (TimeConversionException e) {
            logVerify.verifyLogMessages(e.getMessage(), Level.ERROR);
        }
    }

    @Test
    public void validateOffsetInvalidOffsetToHigh() {
        // SETUP
        int offset = 19;

        try {
            // EXECUTE
            TimeConversion.validateOffset(offset);
            fail("TimeConversionException is not thrown.");
            // VERIFY
        } catch (TimeConversionException e) {
            logVerify.verifyLogMessages(e.getMessage(), Level.ERROR);
        }
    }
}
