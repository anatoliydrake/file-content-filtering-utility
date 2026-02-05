package com.anatoliydrake;

import java.util.ArrayList;
import java.util.List;

public class ArgumentParser {

    public static Options parse(String[] args) {
        String outputPath = "";
        String prefix = "";
        boolean appendMode = false;
        boolean shortStats = false;
        boolean fullStats = false;
        List<String> inputFiles = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            switch (arg) {
                case "-o" -> {
                    if (i + 1 >= args.length) {
                        throw new IllegalArgumentException("Option -o requires a path argument");
                    }
                    String path = args[++i];
                    if (path.startsWith("-")) {
                        throw new IllegalArgumentException("Option -o requires a path argument, got option: " + path);
                    }
                    outputPath = path;
                }
                case "-p" -> {
                    if (i + 1 >= args.length) {
                        throw new IllegalArgumentException("Option -p requires a prefix argument");
                    }
                    String prefixArg = args[++i];
                    if (prefixArg.startsWith("-")) {
                        throw new IllegalArgumentException("Option -p requires a prefix argument, got option: " + prefixArg);
                    }
                    prefix = prefixArg;
                }
                case "-a" -> appendMode = true;
                case "-s" -> shortStats = true;
                case "-f" -> fullStats = true;
                default -> {
                    if (arg.startsWith("-")) {
                        throw new IllegalArgumentException("Unknown option: " + arg);
                    }
                    inputFiles.add(arg);
                }
            }
        }

        if (inputFiles.isEmpty()) {
            throw new IllegalArgumentException("At least one input file is required");
        }

        return new Options(outputPath, prefix, appendMode, shortStats, fullStats, inputFiles);
    }
}
