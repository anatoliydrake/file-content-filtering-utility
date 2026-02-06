package com.anatoliydrake;

import lombok.Getter;

@Getter
public class StatisticsCollector {
    private final boolean collectFull;
    private final IntegerStatistics intStats = new IntegerStatistics();
    private final FloatStatistics floatStatistics = new FloatStatistics();
    private final StringStatistics stringStatistics = new StringStatistics();

    public StatisticsCollector(boolean collectFull) {
        this.collectFull = collectFull;
    }

    public void record(DataType type, String value) {
        switch (type) {
            case INTEGER -> recordInteger(value);
            case FLOAT -> recordFloat(value);
            case STRING -> recordString(value);
        }
    }

    private void recordInteger(String value) {
        intStats.setCount(intStats.getCount() + 1);
        if (collectFull) {
            long num = Long.parseLong(value);
            intStats.setMin(Math.min(intStats.getMin(), num));
            intStats.setMax(Math.max(intStats.getMax(), num));
            intStats.setSum(intStats.getSum() + num);
        }
    }

    private void recordFloat(String value) {
        floatStatistics.setCount(floatStatistics.getCount() + 1);
        if (collectFull) {
            double num = Double.parseDouble(value);
            floatStatistics.setMin(Math.min(floatStatistics.getMin(), num));
            floatStatistics.setMax(Math.max(floatStatistics.getMax(), num));
            floatStatistics.setSum(floatStatistics.getSum() + num);
        }
    }

    private void recordString(String value) {
        stringStatistics.setCount(stringStatistics.getCount() + 1);
        if (collectFull) {
            int length = value.length();
            stringStatistics.setMinLength(Math.min(stringStatistics.getMinLength(), length));
            stringStatistics.setMaxLength(Math.max(stringStatistics.getMaxLength(), length));
        }
    }
}
