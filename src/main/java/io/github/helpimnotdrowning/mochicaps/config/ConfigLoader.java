package io.github.helpimnotdrowning.mochicaps.config;

import org.simpleyaml.configuration.file.YamlFile;

import java.nio.file.Path;

public class ConfigLoader {






    private final Path configPath;
    private final YamlFile yamlFile;

    public ConfigLoader(Path configPath) {
        this.configPath = configPath;
        this.yamlFile = new YamlFile(configPath.toString());
        loadConfig();
    }

    private void loadConfig() {
        final YamlFile yamlFile = new YamlFile(configPath.toString());
        try {
            if (!yamlFile.exists()) {
                yamlFile.createNewFile(true);
                System.out.println("New file has been created: " + yamlFile.getFilePath() + "\n");
            } else {
                System.out.println(yamlFile.getFilePath() + " already exists, loading configurations...\n");
            }
            yamlFile.load(); // Loads the entire file
            // If your file has comments inside you have to load it with yamlFile.loadWithComments()
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    private void createConfig() {
        try {
            yamlFile.createNewFile(true);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
