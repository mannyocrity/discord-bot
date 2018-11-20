package tv.mannyocrity.discordbot.command;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import tv.mannyocrity.discordbot.BotUtils;
import tv.mannyocrity.discordbot.Command;

/**
 * TestCommand command.
 */
@Slf4j
public class TestCommand implements Command {

    @Override
    public final void runCommand(final MessageReceivedEvent event, final List<String> args) {
        log.info("Executing testcommand command with args: {}", args);
        BotUtils.sendMessage(event.getChannel(), "You ran the test command with args: " + args);
    }
}
