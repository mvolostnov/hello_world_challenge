package com.mx.testframework.utils;

public class RandomNumericInt {
    public static Integer randomNum(int count) {
        Integer generatedInteger = (int) ( Math.random() * count );
        return (generatedInteger);
    }
}
