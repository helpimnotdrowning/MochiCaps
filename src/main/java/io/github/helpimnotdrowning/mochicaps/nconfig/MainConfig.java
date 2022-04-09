package io.github.helpimnotdrowning.mochicaps.nconfig;

import io.github.helpimnotdrowning.mochicaps.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainConfig {
    private static final Log LOGGER = new Log(MainConfig.class);

    private static final Path baseConfigPath = Paths.get(System.getProperty("user.home"), "MochiCaps");
    private static final Path mainConfigPath = baseConfigPath.resolve("main.json");

    public MainConfig() {
        if (!Files.exists(mainConfigPath)) {
            try {
                Files.createFile(mainConfigPath);
            } catch (IOException e) {
                LOGGER.error("Couldn't create main config file at \"" + mainConfigPath.toString() + "\"!");
                e.printStackTrace();
                System.exit(1);
            }
        }


    }
}
