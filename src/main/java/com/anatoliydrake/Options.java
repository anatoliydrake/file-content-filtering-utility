package com.anatoliydrake;

import lombok.Value;

import java.util.List;

@Value
public class Options {
    String outputPath;
    String prefix;
    boolean appendMode;
    boolean shortStats;
    boolean fullStats;
    List<String> inputFiles;
}
