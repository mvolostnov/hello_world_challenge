package com.mx.testframework.utils;

import org.apache.commons.lang.RandomStringUtils;

public class RandomString {

    public static String randomString(int count) {
        return (RandomStringUtils.randomAlphabetic(count));
    }
}