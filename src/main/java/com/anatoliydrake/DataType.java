package com.anatoliydrake;

import lombok.Getter;

@Getter
public enum DataType {
    INTEGER("integers.txt"),
    FLOAT("floats.txt"),
    STRING("strings.txt");

    private final String fileName;

    DataType(String fileName) {
        this.fileName = fileName;
    }
}
