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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Bot {
    
    private static final Logger logger = LoggerFactory.getLogger(Bot.class);
    
    private static final RegistryStore registryStore = new RegistryStore();
    
    private static final Properties properties = new Properties();
    
    public static void main(String[] args) throws IOException {
        String gitHash = gitHash();
       
        // TODO: Build a config class that handles the fallthrough of config variable retrieval
        // That way we aren't relying solely on env vars
        
        Sentry.init();
        
        FallbackLoggerConfiguration.setDebug(true);
        FallbackLoggerConfiguration.setTrace(true);
        
        logger.info("Initializing DiscordApiBuilder");
        DiscordApi api = new DiscordApiBuilder()
                .setToken(System.getenv("DISCORD_API_TOKEN"))
                .login()
                .join();
        
        logger.info("Initializing listeners");
        // TODO: create a command factory
        api.addMessageCreateListener(new Ping(registryStore));
        api.addMessageCreateListener(new Help(registryStore));

        logger.debug("Available commands: {}",
                registryStore.availableCommands());
        logger.info("Application startup complete. Git hash: {}", gitHash);
    }
    
    private static String gitHash() throws IOException {
        // TODO: it might be helpful to have a class that handles this
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try(InputStream resourceStream =
                    loader.getResourceAsStream("git.properties")) {
            properties.load(resourceStream);
        }
        
        return properties.getProperty("git.commit.id.abbrev");
    }
    
}
