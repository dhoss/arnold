package io.dja.arnold;

import io.dja.arnold.heartbeat.Ping;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bot {
    
    private static final Logger logger = LoggerFactory.getLogger(Bot.class);
    
    public static void main(String[] args) {
        
        FallbackLoggerConfiguration.setDebug(true);
        FallbackLoggerConfiguration.setTrace(true);
        
        logger.info("Initializing DiscordApiBuilder");
        DiscordApi api = new DiscordApiBuilder()
                .setToken(System.getenv("DISCORD_API_TOKEN"))
                .login()
                .join();
        
        logger.info("Initializing listeners");
        api.addMessageCreateListener(new Ping());
    }
    
}
