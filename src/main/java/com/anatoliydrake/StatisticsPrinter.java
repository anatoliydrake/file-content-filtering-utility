package com.anatoliydrake;

public class StatisticsPrinter {

    public void print(StatisticsCollector collector, StatsPrintingMode printingMode) {
        IntegerStatistics intStats = collector.getIntStats();
        if (intStats.getCount() > 0) {
            System.out.println("Integer statistics:");
            System.out.println("  Count: " + intStats.getCount());
            if (printingMode == StatsPrintingMode.FULL) {
                System.out.println("  Min: " + intStats.getMin());
                System.out.println("  Max: " + intStats.getMax());
                System.out.println("  Sum: " + intStats.getSum());
                System.out.println("  Average: " + intStats.getAverage());
            }
        }

        FloatStatistics floatStatistics = collector.getFloatStatistics();
        if (floatStatistics.getCount() > 0) {
            System.out.println("Float statistics:");
            System.out.println("  Count: " + floatStatistics.getCount());
            if (printingMode == StatsPrintingMode.FULL) {
                System.out.println("  Min: " + floatStatistics.getMin());
                System.out.println("  Max: " + floatStatistics.getMax());
                System.out.println("  Sum: " + floatStatistics.getSum());
                System.out.println("  Average: " + floatStatistics.getAverage());
            }
        }

        StringStatistics stringStatistics = collector.getStringStatistics();
        if (stringStatistics.getCount() > 0) {
            System.out.println("String statistics:");
            System.out.println("  Count: " + stringStatistics.getCount());
            if (printingMode == StatsPrintingMode.FULL) {
                System.out.println("  Min length: " + stringStatistics.getMinLength());
                System.out.println("  Max length: " + stringStatistics.getMaxLength());
            }
        }
    }
}
