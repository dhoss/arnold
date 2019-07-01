package io.dja.arnold.command.heartbeat;

import io.dja.arnold.command.BaseCommand;
import io.dja.arnold.command.registry.RegistryStore;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Ping extends BaseCommand implements MessageCreateListener {

    public String name() {
        return "!ping";
    }

    public String description() {
        return "responds with " +
                "\"Pong!\".  Used for checking if the bot is up.";
    }

    public Ping() {
        super();
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase(name())) {
            event.getChannel().sendMessage("Pong!");
        }
    }
}
