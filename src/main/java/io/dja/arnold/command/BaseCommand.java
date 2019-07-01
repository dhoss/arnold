package io.dja.arnold.command;

import io.dja.arnold.command.registry.RegistryStore;

public abstract class BaseCommand {

    abstract public String name();
    abstract public String description();

    public BaseCommand() {
        RegistryStore.addCommand(name(), description());
    }
}
