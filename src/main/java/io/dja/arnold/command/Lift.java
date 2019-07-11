package io.dja.arnold.command;

import io.dja.arnold.command.registry.RegistryStore;
import org.apache.commons.cli.Options;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Lift extends BaseCommand implements MessageCreateListener {

    private Options options;

    public Lift(RegistryStore registryStore) {
        super(registryStore);
        this.options = new Options();
        this.options.addOption("a", "")
    }

    public String name() {
        return "!add-lift";
    }

    // TODO: make this an embed
    public String description() {
        return "add a lift: " + name() + " barbell squats, 5@200:3@225:1@250:3@240:3@225";
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String messageContent = event.getMessageContent();
        if (messageContent.matches("\\!" + name() + "\\s\\w.+")) {
            String liftInformation = messageContent.split("!add-lift")[1];
            event.getChannel().sendMessage("Added lift: " + liftInformation);
        }
    }

}
