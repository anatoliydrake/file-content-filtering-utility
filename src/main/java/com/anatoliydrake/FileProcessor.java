package com.anatoliydrake;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {
    private final Options options;

    public FileProcessor(Options options) {
        this.options = options;
    }

    public void process() {
        try (OutputWriter outputWriter = new OutputWriter(options)) {
            for (String filePath : options.getInputFiles()) {
                processFile(filePath, outputWriter);
            }
        } catch (IOException e) {
            System.err.println("Error writing output: " + e.getMessage());
        }
    }

    private void processFile(String filePath, OutputWriter outputWriter) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                DataType type = DataClassifier.classify(line);
                outputWriter.write(type, line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath + " - " + e.getMessage());
        }
    }
}
