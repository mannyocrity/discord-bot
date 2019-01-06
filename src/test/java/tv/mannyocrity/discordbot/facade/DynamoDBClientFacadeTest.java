package tv.mannyocrity.discordbot.facade;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import tv.mannyocrity.discordbot.exception.TimeConversionException;
import tv.mannyocrity.discordbot.model.Schedule;
import tv.mannyocrity.discordbot.model.TimeSlot;

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
        ts.setStreamDay("10:30pm", "12:30am", -5);
        log.info(ts.toString());
        schedule.setMonday(ts);
        schedule.setWednesday(ts);
        schedule.setFriday(ts);
        schedule.setSaturday(ts);

        ts = new TimeSlot();
        ts.setStreamingOff();
        schedule.setSunday(ts);
        schedule.setTuesday(ts);
        schedule.setThursday(ts);

        // EXECUTE
        toTest.saveSchedule(schedule);

        // VALIDATE
    }
}
