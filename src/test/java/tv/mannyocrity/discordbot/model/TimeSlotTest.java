package tv.mannyocrity.discordbot.model;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.junit.Rule;
import org.junit.Test;
import tv.mannyocrity.discordbot.rules.LogVerify;
import tv.mannyocrity.discordbot.utils.TimeConversion;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

@Slf4j
public class TimeSlotTest {

    @Test
    public void setStartTime() throws ParseException {
        // SETUP
        TimeSlot toTest = new TimeSlot();
        String startTime = "09:15 PM";
        String expectedTime = "05:15 AM";

        // EXECUTE
        toTest.setStartTime(startTime, "PST");

        // VERIFY
        assertEquals(expectedTime, toTest.getStartTime());
    }

    @Test
    public void setStartTimeParseException() {
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
        }
    }
}
