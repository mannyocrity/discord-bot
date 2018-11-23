package tv.mannyocrity.discordbot.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.junit.Rule;
import org.junit.Test;
import tv.mannyocrity.discordbot.rules.LogVerify;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

@Slf4j
public class TimeSlotTest {

    @Rule
    public LogVerify logVerify = new LogVerify() {{
        recordLoggingForType(TimeSlot.class);
    }};

    @Test
    public void setStartTime() throws ParseException {
        // SETUP
        TimeSlot toTest = new TimeSlot();
        String expectedTime = "10:30 PM";

        // EXECUTE
        toTest.setStartTime(expectedTime);

        // VERIFY
        assertEquals(expectedTime, toTest.getStartTime());
    }

    @Test
    public void setStartTimeParseException() {
        // SETUP
        TimeSlot toTest = new TimeSlot();
        String expectedTime = "10:30 CM";

        try {
            // EXECUTE
            toTest.setStartTime(expectedTime);
            fail("ParseException is never thrown.");
        } catch (ParseException e) {
            // VERIFY
            assertNull(toTest.getStartTime());
            logVerify.verifyLogMessages("Incorrect format 10:30 CM needs to be in format '10:30 PM'.", Level.ERROR);
        }
    }
}
