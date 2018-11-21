package tv.mannyocrity.discordbot;

import lombok.extern.slf4j.Slf4j;
import sx.blah.discord.api.IDiscordClient;

/**
 * Bot main point of entry.
 */
@Slf4j
public final class MainRunner {

    /** Utility classes should not have a public constructor. */
    private MainRunner() {
    }

    /**
     * Main execution.
     *
     * @param args - passed in arguments.
     */
    public static void main(final String[] args) {

        // Make sure the discord token environment variable is set.
        String discordToken = System.getenv(BotUtils.DISCORD_TOKEN_ENV_VAR);
        if (discordToken == null) {
            log.error("Environment variable {} must be set to the correct Discord Bot Token.",
                    BotUtils.DISCORD_TOKEN_ENV_VAR);
            return;
        }

        IDiscordClient cli = BotUtils.getBuiltDiscordClient(discordToken);

        // Register a listener via the EventSubscriber annotation which allows for organisation and delegation of events
        cli.getDispatcher().registerListener(new CommandHandler());

        // Only login after all events are registered otherwise some may be missed.
        cli.login();

    }

}
