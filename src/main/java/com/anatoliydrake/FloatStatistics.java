package com.anatoliydrake;

import lombok.Data;

@Data
public class FloatStatistics {
    private int count = 0;
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;
    private double sum = 0;

    public double getAverage() {
        return sum / count;
    }
}
