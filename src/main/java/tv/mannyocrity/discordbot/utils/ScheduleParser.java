package tv.mannyocrity.discordbot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tv.mannyocrity.discordbot.model.Schedule;

import java.io.IOException;

/**
 * Utility class for helping with serialization and deserialization.
 */
public final class ScheduleParser {
    /** Jackson object mapper. */
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Utility class does not need a public constructor.
     */
    private ScheduleParser() {
    }

    /**
     * Deserialize a Schedule JSON string in to a Schedule object.
     *
     * @param schedule - JSON string representing Schedule object.
     * @return - Schedule POJO
     * @throws IOException - when it cannot deserialize the String.
     */
    public static Schedule deserializeSchedule(final String schedule) throws IOException {
        return objectMapper.readValue(schedule, Schedule.class);
    }

    /**
     * Serializes a Schedule POJO into a JSON String.
     *
     * @param schedule - Schedule POJO.
     * @return - JSON String representing Schedule.
     * @throws JsonProcessingException - if invalid object.
     */
    public static String serializeSchedule(final Schedule schedule) throws JsonProcessingException {
        return objectMapper.writeValueAsString(schedule);
    }

}
