package com.pro_crafting.tools.microprofile_config_sources;

import com.google.common.jimfs.Jimfs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class DockerSecretSourceTest {

    @Test
    void testInitializeProperties() throws IOException {
        FileSystem fileSystem = Jimfs.newFileSystem();
        Path base = fileSystem.getPath("run/secrets/");
        Files.createDirectories(base);
        Files.write(base.resolve("SECRET"), "abcdefg".getBytes(StandardCharsets.UTF_8));
        Files.write(base.resolve("token.secret"), "token".getBytes(StandardCharsets.UTF_8));

        DockerSecretSource dockerSecretSource = new DockerSecretSource(base);
        Assertions.assertEquals(2, dockerSecretSource.getProperties().size());
        Assertions.assertEquals("abcdefg", dockerSecretSource.getValue("SECRET"));
        Assertions.assertEquals("token", dockerSecretSource.getValue("token.secret"));
    }
}