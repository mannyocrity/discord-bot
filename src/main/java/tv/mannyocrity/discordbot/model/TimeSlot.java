package tv.mannyocrity.discordbot.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import tv.mannyocrity.discordbot.exception.TimeConversionException;
import tv.mannyocrity.discordbot.utils.TimeConversion;

import java.time.DayOfWeek;

/**
 * Model for holding the time of the day a streamer will be streaming.
 */
@Slf4j
@Data
class TimeSlot {
    /**
     * Which day of the week this object represents.
     */
    private DayOfWeek day;
    /**
     * The start time of the stream.
     */
    private String startTime;
    /**
     * The end time of the stream.
     */
    private String endTime;

    /**
     * Is this an off day for the streamer.
     */
    private Boolean off;

    /**
     * Converts the start time to UTC timezone and then sets it.
     *
     * @param time - Time in String format to set.
     * @param zone - The timezone the time is from.
     * @throws TimeConversionException - if the time cannot be parsed correctly.
     */
    void setStartTime(final String time, final String zone) throws TimeConversionException {
        startTime = TimeConversion.convertToUTC(time, zone);
    }

    /**
     * Converts the end time to UTC timezone and then sets it.
     *
     * @param time - Time in String format to set.
     * @param zone - The timezone the time is from.
     * @throws TimeConversionException - if the time cannot be parsed correctly.
     */
    void setEndTime(final String time, final String zone) throws TimeConversionException {
        endTime = TimeConversion.convertToUTC(time, zone);
    }
}
