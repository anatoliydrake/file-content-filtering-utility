package com.anatoliydrake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataClassifierTest {

    @Test
    @DisplayName("Classify positive integer")
    void classifyPositiveInteger() {
        assertEquals(DataType.INTEGER, DataClassifier.classify("123"));
        assertEquals(DataType.INTEGER, DataClassifier.classify("45"));
    }

    @Test
    @DisplayName("Classify negative integer")
    void classifyNegativeInteger() {
        assertEquals(DataType.INTEGER, DataClassifier.classify("-123"));
        assertEquals(DataType.INTEGER, DataClassifier.classify("-45"));
    }

    @Test
    @DisplayName("Classify zero")
    void classifyZero() {
        assertEquals(DataType.INTEGER, DataClassifier.classify("0"));
        assertEquals(DataType.INTEGER, DataClassifier.classify("-0"));
    }

    @Test
    @DisplayName("Classify large integer")
    void classifyLargeInteger() {
        assertEquals(DataType.INTEGER, DataClassifier.classify("1234567890123456789"));
    }

    @Test
    @DisplayName("Classify positive float")
    void classifyPositiveFloat() {
        assertEquals(DataType.FLOAT, DataClassifier.classify("3.1415"));
        assertEquals(DataType.FLOAT, DataClassifier.classify("1.0"));
    }

    @Test
    @DisplayName("Classify negative float")
    void classifyNegativeFloat() {
        assertEquals(DataType.FLOAT, DataClassifier.classify("-0.001"));
        assertEquals(DataType.FLOAT, DataClassifier.classify("-3.1415"));
    }

    @Test
    @DisplayName("Classify scientific notation")
    void classifyScientificNotation() {
        assertEquals(DataType.FLOAT, DataClassifier.classify("1.23456789E-25"));
        assertEquals(DataType.FLOAT, DataClassifier.classify("1E3"));
        assertEquals(DataType.FLOAT, DataClassifier.classify("1.5e-5"));
        assertEquals(DataType.FLOAT, DataClassifier.classify("1.5e5"));
    }

    @Test
    @DisplayName("Classify zero as float")
    void classifyZeroFloat() {
        assertEquals(DataType.FLOAT, DataClassifier.classify("0.0"));
        assertEquals(DataType.FLOAT, DataClassifier.classify("-0.0"));
    }

    @Test
    @DisplayName("Classify text string")
    void classifyTextString() {
        assertEquals(DataType.STRING, DataClassifier.classify("Hello"));
        assertEquals(DataType.STRING, DataClassifier.classify("Lorem ipsum dolor sit amet"));
        assertEquals(DataType.STRING, DataClassifier.classify("Пример"));
    }

    @Test
    @DisplayName("Classify empty string")
    void classifyEmptyString() {
        assertEquals(DataType.STRING, DataClassifier.classify(""));
    }

    @Test
    @DisplayName("Classify mixed alphanumeric")
    void classifyMixedAlphanumeric() {
        assertEquals(DataType.STRING, DataClassifier.classify("123abc"));
        assertEquals(DataType.STRING, DataClassifier.classify("abc123"));
        assertEquals(DataType.STRING, DataClassifier.classify("12.34.56"));
    }

    @Test
    @DisplayName("Classify whitespace")
    void classifyWhitespace() {
        assertEquals(DataType.STRING, DataClassifier.classify(" "));
        assertEquals(DataType.STRING, DataClassifier.classify("   "));
    }

    @Test
    @DisplayName("Classify special characters")
    void classifySpecialCharacters() {
        assertEquals(DataType.STRING, DataClassifier.classify("@#$%"));
        assertEquals(DataType.STRING, DataClassifier.classify("test!"));
    }
}
