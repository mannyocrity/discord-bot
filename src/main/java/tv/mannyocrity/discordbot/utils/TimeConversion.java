package tv.mannyocrity.discordbot.utils;

import lombok.extern.slf4j.Slf4j;
import tv.mannyocrity.discordbot.exception.TimeConversionException;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper methods to assist with converting to and from UTC.
 */
@Slf4j
public final class TimeConversion {
    /**
     * The time format used '10:30pm'.
     */
    static final String TIME_PATTERN = "h:mma";

    /**
     * Utility class should not have a public constructor.
     */
    private TimeConversion() {
    }

    /**
     * Converts a time from UTC to a specified timezone.
     *
     * @param time   - time to convert in 10:30 PM format.
     * @param offset - The number of hours difference from UTC that the time value will be set tos.
     * @return - String of time converted from UTC.
     * @throws TimeConversionException - if the time cannot be parsed correctly.
     */
    public static String convertFromUTC(final String time, final int offset) throws TimeConversionException {
        ZoneOffset zoneOffset = validateOffset(offset);
        return convertTimezone(time, ZoneOffset.UTC, zoneOffset);
    }

    /**
     * Converts a time from a specified timezone to UTC.
     *
     * @param time   - time to convert in 10:30 PM format.
     * @param offset - The number of hours difference from UTC that the time value is.
     * @return - String of time converted to UTC.
     * @throws TimeConversionException - if the time cannot be parsed correctly.
     */
    public static String convertToUTC(final String time, final int offset) throws TimeConversionException {
        ZoneOffset zoneOffset = validateOffset(offset);
        return convertTimezone(time, zoneOffset, ZoneOffset.UTC);
    }

    /**
     * Validates that the offset is in the acceptable range.
     *
     * @param offset - The number of hours difference from UTC that the time value is.
     * @return - offset value as a ZoneOffset object.
     * @throws TimeConversionException - when the offset is not within the valid range.
     */
    static ZoneOffset validateOffset(final int offset) throws TimeConversionException {
        try {
            return ZoneOffset.ofHours(offset);
        } catch (DateTimeException e) {
            log.error(e.getMessage());
            throw new TimeConversionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a time String "10:30 pm" with a given timezone offset to a different time using a
     * destination timezone offset.
     *
     * @param time              - The Time to validate. Should be in the format of {@value #TIME_PATTERN}
     * @param currentOffset     - The current timezone offset the time value is set to.
     * @param destinationOffset - The timezone offset we are setting the time value to.
     * @return - String of time converted to the endZone.
     * @throws TimeConversionException - if the time cannot be parsed correctly.
     */
    static String convertTimezone(final String time, final ZoneOffset currentOffset, final ZoneOffset destinationOffset)
            throws TimeConversionException {

        try {
            log.debug("Converting {}.", time);
            // Make sure AM/PM are capitalized.
            String timeStr = time.replace("am", "AM").replace("pm", "PM");

            // Validate that the Time is in proper format.
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
            LocalTime localTime = LocalTime.parse(timeStr, timeFormatter);

            // Set time to given timezone offset.
            OffsetTime offsetTime = localTime.atOffset(currentOffset);
            // Get UTC from time zoned time.
            return timeFormatter.format(offsetTime.withOffsetSameInstant(destinationOffset));

        } catch (DateTimeParseException e) {
            log.error(e.getMessage());
            throw new TimeConversionException(e.getMessage(), e);
        }
    }
}
