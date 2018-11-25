package tv.mannyocrity.discordbot.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import tv.mannyocrity.discordbot.exception.TimeConversionException;

import static org.junit.Assert.assertEquals;

@Slf4j
public class TimeSlotTest {

    @Test
    public void setStartTime() throws TimeConversionException {
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
    public void setEndTime() throws TimeConversionException {
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
}
