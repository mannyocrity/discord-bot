package tv.mannyocrity.discordbot;

import lombok.extern.slf4j.Slf4j;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RequestBuffer;

/**
 * Helper functions to make certain aspects of the bot easier to use.
 */
@Slf4j
public final class BotUtils {

    /** Prefix used for all commands that will be processed by this Bot. */
    static final String BOT_PREFIX = "/";
    /** Environment variable token used to find the discord token. */
    static final String DISCORD_TOKEN_ENV_VAR = "DISCORD_TOKEN";

    /**
     * Handles the creation and getting of a IDiscordClient object for a token.
     *
     * @param token - Discord client token
     * @return - discord client object
     */
    static IDiscordClient getBuiltDiscordClient(final String token) {

        // The ClientBuilder object is where you will attach your params for configuring the instance of your bot.
        // Such as withToken, setDaemon etc
        return new ClientBuilder()
                .withToken(token)
                .build();
    }

    /** public static classes should not have a public constructor. */
    private BotUtils() {
    }

    /**
     * sends a message back to bot.
     *
     * @param channel - the channel message is going to.
     * @param message - message to be sent.
     */
    public static void sendMessage(final IChannel channel, final String message) {

        // This might look weird but it'll be explained in another page.
        RequestBuffer.request(() -> {
            try {
                channel.sendMessage(message);
            } catch (DiscordException e) {
                log.error("Message could not be sent with error: ", e);
            }
        });
    }
}
