package tv.mannyocrity.discordbot.command;

import org.apache.commons.cli.ParseException;
import org.junit.Test;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;

public class ScheduleCommandTest {
    ScheduleCommand toTest;

    @Test
    public void getCommandLineOptions() throws ParseException {
        // SETUP
        List<String> commandArgs = Arrays.asList("-m", "10:30pm-1:00am", "-t", "10:30pm-1:00am");
        toTest = new ScheduleCommand();

        // EXECUTE
//        toTest.getCommandLineOptions();

        // VERIFY
    }
    // todo: mon=10:30pm-1:00am

    @Test
    public void runCommandReturnCommandLienHelp() throws ParseException {
        // SETUP
        List<String> commandArgs = Arrays.asList("-h");
        toTest = new ScheduleCommand();

        // EXECUTE
        toTest.runCommand(mock(MessageReceivedEvent.class), commandArgs);

        // VERIFY
    }
}
