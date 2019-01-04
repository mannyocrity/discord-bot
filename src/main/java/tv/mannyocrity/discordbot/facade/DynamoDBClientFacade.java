package tv.mannyocrity.discordbot.facade;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * Facade for accessing DynamoDb.
 */
@Slf4j
public class DynamoDBClientFacade {
    /** Amazon SQS Client instance. */
    private final AmazonDynamoDB dbClient;

    /**
     * Constructor to create a new Amazon DynamoDB Client facade.
     */
    public DynamoDBClientFacade() {
        dbClient = AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(new EnvironmentVariableCredentialsProvider())
                .withRegion(Regions.US_EAST_1)
                .build();
    }
}
