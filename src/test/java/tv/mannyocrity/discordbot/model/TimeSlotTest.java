package tv.mannyocrity.discordbot.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import tv.mannyocrity.discordbot.exception.TimeConversionException;

import java.time.DayOfWeek;

import static org.junit.Assert.*;

@Slf4j
public class TimeSlotTest {

    @Test
    public void setStartTime() throws TimeConversionException {
        // SETUP
        int offset = -8;
        TimeSlot toTest = new TimeSlot();
        String startTime = "09:15PM";
        String expectedStartTime = "05:15AM";

        String endTime = "11:30PM";
        String expectedEndTime = "07:30AM";

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
        toTest.setStreamDay("10:30PM", "11:00PM", offset);

        //EXECUTE
        assertFalse(toTest.isOff());
        toTest.setOffDay();

        // VERIFY
        assertTrue(toTest.isOff());
        assertNull(toTest.getStartTime());
        assertNull(toTest.getEndTime());
    }
}
