package tv.mannyocrity.discordbot.command;

import java.util.List;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import tv.mannyocrity.discordbot.BotUtils;
import tv.mannyocrity.discordbot.Command;

public class TestCommand implements Command {

    @Override
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        BotUtils.sendMessage(event.getChannel(), "You ran the test command with args: " + args);
    }
}
