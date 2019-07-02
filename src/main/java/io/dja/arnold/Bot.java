package io.dja.arnold;

import io.dja.arnold.command.Help;
import io.dja.arnold.command.heartbeat.Ping;
import io.dja.arnold.command.registry.RegistryStore;
import io.sentry.Sentry;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bot {
    
    private static final Logger logger = LoggerFactory.getLogger(Bot.class);
    
    private static final RegistryStore registryStore = new RegistryStore();
    
    public static void main(String[] args) {
        Sentry.init();
        
        FallbackLoggerConfiguration.setDebug(true);
        FallbackLoggerConfiguration.setTrace(true);
        
        logger.info("Initializing DiscordApiBuilder");
        DiscordApi api = new DiscordApiBuilder()
                .setToken(System.getenv("DISCORD_API_TOKEN"))
                .login()
                .join();
        
        logger.info("Initializing listeners");
        api.addMessageCreateListener(new Ping(registryStore));
        api.addMessageCreateListener(new Help(registryStore));

        logger.debug("Available commands: " + registryStore.availableCommands());
    }
    
}
