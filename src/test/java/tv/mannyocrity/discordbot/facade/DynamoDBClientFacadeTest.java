package tv.mannyocrity.discordbot.facade;

import org.junit.Test;
import tv.mannyocrity.discordbot.exception.TimeConversionException;
import tv.mannyocrity.discordbot.model.Schedule;
import tv.mannyocrity.discordbot.model.TimeSlot;

public class DynamoDBClientFacadeTest {

    @Test
    public void Connection() {
        DynamoDBClientFacade client = new DynamoDBClientFacade();
        client.listTables();
    }

    @Test
    public void saveSchedule() throws TimeConversionException {
        // SETUP
        DynamoDBClientFacade client = new DynamoDBClientFacade();

        Schedule schedule = new Schedule();
        schedule.setTwitchId("Fooby");
        schedule.setDiscordId("Fooby-BW");

        TimeSlot ts = new TimeSlot();
        ts.setStreamDay("10:30pm", "12:30am", -5);
        schedule.setMonday(ts);

        // EXECUTE

        // VALIDATE
    }
}
