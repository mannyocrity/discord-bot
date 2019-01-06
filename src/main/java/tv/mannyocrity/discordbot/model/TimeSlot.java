package tv.mannyocrity.discordbot.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import tv.mannyocrity.discordbot.exception.TimeConversionException;
import tv.mannyocrity.discordbot.utils.TimeConversion;

/**
 * Model for holding the time of the day a streamer will be streaming.
 */
@Slf4j
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TimeSlot {
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
     * Is this an streaming day for the streamer.
     */
    @Getter
    private boolean streaming = false;

    // TODO: What about support days?!?!?!?!?

    /**
     * Converts the start and end time to UTC timezone.
     *
     * @param startTimeXYZ - Start time of stream in String format to set.
     * @param endTimeXYZ   - End time of stream in String format to set.
     * @param offset       - The number of hours difference from UTC that the time value is.
     * @throws TimeConversionException - if the time cannot be parsed correctly.
     */
    public final void setStreamDay(final String startTimeXYZ, final String endTimeXYZ, final int offset)
            throws TimeConversionException {
        this.startTime = TimeConversion.convertToUTC(startTimeXYZ, offset);
        this.endTime = TimeConversion.convertToUTC(endTimeXYZ, offset);
        this.streaming = true;
    }

    /**
     * Sets streaming day to false and nulls out start and end times.
     */
    public final void setStreamingOff() {
        streaming = false;
        startTime = null;
        endTime = null;
    }
}
