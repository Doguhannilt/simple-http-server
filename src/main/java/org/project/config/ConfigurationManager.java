package org.project.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.project.util.Json;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.io.IOException;

public class ConfigurationManager {
    private static Configuration myCurrentConfiguration;
    private static ConfigurationManager myConfigurationManager;

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        if (myConfigurationManager == null)
            myConfigurationManager = new ConfigurationManager();
            return myConfigurationManager;

    }

    /**
     * Loads a configuration file from the specified file path and parses its content.
     * The file content is expected to be in JSON format, which is then parsed into a
     * {@link Configuration} object and stored in {@link #myCurrentConfiguration}.
     * <p>
     * If the file cannot be read or the JSON content is invalid, an {@link HttpConfigurationException}
     * is thrown.
     *
     * @param filePath The path of the configuration file to load.
     * @throws HttpConfigurationException If there is an issue reading the file or parsing its content.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public void loadConfigurationFile(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                sb.append(line).append("\n");
            }

            JsonNode conf = Json.parse(sb.toString());
            myCurrentConfiguration = Json.fromJson(conf, Configuration.class);

        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }
    }

    /**
     * Retrieves the current configuration.
     * <p>
     * If the configuration has not been loaded (i.e., {@link #myCurrentConfiguration} is null),
     * an {@link HttpConfigurationException} is thrown.
     *
     * @return The current {@link Configuration} object.
     * @throws HttpConfigurationException If no configuration has been loaded.
     */
    public Configuration getCurrentConfiguration() {
        if (myCurrentConfiguration == null) {
            throw new HttpConfigurationException("No Current Configuration.");
        }
        return myCurrentConfiguration;
    }

}
