package tv.mannyocrity.discordbot.command;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;

/**
 * ScheduleCommand command.
 */
@Slf4j
public class ScheduleCommand implements Command {

    @Override
    public final void runCommand(final MessageReceivedEvent event, final List<String> argList) {
        try {
            CommandLine cmd = parseCommands(argList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses command line arguments.
     *
     * @param argList - List of arguments to parse.
     * @return - Successfully parsed CommandLine Object.
     * @throws ParseException - when there are any issues with parsing the argsList.
     */
    final CommandLine parseCommands(final List<String> argList) throws ParseException {
        // Convert List back to array for command line parsing.
        String[] args = new String[argList.size()];
        args = argList.toArray(args);

        Options options = new Options();

        OptionGroup group = new OptionGroup();
        group.addOption(Option.builder("m").longOpt("mon").hasArg()
                .desc("Monday's schedule.  (-m 10:30pm-1:00am or -m off if you are not streaming)")
                .build());
        group.addOption(Option.builder("t").longOpt("tue").hasArg()
                .desc("Tuesday's schedule.  (-t 10:30pm-1:00am or -t off if you are not streaming)")
                .build());
        group.addOption(Option.builder("w").longOpt("wed").hasArg()
                .desc("Wednesday's schedule.  (-w 10:30pm-1:00am or -w off if you are not streaming)")
                .build());
        group.addOption(Option.builder("r").longOpt("thur").hasArg()
                .desc("Thursday's schedule.  (-r 10:30pm-1:00am or -r off if you are not streaming)")
                .build());
        group.addOption(Option.builder("f").longOpt("fri").hasArg()
                .desc("Friday's schedule.  (-f 10:30pm-1:00am or -f off if you are not streaming)")
                .build());
        group.addOption(Option.builder("s").longOpt("sat").hasArg()
                .desc("Saturday's schedule.  (-s 10:30pm-1:00am or -s off if you are not streaming)")
                .build());
        group.addOption(Option.builder("u").longOpt("sun").hasArg()
                .desc("Sunday's schedule.  (-u 10:30pm-1:00am or -u off if you are not streaming)")
                .build());

        options.addOption("o", "offset", true, "Your current timezone offset from UTC.");
        options.addOptionGroup(group);

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("ScheduleCommand", options);

        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);

    }
}
