package com.anatoliydrake;

public class DataClassifier {

    public static DataType classify(String line) {
        try {
            Long.parseLong(line);
            return DataType.INTEGER;
        } catch (NumberFormatException ignored) {
        }

        try {
            Double.parseDouble(line);
            return DataType.FLOAT;
        } catch (NumberFormatException ignored) {
        }

        return DataType.STRING;
    }
}
