package tv.mannyocrity.discordbot.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import tv.mannyocrity.discordbot.exception.TimeConversionException;
import tv.mannyocrity.discordbot.utils.TimeConversion;

import java.time.DayOfWeek;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;

@Slf4j
public class TimeSlotTest {

    @Test
    public void setStartTime() throws TimeConversionException {
        // SETUP
        int offset = -8;
        TimeSlot toTest = new TimeSlot();
        String startTime = "09:15 PM";
        String expectedStartTime = "05:15 AM";

        String endTime = "11:30 PM";
        String expectedEndTime = "07:30 AM";

        // EXECUTE
        toTest.setStreamDay(startTime, endTime, offset);

        // VERIFY
        assertEquals(expectedStartTime, toTest.getStartTime());
        assertEquals(expectedEndTime, toTest.getEndTime());
        assertFalse(toTest.isOff());
    }

    @Test
    public void setOffDay() throws TimeConversionException {
        // SETUP
        int offset = -8;
        TimeSlot toTest = new TimeSlot();
        toTest.setDay(DayOfWeek.MONDAY);
        toTest.setStreamDay("10:30 PM", "11:00 PM", offset);

        //EXECUTE
        assertFalse(toTest.isOff());
        toTest.setOffDay();

        // VERIFY
        assertTrue(toTest.isOff());
        assertNull(toTest.getStartTime());
        assertNull(toTest.getEndTime());
    }
}
