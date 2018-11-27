package tv.mannyocrity.discordbot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import tv.mannyocrity.discordbot.exception.TimeConversionException;
import tv.mannyocrity.discordbot.utils.TimeConversion;

import java.time.DayOfWeek;

/**
 * Model for holding the time of the day a streamer will be streaming.
 */
@Slf4j
@NoArgsConstructor
class TimeSlot {
    /**
     * Which day of the week this object represents.
     */
    @Setter
    @Getter
    private DayOfWeek day;
    /**
     * The start time of the stream.
     */
    @Getter
    private String startTime;
    /**
     * The end time of the stream.
     */
    @Getter
    private String endTime;
    /**
     * Is this an off day for the streamer.
     */
    @Getter
    private boolean off = false;

    /**
     * Converts the start and end time to UTC timezone and then sets it.
     *
     * @param startTimeXYZ - Start time of stream in String format to set.
     * @param endTimeXYZ - End time of stream in String format to set.
     * @param offset - The number of hours difference from UTC that the time value is.
     * @throws TimeConversionException - if the time cannot be parsed correctly.
     */
    void setStreamDay(final String startTimeXYZ, final String endTimeXYZ, final int offset)
            throws TimeConversionException {
        this.startTime = TimeConversion.convertToUTC(startTimeXYZ, offset);
        this.endTime = TimeConversion.convertToUTC(endTimeXYZ, offset);
        this.off = false;
    }
    /**
     * Sets off day to true and nulls out start and end times.
     */
    void setOffDay() {
        off = true;
        startTime = null;
        endTime = null;
    }
}
