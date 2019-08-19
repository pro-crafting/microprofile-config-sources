package com.pro_crafting.tools.microprofile_config_sources;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * Config Source which reads configuration values from docker swarm secrets. <br/>
 * The secrets must be located in /run/secrets/<secret-name> <br/>
 * The file name of the secret is used directly as name of the configuration value
 */
// TODO: Logging using slf4j
public class DockerSecretSource implements ConfigSource {
    public static final String DIRECTORY = "/run/secrets/";

    private Map<String, String> properties = new ConcurrentHashMap<>();

    public DockerSecretSource() {
        this(Paths.get(DIRECTORY));
    }

    public DockerSecretSource(Path directory) {
        initializeProperties(directory);
    }

    private void initializeProperties(Path directory) {
        if (Files.notExists(directory)) {
            return;
        }
        try (Stream<Path> walk = Files.walk(directory)) {
            walk.filter(Files::isRegularFile)
                    .forEach(this::setProperty);
        } catch (IOException e) {
            // Not able to search in the file system, provide no values
            return;
        }
    }

    /**
     * Sets the properties content based upon the file name and file content
     * @param file
     */
    private void setProperty(Path file) {
        byte[] allBytes;
        try {
            allBytes = Files.readAllBytes(file);
        } catch (IOException e) {
            // Not able to read this file
            return;
        }

        String fileContent = new String(allBytes, StandardCharsets.UTF_8);
        properties.put(file.getFileName().toString(), fileContent);
    }

    @Override
    public Set<String> getPropertyNames() {
        return properties.keySet();
    }

    @Override
    public Map<String, String> getProperties() {
        return properties;
    }

    @Override
    public String getValue(String propertyName) {
        return properties.get(propertyName);
    }

    @Override
    public String getName() {
        return DockerSecretSource.class.getSimpleName();
    }

    @Override
    public int getOrdinal() {
        return 300;
    }
}
