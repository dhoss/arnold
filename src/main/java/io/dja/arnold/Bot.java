package io.dja.arnold;

import io.dja.arnold.command.Help;
import io.dja.arnold.command.heartbeat.Ping;
import io.dja.arnold.command.registry.RegistryStore;
import io.sentry.Sentry;
import io.sentry.event.BreadcrumbBuilder;
import io.sentry.event.UserBuilder;
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
    
        Sentry.getContext().setUser(
                new UserBuilder()
                        .setUsername(System.getenv("APP_USER")).build());
        Sentry.getContext().recordBreadcrumb(
                new BreadcrumbBuilder()
                        .setMessage("Starting application")
                        .build());
        Sentry.getContext().addTag("event", "startup");
        Sentry.capture("startup");
        
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
        logger.debug("Application startup complete. Git hash: " +
                System.getProperty("git.commit.id.abbrev"));
    }
    
}
