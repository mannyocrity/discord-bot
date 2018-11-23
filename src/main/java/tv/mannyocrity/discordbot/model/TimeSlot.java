package tv.mannyocrity.discordbot.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Date;

/**
 * Model for holding the time of the day a streamer will be streaming.
 */
@Slf4j
@Data
class TimeSlot {
    /** Date parser used to verify that the Date String is in teh correct format. */
    static final SimpleDateFormat PARSE_FORMAT = new SimpleDateFormat("hh:mm a");

    /** Which day of the week this object represents. */
    private DayOfWeek day;
    /** The start time of the stream. */
    private String startTime;
    /** The end time of the stream. */
    private Date endTime;
    /** Is this an off day for the streamer. */
    private Boolean off;

    /**
     * Sets teh time Start time string after it is verifies to be in teh correct format.
     * @param time - Time in String format to set.
     * @throws ParseException - if the time cannot be parsed correctly.
     */
    void setStartTime(final String time) throws ParseException {
        try {
            PARSE_FORMAT.parse(time);
            startTime = time;
        } catch (ParseException e) {
            String errMsg = "Incorrect format " + time + " needs to be in format '10:30 PM'.";
            log.error(errMsg, time);
            throw new ParseException(errMsg, e.getErrorOffset());
        }
    }
}
