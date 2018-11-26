package tv.mannyocrity.discordbot.utils;

import org.apache.logging.log4j.Level;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import tv.mannyocrity.discordbot.exception.TimeConversionException;
import tv.mannyocrity.discordbot.rules.LogVerify;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TimeConversionTest {
    @Mock
    private DateFormat startZoneMock;

    @Mock
    private DateFormat endZoneMock;

    @Rule
    public LogVerify logVerify = new LogVerify() {{
        recordLoggingForType(TimeConversion.class);
    }};

    @Test
    public void convertFromUTC() throws TimeConversionException {
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
    public void convertToUTC() throws TimeConversionException {
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
    public void validateTime() throws TimeConversionException {
        // SETUP
        String time = "10:30 PM";
        DateFormat startZone = new SimpleDateFormat(TimeConversion.TIME_PATTERN, Locale.ROOT);
        startZone.setTimeZone(TimeZone.getTimeZone("PST"));

        DateFormat utcFormat = new SimpleDateFormat(TimeConversion.TIME_PATTERN);
        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        // EXECUTE
        TimeConversion.convertTimezone(time, startZone, utcFormat);

        // VERIFY
        // No exception is thrown.
    }

    @Test
    public void validateTimeInvalidHour() {
        // SETUP
        String time = "15:30 PM";

        try {
            // EXECUTE
            TimeConversion.convertTimezone(time, startZoneMock, endZoneMock);
            fail("DateTimeParseException is not thrown.");
            // VERIFY
        } catch (TimeConversionException e) {
            logVerify.verifyLogMessages(e.getMessage(), Level.ERROR);
        }
    }

    @Test
    public void validateTimeInvalidMinute() {
        // SETUP
        String time = "09:61 PM";

        try {
            // EXECUTE
            TimeConversion.convertTimezone(time, startZoneMock, endZoneMock);
            fail("DateTimeParseException is not thrown.");
            // VERIFY
        } catch (TimeConversionException e) {
            logVerify.verifyLogMessages(e.getMessage(), Level.ERROR);
        }
    }

    @Test
    public void validateTimeInvalidMeridies () {
        // SETUP
        String time = "09:15 CM";

        try {
            // EXECUTE
            TimeConversion.convertTimezone(time, startZoneMock, endZoneMock);
            fail("DateTimeParseException is not thrown.");
            // VERIFY
        } catch (TimeConversionException e) {
            logVerify.verifyLogMessages(e.getMessage(), Level.ERROR);
        }
    }

    @Test
    public void validateTimeInvalidFormat () {
        // SETUP
        String time = "09 15 AM";

        try {
            // EXECUTE
            TimeConversion.convertTimezone(time, startZoneMock, endZoneMock);
            fail("DateTimeParseException is not thrown.");
            // VERIFY
        } catch (TimeConversionException e) {
            logVerify.verifyLogMessages(e.getMessage(), Level.ERROR);
        }
    }
}
