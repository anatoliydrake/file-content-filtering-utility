package com.anatoliydrake;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {
    private final Options options;
    private final StatisticsPrinter printer;
    private final StatisticsCollector stats;

    public FileProcessor(Options options) {
        this.options = options;
        this.printer = new StatisticsPrinter();
        this.stats = new StatisticsCollector(options.isFullStats());
    }

    public void process() {
        try (OutputWriter outputWriter = new OutputWriter(options)) {
            for (String filePath : options.getInputFiles()) {
                processFile(filePath, outputWriter, stats);
            }
        } catch (IOException e) {
            System.err.println("Error writing output: " + e.getMessage());
        }

        if (options.isFullStats()) {
            printer.print(stats, StatsPrintingMode.FULL);
        } else if (options.isShortStats()) {
            printer.print(stats, StatsPrintingMode.SHORT);
        }
    }

    private void processFile(String filePath, OutputWriter outputWriter, StatisticsCollector stats) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                DataType type = DataClassifier.classify(line);
                outputWriter.write(type, line);
                stats.record(type, line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath + " - " + e.getMessage());
        }
    }
}
