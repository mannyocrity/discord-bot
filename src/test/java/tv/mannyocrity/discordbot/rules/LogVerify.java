package tv.mannyocrity.discordbot.rules;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertFalse;
import static org.mockito.AdditionalAnswers.answerVoid;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LogVerify implements TestRule {

    private Class<?> type;
    private Appender mockAppender;
    private List<LogEvent> capturedEvents = new ArrayList<>();
    private Logger logger;

    public class LogStatement extends Statement {
        /**
         * @see #LogStatement(org.junit.runners.model.Statement)
         */
        private final Statement statement;

        /**
         * @param statement the wrapping statement that we save so that we can execute it (the statement represents
         *                  the test to execute).
         */
        LogStatement(Statement statement) {
            this.statement = statement;
        }

        @Override
        public void evaluate() throws Throwable {
            // Setup Logback to catch log calls
            before();

            try {
                // Run the test
                this.statement.evaluate();
            } finally {
                // Remove Logback setup
                after();
            }
        }

        /**
         * Setup Logback capturing.
         */
        private void before() {
            // prepare the appender so Log4j likes it
            mockAppender = mock(Appender.class);
            when(mockAppender.getName()).thenReturn("MockAppender");
            when(mockAppender.isStarted()).thenReturn(true);

            // when append is called, convert the event to immutable and add it to the event list
            doAnswer(answerVoid((LogEvent event) -> capturedEvents.add(event.toImmutable())))
                .when(mockAppender).append(any());

            logger = (Logger) LogManager.getLogger(type);
            logger.addAppender(mockAppender);
            logger.setLevel(Level.INFO);
        }

        /**
         * Stop Logback capturing.
         */
        private void after() {
            logger.removeAppender(mockAppender);
        }
    }

    @Override
    public Statement apply(Statement statement, Description description) {
        return new LogStatement(statement);
    }

    /**
     * @param type the logging class type for which to capture logs
     */
    protected void recordLoggingForType(Class<?> type) {
        this.type = type;
    }

    // inspecting messages involves just using the list of captured events
    public void verifyLogMessages(String message, Level level) {

        List<String> actualMessages = new ArrayList<>();
        for (LogEvent loggingEvent : capturedEvents) {
            if (loggingEvent.getLevel() == level) {
                actualMessages.add(loggingEvent.getMessage().getFormattedMessage());
            }
        }

        assertFalse("There are no log messages of type " + level.toString(), actualMessages.isEmpty());
        assertThat(actualMessages, hasItem(message));
    }
}
