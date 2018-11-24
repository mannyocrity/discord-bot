package tv.mannyocrity.discordbot.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Helper methods to assist with converting to and from UTC.
 */
@Slf4j
public final class TimeConversion {
    /**
     * The time format used '10:30 PM'.
     */
    private static final String TIME_PATTERN = "hh:mm a";

    /**
     * Date Formatter for converting to UTC timezone.
     */
    private static DateFormat utcFormat;

    static {
        utcFormat = new SimpleDateFormat(TIME_PATTERN);
        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    /**
     * Utility class should not have a public constructor.
     */
    private TimeConversion() {
    }

    /**
     * Converts a time from UTC to a specified timezone.
     *
     * @param time - time to convert in 10:30 PM format.
     * @param zone - Timezone to convert to.
     * @return - String of time converted from UTC.
     * @throws ParseException - Throws if there is an issue with the conversion.
     */
    public static String convertFromUTC(final String time, final String zone) throws ParseException {
        DateFormat zoneFormat = new SimpleDateFormat(TIME_PATTERN, Locale.ROOT);
        zoneFormat.setTimeZone(TimeZone.getTimeZone(zone));

        try {
            return zoneFormat.format(utcFormat.parse(time));
        } catch (ParseException e) {
            String errMsg = "Incorrect format '" + time + "' needs to be in format '10:30 PM'.";
            log.error(errMsg, time);
            throw new ParseException(errMsg, e.getErrorOffset());
        }
    }

    /**
     * Converts a time from a specified timezone to UTC.
     *
     * @param time - time to convert in 10:30 PM format.
     * @param zone - Timezone to convert from.
     * @return - String of time converted to UTC.
     * @throws ParseException - Throws if there is an issue with the conversion.
     */
    public static String convertToUTC(final String time, final String zone) throws ParseException {
        validateTime(time);

        DateFormat zoneFormat = new SimpleDateFormat(TIME_PATTERN, Locale.ROOT);
        zoneFormat.setTimeZone(TimeZone.getTimeZone(zone));

        try {
            return utcFormat.format(zoneFormat.parse(time));
        } catch (ParseException e) {
            String errMsg = "Incorrect format '" + time + "' needs to be in format '10:30 PM'.";
            log.error(errMsg, time);
            throw new ParseException(errMsg, e.getErrorOffset());
        }
    }

    /**
     * SimpleDateFormat does not validate if a Time is a valid 12 hour time.  We'll use DateTimeFormatter
     * and LocalTime to validate the format is correct before changing the timezone.
     *
     * If the time is not valid we will let the exception propagate up.
     * @param time - The Time to validate. Should be in the format of {@value #TIME_PATTERN}
     */
    static void validateTime(final String time) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        LocalTime.parse(time, timeFormatter);
    }
}
