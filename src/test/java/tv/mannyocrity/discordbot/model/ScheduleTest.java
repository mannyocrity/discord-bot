package tv.mannyocrity.discordbot.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import tv.mannyocrity.discordbot.exception.TimeConversionException;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ScheduleTest {
//    final String testSchdule =
//            "{\"discordId\":\"someOne\"," +
//            "\"twitchId\":\"nobody\"," +
//                    "\"days\": []}";


//    @Test
//    public void deserializeSchedule() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        Schedule schedule = objectMapper.readValue(json, Schedule.class);
//    }

    @Test
    public void serializeSchedule() throws JsonProcessingException, TimeConversionException {
        // SETUP
        ObjectMapper objectMapper = new ObjectMapper();
        Schedule schedule = new Schedule();
        schedule.setDiscordId("someone");
        schedule.setTwitchId("nobody");

        TimeSlot ts = new TimeSlot();
        ts.setDay(DayOfWeek.MONDAY);
        ts.setStreamDay("10:30 PM", "11:00 PM","PST");

        List<TimeSlot> timeSlotList = new ArrayList();
        timeSlotList.add(ts);

        schedule.setDays(timeSlotList);

        // EXECUTE
        log.info(objectMapper.writeValueAsString(schedule));
    }
}
