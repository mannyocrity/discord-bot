package tv.mannyocrity.discordbot.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import tv.mannyocrity.discordbot.exception.TimeConversionException;

import java.time.DayOfWeek;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@Slf4j
public class TimeSlotTest {

    @Test
    public void setStartTime() throws TimeConversionException {
        // SETUP
        int offset = -8;
        TimeSlot toTest = new TimeSlot();
        String startTime = "09:15PM";
        String expectedStartTime = "5:15AM";

        String endTime = "11:30PM";
        String expectedEndTime = "7:30AM";

        // EXECUTE
        toTest.setStreamDay(DayOfWeek.FRIDAY, startTime, endTime, offset);

        // VERIFY
        assertEquals(expectedStartTime, toTest.getStartTime());
        assertEquals(expectedEndTime, toTest.getEndTime());
        assertEquals(Activity.STREAMING, toTest.getActivity());
    }

    @Test
    public void setOffDay() throws TimeConversionException {
        // SETUP
        int offset = -8;
        TimeSlot toTest = new TimeSlot();
        toTest.setStreamDay(DayOfWeek.FRIDAY, "10:30PM", "11:00PM", offset);

        //EXECUTE
        assertEquals(Activity.STREAMING, toTest.getActivity());
        toTest.setOffDay(DayOfWeek.SATURDAY);

        // VERIFY
        assertNull(toTest.getStartTime());
        assertNull(toTest.getEndTime());
        assertEquals(Activity.OFF, toTest.getActivity());
    }

    @Test
    public void setSupportDay() throws TimeConversionException {
        // SETUP
        int offset = -8;
        TimeSlot toTest = new TimeSlot();
        toTest.setStreamDay(DayOfWeek.FRIDAY, "10:30PM", "11:00PM", offset);

        //EXECUTE
        assertEquals(Activity.STREAMING, toTest.getActivity());
        toTest.setSupportDay(DayOfWeek.TUESDAY);

        // VERIFY
        assertNull(toTest.getStartTime());
        assertNull(toTest.getEndTime());
        assertEquals(Activity.SUPPORTING, toTest.getActivity());
    }
}
