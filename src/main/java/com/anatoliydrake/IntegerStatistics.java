package com.anatoliydrake;

import lombok.Data;

@Data
public class IntegerStatistics {
    private int count = 0;
    private long min = Long.MAX_VALUE;
    private long max = Long.MIN_VALUE;
    private long sum = 0;

    public double getAverage() {
        return (double) sum / count;
    }
}
