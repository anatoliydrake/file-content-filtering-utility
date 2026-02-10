package com.anatoliydrake;

public class App {
    public static void main(String[] args) {
        try {
            Options options = ArgumentParser.parse(args);
            FileProcessor processor = new FileProcessor(options);
            processor.process();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Usage: file-filter [options] <input-files>");
            System.err.println("Options: -o <path> -p <prefix> -a -s -f");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
