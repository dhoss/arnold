package io.dja.arnold.command.registry;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class RegistryStoreTest {

    private RegistryStore registryStore = new RegistryStore();
    
    @Test
    public void addCommand() {
        registryStore.addCommand("test", "test command");
        assertThat(registryStore.availableCommands())
                .isEqualTo(
                        "```\n" +
                        String.format("%5s: %8s", "test", "test command") +
                        "\n```");
    }
}
