package io.github.helpimnotdrowning.mochicaps.config;

import io.github.helpimnotdrowning.mochicaps.Log;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    private static final Log LOGGER = new Log(Config.class);

    private static final Path baseConfigPath = Paths.get(System.getProperty("user.home"), "MochiCaps");
    private static final Path mainConfigPath = baseConfigPath.resolve("main.json");




    private void createDummyConfig {

    }


    public Config load(Path configFile) {

    }
}
