package io.dja.arnold.command;

import io.dja.arnold.command.registry.RegistryStore;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.rmi.registry.Registry;

public class Help extends BaseCommand implements MessageCreateListener {
    
    public Help(RegistryStore registryStore) {
        super(registryStore);
    }
    
    public String name() {
        return "!help";
    }

    public String description() {
        return "Returns a list of commands and their description.";
    }

    // TODO: see if we can't move this somewhere common, there's a lot of common code per command
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase(name())) {
            event.getChannel().sendMessage(registryStore.availableCommands());
        }
    }
}
