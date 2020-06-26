package com.mx.testframework.utils;


public class RandomNumericLong {
    public static Long randomNum(long count) {
        Long generatedLong = (long) ( Math.random() * count );
        return (generatedLong);
    }
}
