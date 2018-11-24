package tv.mannyocrity.discordbot.model;

import lombok.Data;

import java.util.List;

/**
 * Weekly schedule for a streamer.
 */
@Data
public class Schedule {
    /** The streamer's discord user Id. */
    private String discordId;
    /** The streamer's twitch user Id. */
    private String twitchId;
    /** The list of times the Streamer streams. */
    private List<TimeSlot> days;
}
