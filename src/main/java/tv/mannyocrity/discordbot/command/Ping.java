package tv.mannyocrity.discordbot.command;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import tv.mannyocrity.discordbot.BotUtils;

/**
 * Ping command.
 */
@Slf4j
public class Ping implements Command {

    @Override
    public final void runCommand(final MessageReceivedEvent event, final List<String> args) {
        log.info("Executing ping command.");
        BotUtils.sendMessage(event.getChannel(), "Pong");
    }
}
