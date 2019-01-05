package tv.mannyocrity.discordbot.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Getter;

/**
 * Weekly schedule for a streamer.
 */
@DynamoDBTable(tableName = "schedule")
public class Schedule {
    /**
     * The DynamoDB Table name.
     */
    @DynamoDBIgnore
    @Getter
    public static final String TABLE_NAME = "schedule";
    /**
     * The streamer's discord user Id.
     */
    private String discordId;
    /**
     * The streamer's twitch user Id.
     */
    private String twitchId;

    /** sunday's timeslot. */
    private TimeSlot sunday;
    /** monday's timeslot. */
    private TimeSlot monday;
    /** tuesday's timeslot. */
    private TimeSlot tuesday;
    /** wednesday's timeslot. */
    private TimeSlot wednesday;
    /** thursday's timeslot. */
    private TimeSlot thursday;
    /** friday's timeslot. */
    private TimeSlot friday;
    /** saturday's timeslot. */
    private TimeSlot saturday;

    /**
     * Getter created for DynamoDB table access.
     * @return TimeSlot for Sunday.
     */
    @DynamoDBAttribute(attributeName = "sunday")
    public final TimeSlot getSunday() {
        return sunday;
    }

    /**
     * Setter created for DynamoDb table access.
     * @param sunday - Sunday's TimeSlot to set.
     */
    public final void setSunday(final TimeSlot sunday) {
        this.sunday = sunday;
    }

    /**
     * Getter created for DynamoDB table access.
     * @return TimeSlot for Monday.
     */
    @DynamoDBAttribute(attributeName = "monday")
    public final TimeSlot getMonday() {
        return monday;
    }

    /**
     * Setter created for DynamoDb table access.
     * @param monday - Monday's TimeSlot to set.
     */
    public final void setMonday(final TimeSlot monday) {
        this.monday = monday;
    }

    /**
     * Getter created for DynamoDB table access.
     * @return TimeSlot for Tuesday.
     */
    @DynamoDBAttribute(attributeName = "tuesday")
    public final TimeSlot getTuesday() {
        return tuesday;
    }

    /**
     * Setter created for DynamoDb table access.
     * @param tuesday - Tuesday's TimeSlot to set.
     */
    public final void setTuesday(final TimeSlot tuesday) {
        this.tuesday = tuesday;
    }

    /**
     * Getter created for DynamoDB table access.
     * @return TimeSlot for Wednesday.
     */
    @DynamoDBAttribute(attributeName = "wednesday")
    public final TimeSlot getWednesday() {
        return wednesday;
    }

    /**
     * Setter created for DynamoDb table access.
     * @param wednesday - Wednesday's TimeSlot to set.
     */
    public final void setWednesday(final TimeSlot wednesday) {
        this.wednesday = wednesday;
    }

    /**
     * Getter created for DynamoDB table access.
     * @return TimeSlot for Thursday.
     */
    @DynamoDBAttribute(attributeName = "thursday")
    public final TimeSlot getThursday() {
        return thursday;
    }

    /**
     * Setter created for DynamoDb table access.
     * @param thursday - Thursday's TimeSlot to set.
     */
    public final void setThursday(final TimeSlot thursday) {
        this.thursday = thursday;
    }

    /**
     * Getter created for DynamoDB table access.
     * @return TimeSlot for Friday.
     */
    @DynamoDBAttribute(attributeName = "friday")
    public final TimeSlot getFriday() {
        return friday;
    }

    /**
     * Setter created for DynamoDb table access.
     * @param friday - Friday's TimeSlot to set.
     */
    public final void setFriday(final TimeSlot friday) {
        this.friday = friday;
    }

    /**
     * Getter created for DynamoDB table access.
     * @return TimeSlot for Saturday.
     */
    @DynamoDBAttribute(attributeName = "saturday")
    public final TimeSlot getSaturday() {
        return saturday;
    }

    /**
     * Setter created for DynamoDb table access.
     * @param saturday - Saturday's TimeSlot to set.
     */
    public final void setSaturday(final TimeSlot saturday) {
        this.saturday = saturday;
    }

    /**
     * Getter created for DynamoDb table access.
     * @return - Streamers twitchId.
     */
    @DynamoDBHashKey(attributeName = "twitchId")
    public final String getTwitchId() {
        return twitchId;
    }

    /**
     * Setter created for DynamoDb table access.
     * @param twitchId - Streamer's twitchID.
     */
    public final void setTwitchId(final String twitchId) {
        this.twitchId = twitchId;
    }

    /**
     * Getter created for DynamoDb table access.
     * @return - List of TimeSlots for streamers schedule.
     */
    @DynamoDBAttribute(attributeName = "discordId")
    public final String getDiscordId() {
        return discordId;
    }

    /**
     * Setter created for DynamoDb table access.
     * @param discordId - Streamer's discordID.
     */
    public final void setDiscordId(final String discordId) {
        this.discordId = discordId;
    }
}
