package tv.mannyocrity.discordbot;

import lombok.extern.slf4j.Slf4j;
import sx.blah.discord.api.IDiscordClient;

@Slf4j
public class MainRunner {

    public static void main(String[] args){

        if(args.length != 1){
            log.error("Please enter the bots token as the first argument e.g java -jar thisjar.jar tokenhere");
            return;
        }

        IDiscordClient cli = BotUtils.getBuiltDiscordClient(args[0]);

        // Register a listener via the EventSubscriber annotation which allows for organisation and delegation of events
        cli.getDispatcher().registerListener(new CommandHandler());

        // Only login after all events are registered otherwise some may be missed.
        cli.login();

    }

}
