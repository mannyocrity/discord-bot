package tv.mannyocrity.discordbot.utils;

import lombok.extern.slf4j.Slf4j;
import tv.mannyocrity.discordbot.exception.TimeConversionException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    static final String TIME_PATTERN = "hh:mm a";

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
     * @throws TimeConversionException - if the time cannot be parsed correctly.
     */
    public static String convertFromUTC(final String time, final String zone) throws TimeConversionException {
        DateFormat zoneFormat = new SimpleDateFormat(TIME_PATTERN, Locale.ROOT);
        zoneFormat.setTimeZone(TimeZone.getTimeZone(zone));

        return convertTimezone(time, utcFormat, zoneFormat);
    }

    /**
     * Converts a time from a specified timezone to UTC.
     *
     * @param time - time to convert in 10:30 PM format.
     * @param zone - Timezone to convert from.
     * @return - String of time converted to UTC.
     * @throws TimeConversionException - if the time cannot be parsed correctly.
     */
    public static String convertToUTC(final String time, final String zone) throws TimeConversionException {
        DateFormat zoneFormat = new SimpleDateFormat(TIME_PATTERN, Locale.ROOT);
        zoneFormat.setTimeZone(TimeZone.getTimeZone(zone));

        return convertTimezone(time, zoneFormat, utcFormat);
    }

    /**
     * SimpleDateFormat does not validate if a Time is a valid 12 hour time.  We'll use DateTimeFormatter
     * and LocalTime to validate the format is correct before changing the timezone.
     *
     * @param time - The Time to validate. Should be in the format of {@value #TIME_PATTERN}
     * @param startZone - timezone the time is in.
     * @param endZone - timezone we are switching the timezone too.
     * @return - String of time converted to the endZone.
     * @throws TimeConversionException - if the time cannot be parsed correctly.
     */
    static String convertTimezone(final String time, final DateFormat startZone, final DateFormat endZone)
            throws TimeConversionException {
        try {
            // Validate that the Time is in proper format.
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
            LocalTime.parse(time, timeFormatter);

            // Convert the time zone from start to end.
            return endZone.format(startZone.parse(time));
        } catch (DateTimeParseException | ParseException e) {
            log.error(e.getMessage());
            throw new TimeConversionException(e.getMessage(), e);
        }
    }
}
