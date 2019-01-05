package tv.mannyocrity.discordbot.facade;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import lombok.extern.slf4j.Slf4j;
import tv.mannyocrity.discordbot.model.Schedule;

/**
 * Facade for accessing DynamoDb.
 */
@Slf4j
public class DynamoDBClientFacade {
    /**
     * Amazon SQS Client instance.
     */
    private final AmazonDynamoDB dbClient;
    /**
     * Allows mapping of client-side classes to DynamoDB tables.
     */
    private DynamoDBMapper dbMapper;

    /**
     * Constructor to create a new Amazon DynamoDB Client facade.
     */
    public DynamoDBClientFacade() {
        dbClient = AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(new EnvironmentVariableCredentialsProvider())
                .withRegion(Regions.US_EAST_1)
                .build();
        dbMapper = new DynamoDBMapper(dbClient);
    }

    /**
     * This all tables in DynamoDB.
     */
    public final void listTables() {
        ListTablesResult tables = dbClient.listTables();
        for (String tableName : tables.getTableNames()) {
            log.info(tableName);
        }
    }

    /**
     * Save a schedule object to the dynamoDB.
     * @param schedule - streamer schedule to save.
     */
    public final void saveSchedule(final Schedule schedule) {
        dbMapper.save(schedule);
    }
}
