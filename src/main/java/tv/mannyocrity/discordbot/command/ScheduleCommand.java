package tv.mannyocrity.discordbot.command;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import tv.mannyocrity.discordbot.exception.TimeConversionException;
import tv.mannyocrity.discordbot.model.TimeSlot;
import tv.mannyocrity.discordbot.utils.CollectionsHelper;

import java.time.DayOfWeek;
import java.util.ArrayList;
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
     * String literal for offset value.
     */
    private static final String OFFSET = "offset=";

    /**
     * Regular Expression Pattern for representing the time format.
     */
    private static final String TIME_PATTERN = "(\\d{1,2}:\\d{2}[a|p|A|P]m)";

    /**
     * Regular Expression Pattern for representing the day format.
     */
    private static final String DAY_PATTERN = "^(\\w{3})=";

    /**
     * Regular Expression Pattern for representing the timeslot format.
     */
    private static final String TIME_SLOT_PATTERN = DAY_PATTERN + TIME_PATTERN + "-" + TIME_PATTERN;

    /**
     * Regular Expression Pattern for representing the off day format.
     */
    private static final String OFF_DAY_PATTERN = DAY_PATTERN + "off";

    /**
     * A static map of days mapping to DayOfWeek.
     */
    @Getter
    private static Map<String, DayOfWeek> dayMapping = new LinkedHashMap<>();

    /**
     * To fix magic number checkstyle error.
     */
    private static final int THREE = 3;

    // Statically populate the dayMap with the days of the week.
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

        log.info("Executing ScheduleCommand");
        List<String> days = new ArrayList<>();
        Integer offset = 0;
        for (String arg : argList) {
            // Remove offset value.
            if (arg.startsWith(OFFSET)) {
                String o = arg.replaceFirst(OFFSET, "");
                offset = Integer.parseInt(o);
                log.info("Offset: {}", offset);
            } else {
                days.add(arg);
            }
        }

        for (String day : days) {
            TimeSlot timeSlot = parseDay(day, offset);
            log.info(timeSlot.toString());
        }
    }

    /**
     * Returns user input as a time slot.
     *
     * @param value - String to parse.
     * @param offset - timezone offset.
     * @return - a valid TimeSlot object if formatting is correct or null if it is not.
     */
    final TimeSlot parseDay(final String value, final int offset) {
        Pattern timeSlotPattern = Pattern.compile(TIME_SLOT_PATTERN);
        Pattern offDayPattern = Pattern.compile(OFF_DAY_PATTERN);
        String day;
        String startTime;
        String endTime;
        TimeSlot timeSlot = new TimeSlot();

        Matcher timeSlotMatcher = timeSlotPattern.matcher(value);
        Matcher offDayMatcher = offDayPattern.matcher(value);
        if (timeSlotMatcher.find()) {
            if (dayMapping.containsKey(timeSlotMatcher.group(1))) {
                day = timeSlotMatcher.group(1);
                startTime = timeSlotMatcher.group(2);
                endTime = timeSlotMatcher.group(THREE);
            } else {
                log.error("Not a valid day of the week. {}", CollectionsHelper.getKeysFromMap(dayMapping).toString());
                return null;
            }

            try {
                timeSlot.setStreamDay(startTime, endTime, offset);
            } catch (TimeConversionException e) {
                // TODO: We should send a message to user that format is wrong.
                return null;
            }
        } else if (offDayMatcher.find()) {
            day = timeSlotMatcher.group(1);

            timeSlot.setOffDay();
        } else {
            log.error("'{}' does not match regular expression.", value);
            return null;
        }


        return timeSlot;
    }
}
