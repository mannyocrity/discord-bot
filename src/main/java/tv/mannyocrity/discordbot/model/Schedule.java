package tv.mannyocrity.discordbot.model;

import lombok.Data;

import java.util.List;
import java.util.TimeZone;

/**
 * Weekly schedule for a streamer.
 */
@Data
public class Schedule {
    /** The streamer's discord user Id. */
    private String discordId;
    /** The streamer's twitch user Id. */
    private String twitchId;
    /** The timezone the streamer is in. */
    private TimeZone timeZone;
    /** The list of times the Streamer streams. */
    private List<TimeSlot> days;
}
