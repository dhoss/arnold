package io.dja.arnold.command.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class RegistryStore {

    private Map<String, String> commands = new ConcurrentHashMap<>();

    public void addCommand(String name, String description) {
        commands.put(name, description);
    }

    public String availableCommands() {
        return commands.entrySet()
                .stream()
                .map(k -> String.format("%5s: %8s", k.getKey(), k.getValue()))
                .collect(Collectors.joining("\n", "```\n", "\n```"));
    }

}
