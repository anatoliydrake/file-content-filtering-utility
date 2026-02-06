package com.anatoliydrake;

public class App {
    public static void main(String[] args) {
        try {
            Options options = ArgumentParser.parse(args);
            FileProcessor processor = new FileProcessor(options);
            processor.process();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            System.exit(1);
        }
    }
}
