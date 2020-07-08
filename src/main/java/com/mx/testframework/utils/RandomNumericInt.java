package com.mx.testframework.utils;

public class RandomNumericInt {
    public static Integer getInteger(int count) {
        return ((Integer) (int) ( Math.random() * count ));
    }

    public static Long getLong(long count) {
        return ((Long) (long) ( Math.random() * count ));
    }

}
