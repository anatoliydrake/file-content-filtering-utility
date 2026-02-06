package com.anatoliydrake;

public class App {
    public static void main(String[] args) {
        args = new String[]{"-p", "result-", "src/main/resources/in1.txt", "src/main/resources/in2.txt"};

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
