package com.anatoliydrake;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class OutputWriter implements AutoCloseable {
    private final Options options;
    private final Map<DataType, BufferedWriter> writers = new HashMap<>();

    public OutputWriter(Options options) {
        this.options = options;
    }

    public void write(DataType type, String line) throws IOException {
        BufferedWriter writer;

        boolean isWriterNotExist = !writers.containsKey(type);
        if (isWriterNotExist) {
            String filename = options.getPrefix() + type.getFileName();
            Path path = getOutputPath(filename);

            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            writer = new BufferedWriter(new FileWriter(path.toFile(), options.isAppendMode()));
            writers.put(type, writer);
        } else {
            writer = writers.get(type);
        }
        writer.write(line);
        writer.newLine();
    }
    private Path getOutputPath(String filename) {
        if (options.getOutputPath().isEmpty()) {
            return Paths.get(filename);
        }
        return Paths.get(options.getOutputPath(), filename);
    }

    @Override
    public void close() throws IOException {
        for (BufferedWriter writer : writers.values()) {
            writer.close();
        }
    }
}
