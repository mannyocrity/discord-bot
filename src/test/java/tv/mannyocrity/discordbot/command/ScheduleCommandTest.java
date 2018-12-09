package tv.mannyocrity.discordbot.command;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ScheduleCommandTest {
    ScheduleCommand toTest;

    @Test
    public void parseCommands() throws ParseException {
        // SETUP
      List<String> commandArgs = Arrays.asList("-m", "10:30pm-1:00am");
        toTest = new ScheduleCommand();

        // EXECUTE
        toTest.parseCommands(commandArgs);

        // VERIFY
    }
}
