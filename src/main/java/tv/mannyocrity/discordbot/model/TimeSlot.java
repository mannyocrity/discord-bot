package tv.mannyocrity.discordbot.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import tv.mannyocrity.discordbot.exception.TimeConversionException;
import tv.mannyocrity.discordbot.utils.TimeConversion;

import java.util.Arrays;
import java.util.List;

/**
 * Model for holding the time of the day a streamer will be streaming.
 */
@Slf4j
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TimeSlot {
    /**
     * used for the DynamoDB Type conversion of the TimeSlots.
     */
    private static final String TIME_SEPARATOR = ".";

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
        this.off = false;
    }

    /**
     * Sets off day to true and nulls out start and end times.
     */
    public final void setOffDay() {
        off = true;
        startTime = null;
        endTime = null;
    }

    /**
     * Dynamo DB Type converter class for TimeSlot.
     */
    public static class TimeSlotConverter implements DynamoDBTypeConverter<String, TimeSlot> {

        @Override
        public final String convert(final TimeSlot timeSlot) {
            String s = timeSlot.getStartTime() + TIME_SEPARATOR + timeSlot.getEndTime();
            log.info(s);
            return s;
        }

        @Override
        public final TimeSlot unconvert(final String s) {
            List<String> times = Arrays.asList(s.split(TIME_SEPARATOR));
            String startTime = times.get(0);
            String endTime = times.get(1);

            TimeSlot ts = new TimeSlot();
            try {
                ts.setStreamDay(startTime, endTime, 0);
            } catch (TimeConversionException e) {
                log.error("Unable to convert {} to a TimeSlot.", s);
                return null;
            }

            return ts;
        }
    }
}
