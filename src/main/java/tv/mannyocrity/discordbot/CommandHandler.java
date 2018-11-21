package tv.mannyocrity.discordbot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import tv.mannyocrity.discordbot.command.Command;
import tv.mannyocrity.discordbot.command.Ping;
import tv.mannyocrity.discordbot.command.Schedule;
import tv.mannyocrity.discordbot.command.TestCommand;

/**
 *
 */
@Slf4j
public class CommandHandler {

    /** A static map of commands mapping from command string to the functional impl. */
    private static Map<String, Class> commandMap = new HashMap<>();

    // Statically populate the commandMap with the known commands.
    static {
        commandMap.put("ping", Ping.class);
        commandMap.put("schedule", Schedule.class);
        commandMap.put("testcommand", TestCommand.class);
    }

    /**
     * @param event - message received from discord.
     */
    @EventSubscriber
    public final void onMessageReceived(final MessageReceivedEvent event) {

        // Note for error handling, you'll probably want to log failed commands with a logger or sout
        // In most cases it's not advised to annoy the user with a reply in case they didn't intend to trigger a
        // command anyway, such as a user typing ?notacommand, the bot should not say "notacommand" doesn't exist in
        // most situations. It's partially good practise and partially developer preference

        // Given a message "/test arg1 arg2", argArray will contain ["/test", "arg1", "arg"]
        String[] argArray = event.getMessage().getContent().split(" ");

        // First ensure at least the command and prefix is present, the arg length can be handled by your command func
        if (argArray.length == 0) {
            return;
        }

        // Check if the first arg (the command) starts with the prefix defined in the utils class
        if (!argArray[0].startsWith(BotUtils.BOT_PREFIX)) {
            return;
        }

        // Extract the "command" part of the first arg out by just ditching the first character and making everything
        // lower case
        String commandStr = argArray[0].substring(1).toLowerCase();

        // Load the rest of the args in the array into a List for safer access
        List<String> argsList = new ArrayList<>(Arrays.asList(argArray));
        argsList.remove(0); // Remove the command

        if (commandMap.containsKey(commandStr)) {
            try {
                Class<?> clazz = commandMap.get(commandStr);
                Command cmd = (Command) clazz.newInstance();
                cmd.runCommand(event, argsList);
            } catch (InstantiationException | IllegalAccessException e) {
                log.error("Failed to execute command {}", commandStr, e);
            }
        }

    }

}
