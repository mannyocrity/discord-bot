package tv.mannyocrity.discordbot;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.List;

/**
 * Interface for a command to be implemented in the command map.
 */
public interface Command {

    /**
     * contract for executing a command.
     * @param event - command to execute
     * @param args - command arguments
     */
    void runCommand(MessageReceivedEvent event, List<String> args);

}
