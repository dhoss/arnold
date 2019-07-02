package io.dja.arnold.command;

import io.dja.arnold.command.registry.RegistryStore;

public abstract class BaseCommand {

    abstract public String name();
    abstract public String description();
    
    protected RegistryStore registryStore;
    public BaseCommand(RegistryStore rs) {
        this.registryStore = rs;
        registryStore.addCommand(name(), description());
    }
}
