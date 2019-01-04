package tv.mannyocrity.discordbot.facade;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DynamoDBUnitTestBase {
    protected static AmazonDynamoDB amazonDynamoDB = null;
    protected static ProvisionedThroughput throughput;
    protected static DynamoDBMapper dynamoDBMapper;

    @BeforeClass
    public static void baseSetup() {
        amazonDynamoDB = DynamoDBEmbedded.create().amazonDynamoDB();

        throughput = new ProvisionedThroughput()
            .withReadCapacityUnits(5L)
            .withWriteCapacityUnits(5L);

        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
    }

    @AfterClass
    public static void baseTeardown() {
        if (amazonDynamoDB != null) {
            amazonDynamoDB.shutdown();
            amazonDynamoDB = null;
        }
    }

    /**
     * Deletes and then creates tables in DynamoDBLocal.  This is used to clean up tables between tests.
     * @param tableName table name to cleanup.
     * @param clazz Class to use to generate new table.
     * @throws InterruptedException thrown
     */
    protected void cleanUpTable(final String tableName, Class clazz) throws InterruptedException {
        // Delete table because it may have items in it.
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        Table table = dynamoDB.getTable(tableName);
        table.delete();
        table.waitForDelete();

        // Create empty table
        CreateTableRequest createRequest = dynamoDBMapper.generateCreateTableRequest(clazz);
        createRequest.setProvisionedThroughput(throughput);
        amazonDynamoDB.createTable(createRequest);
    }
}
