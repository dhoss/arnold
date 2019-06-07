package io.dja.arnold.heartbeat;

    import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Ping implements MessageCreateListener {
        
        /*
         * This command can be used to set the bot's avatar.
         * It can only be used by the bot's owner.
         */
        @Override
        public void onMessageCreate(MessageCreateEvent event) {
            if (event.getMessageContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        }
        
    
}
