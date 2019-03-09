package tv.mannyocrity.discordbot.facade;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import tv.mannyocrity.discordbot.exception.TimeConversionException;
import tv.mannyocrity.discordbot.model.Schedule;
import tv.mannyocrity.discordbot.model.TimeSlot;

import java.time.DayOfWeek;

@Slf4j
public class DynamoDBClientFacadeTest {

    @Test
    @Ignore("Will not work on the travis because of aws keys.")
    public void saveSchedule() throws TimeConversionException {
        // SETUP
        DynamoDBClientFacade toTest = new DynamoDBClientFacade();

        Schedule schedule = new Schedule();
        schedule.setTwitchId("Fooby");
        schedule.setDiscordId("Fooby-BW");

        TimeSlot ts = new TimeSlot();
        ts.setStreamDay(DayOfWeek.MONDAY, "10:30pm", "12:30am", -5);
        log.info(ts.toString());
        schedule.setMonday(ts);
        schedule.setWednesday(ts);
        schedule.setFriday(ts);
        schedule.setSaturday(ts);

        ts = new TimeSlot();
        ts.setOffDay(DayOfWeek.SATURDAY);
        schedule.setSunday(ts);
        schedule.setTuesday(ts);

        ts = new TimeSlot();
        ts.setSupportDay(DayOfWeek.SUNDAY);
        schedule.setThursday(ts);

        // EXECUTE
        toTest.saveSchedule(schedule);

        // VALIDATE
    }
}
