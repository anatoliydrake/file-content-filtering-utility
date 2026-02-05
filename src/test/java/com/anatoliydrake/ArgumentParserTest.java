package com.anatoliydrake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentParserTest {

    @Test
    @DisplayName("Parse minimal arguments")
    void parseMinimalArgs() {
        String[] args = {"input1.txt", "input2.txt"};
        Options options = ArgumentParser.parse(args);

        assertEquals("", options.getOutputPath());
        assertEquals("", options.getPrefix());
        assertFalse(options.isAppendMode());
        assertFalse(options.isShortStats());
        assertFalse(options.isFullStats());
        assertEquals(2, options.getInputFiles().size());
        assertEquals("input1.txt", options.getInputFiles().get(0));
        assertEquals("input2.txt", options.getInputFiles().get(1));
    }

    @Test
    @DisplayName("Parse all options")
    void parseAllOptions() {
        String[] args = {"-o", "/output", "-p", "prefix_", "-a", "-s", "-f", "file.txt"};
        Options options = ArgumentParser.parse(args);

        assertEquals("/output", options.getOutputPath());
        assertEquals("prefix_", options.getPrefix());
        assertTrue(options.isAppendMode());
        assertTrue(options.isShortStats());
        assertTrue(options.isFullStats());
        assertEquals(1, options.getInputFiles().size());
        assertEquals("file.txt", options.getInputFiles().get(0));
    }

    @Test
    @DisplayName("Parse short statistics only")
    void parseShortStatsOnly() {
        String[] args = {"-s", "input.txt"};
        Options options = ArgumentParser.parse(args);

        assertTrue(options.isShortStats());
        assertFalse(options.isFullStats());
    }

    @Test
    @DisplayName("Parse full statistics only")
    void parseFullStatsOnly() {
        String[] args = {"-f", "input.txt"};
        Options options = ArgumentParser.parse(args);

        assertFalse(options.isShortStats());
        assertTrue(options.isFullStats());
    }

    @Test
    @DisplayName("Missing argument for -o option")
    void parseMissingOutputPathArgument() {
        String[] args = {"-o"};

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> ArgumentParser.parse(args)
        );

        assertEquals("Option -o requires a path argument", exception.getMessage());
    }

    @Test
    @DisplayName("Missing argument for -p option")
    void parseMissingPrefixArgument() {
        String[] args = {"-p"};

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> ArgumentParser.parse(args)
        );

        assertEquals("Option -p requires a prefix argument", exception.getMessage());
    }

    @Test
    @DisplayName("Unknown option")
    void parseUnknownOption() {
        String[] args = {"-x", "input.txt"};

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> ArgumentParser.parse(args)
        );

        assertEquals("Unknown option: -x", exception.getMessage());
    }

    @Test
    @DisplayName("No input files provided")
    void parseNoInputFiles() {
        String[] args = {"-a", "-s"};

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> ArgumentParser.parse(args)
        );

        assertEquals("At least one input file is required", exception.getMessage());
    }

    @Test
    @DisplayName("Parse multiple input files")
    void parseMultipleInputFiles() {
        String[] args = {"-p", "result_", "in1.txt", "in2.txt", "in3.txt"};
        Options options = ArgumentParser.parse(args);

        assertEquals("result_", options.getPrefix());
        assertEquals(3, options.getInputFiles().size());
        assertEquals("in1.txt", options.getInputFiles().get(0));
        assertEquals("in2.txt", options.getInputFiles().get(1));
        assertEquals("in3.txt", options.getInputFiles().get(2));
    }

    @Test
    @DisplayName("Parse options and files intermixed")
    void parseOptionsAndFilesIntermixed() {
        String[] args = {"file1.txt", "-o", "/tmp", "file2.txt", "-a", "file3.txt"};
        Options options = ArgumentParser.parse(args);

        assertEquals("/tmp", options.getOutputPath());
        assertTrue(options.isAppendMode());
        assertEquals(3, options.getInputFiles().size());
        assertEquals("file1.txt", options.getInputFiles().get(0));
        assertEquals("file2.txt", options.getInputFiles().get(1));
        assertEquals("file3.txt", options.getInputFiles().get(2));
    }

    @Test
    @DisplayName("Option instead of path for -o")
    void parseOutputPathFollowedByOption() {
        String[] args = {"-o", "-a", "file.txt"};

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> ArgumentParser.parse(args)
        );

        assertEquals("Option -o requires a path argument, got option: -a", exception.getMessage());
    }

    @Test
    @DisplayName("Option instead of prefix for -p")
    void parsePrefixFollowedByOption() {
        String[] args = {"-p", "-s", "file.txt"};

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> ArgumentParser.parse(args)
        );

        assertEquals("Option -p requires a prefix argument, got option: -s", exception.getMessage());
    }
}
