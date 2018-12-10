package tv.mannyocrity.discordbot.command;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import tv.mannyocrity.discordbot.exception.TimeConversionException;
import tv.mannyocrity.discordbot.model.TimeSlot;
import tv.mannyocrity.discordbot.utils.CollectionsHelper;

import java.time.DayOfWeek;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ScheduleCommand command.
 */
@Slf4j
public class ScheduleCommand implements Command {
    /**
     * Regular Expression Pattern for representing the time format.
     */
    private static final String TIME_PATTERN = "(\\d{1,2}:\\d{2}[a|p|A|P]m)";
    /**
     * Regular Expression Pattern for representing the day/time format.
     */
    private static final String DAY_PATTERN = "^(\\w{3})=" + TIME_PATTERN + "-" + TIME_PATTERN;

    /**
     * A static map of days mapping to DayOfWeek.
     */
    @Getter
    private static Map<String, DayOfWeek> dayMapping = new LinkedHashMap<>();

    // Statically populate the dayMap with the days of teh week.
    static {
        dayMapping.put("mon", DayOfWeek.MONDAY);
        dayMapping.put("tue", DayOfWeek.TUESDAY);
        dayMapping.put("wed", DayOfWeek.WEDNESDAY);
        dayMapping.put("thu", DayOfWeek.THURSDAY);
        dayMapping.put("fri", DayOfWeek.FRIDAY);
        dayMapping.put("sat", DayOfWeek.SATURDAY);
        dayMapping.put("sun", DayOfWeek.SUNDAY);
    }

    @Override
    public final void runCommand(final MessageReceivedEvent event, final List<String> argList) {

        for (String arg : argList) {
            parseDay(arg);
        }
    }

    /**
     * Returns user input as a time slot.
     *
     * @param value - String to parse
     * @return - a valid TimeSlot object if formatting is correct or null if it is not.
     */
    final TimeSlot parseDay(final String value) {
        Pattern pattern = Pattern.compile(DAY_PATTERN);
        String day;
        String startTime;
        String endTime;

        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            if (dayMapping.containsKey(matcher.group(1))) {
                day = matcher.group(1);
                startTime = matcher.group(2);
                endTime = matcher.group(3);
            } else {
                log.error("Not a valid day of the week. {}", CollectionsHelper.getKeysFromMap(dayMapping).toString());
                return null;
            }
        } else {
            log.error("'{}' does not match regular expression.", value);
            return null;
        }

        TimeSlot timeSlot = new TimeSlot();
        try {
            timeSlot.setStreamDay(startTime, endTime, 1);
            timeSlot.setDay(dayMapping.get(day));
        } catch (TimeConversionException e) {
            // TODO: We should send a message to user that format is wrong.
            return null;
        }
        return timeSlot;
    }
}
