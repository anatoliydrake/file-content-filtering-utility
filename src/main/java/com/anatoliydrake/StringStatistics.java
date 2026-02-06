package com.anatoliydrake;

import lombok.Data;

@Data
public class StringStatistics {
    private int count = 0;
    private int minLength = Integer.MAX_VALUE;
    private int maxLength = 0;
}
