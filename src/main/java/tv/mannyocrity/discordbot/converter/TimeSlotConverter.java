package tv.mannyocrity.discordbot.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import lombok.extern.slf4j.Slf4j;
import tv.mannyocrity.discordbot.model.Activity;
import tv.mannyocrity.discordbot.model.TimeSlot;

import java.util.Arrays;
import java.util.List;

/**
 * Dynamo DB Type converter class for TimeSlot.
 */
@Slf4j
public class TimeSlotConverter implements DynamoDBTypeConverter<String, TimeSlot> {
    /**
     * used for the DynamoDB Type conversion of the TimeSlots.
     */
    private static final String TIME_SEPARATOR = ".";

    @Override
    public final String convert(final TimeSlot timeSlot) {
        log.info(timeSlot.toString());
        String s;
        if (timeSlot.getActivity() == Activity.STREAMING) {
            s = timeSlot.getStartTime() + TIME_SEPARATOR + timeSlot.getEndTime();
        } else {
            s = timeSlot.getActivity().toString();
        }
        log.info(s);
        return s;
    }

    @Override
    public final TimeSlot unconvert(final String s) {
        List<String> times = Arrays.asList(s.split(TIME_SEPARATOR));
        String startTime = times.get(0);
        String endTime = times.get(1);

        TimeSlot ts = new TimeSlot();
//        try {
//            ts.setStreamDay(startTime, endTime, 0);
//        } catch (TimeConversionException e) {
//            log.error("Unable to convert {} to a TimeSlot.", s);
//            return null;
//        }

        return ts;
    }
}
