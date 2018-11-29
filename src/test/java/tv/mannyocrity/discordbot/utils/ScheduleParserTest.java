package tv.mannyocrity.discordbot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import tv.mannyocrity.discordbot.exception.TimeConversionException;
import tv.mannyocrity.discordbot.model.Schedule;
import tv.mannyocrity.discordbot.model.TimeSlot;

import java.io.IOException;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Slf4j
public class ScheduleParserTest {
    final String json = "{\"discordId\":\"someone\"," +
            "\"twitchId\":\"nobody\"," +
            "\"days\":[" +
            "{\"day\":\"SATURDAY\",\"startTime\":\"06:30 AM\",\"endTime\":\"07:00 AM\",\"off\":false}," +
            "{\"day\":\"SATURDAY\",\"startTime\":\"06:30 AM\",\"endTime\":\"07:00 AM\",\"off\":false}," +
            "{\"day\":\"SATURDAY\",\"startTime\":\"06:30 AM\",\"endTime\":\"07:00 AM\",\"off\":false}]}";

    @Test
    public void deserializeSchedule() throws IOException, TimeConversionException {
        // SETUP
        Schedule expectedSchedule = new Schedule();
        expectedSchedule.setDiscordId("someone");
        expectedSchedule.setTwitchId("nobody");

        List<TimeSlot> timeSlotList = new ArrayList<>();
        TimeSlot ts = new TimeSlot();
        ts.setDay(DayOfWeek.MONDAY);
        ts.setStreamDay("10:30 PM", "11:00 PM",-8);
        timeSlotList.add(ts);
        ts.setDay(DayOfWeek.WEDNESDAY);
        timeSlotList.add(ts);
        ts.setDay(DayOfWeek.SATURDAY);
        timeSlotList.add(ts);

        expectedSchedule.setDays(timeSlotList);

        // EXECUTE
        Schedule result = ScheduleParser.deserializeSchedule(json);

        // VERIFY
        assertEquals(expectedSchedule, result);
    }

    @Test
    public void serializeSchedule() throws JsonProcessingException, TimeConversionException {
        // SETUP
        Schedule schedule = new Schedule();
        schedule.setDiscordId("someone");
        schedule.setTwitchId("nobody");

        List<TimeSlot> timeSlotList = new ArrayList<>();
        TimeSlot ts = new TimeSlot();
        ts.setDay(DayOfWeek.MONDAY);
        ts.setStreamDay("10:30 PM", "11:00 PM",-8);
        timeSlotList.add(ts);
        ts.setDay(DayOfWeek.WEDNESDAY);
        timeSlotList.add(ts);
        ts.setDay(DayOfWeek.SATURDAY);
        timeSlotList.add(ts);

        schedule.setDays(timeSlotList);

        // EXECUTE
        String result = ScheduleParser.serializeSchedule(schedule);

        // VERIFY
        assertEquals(json, result);
    }
}
