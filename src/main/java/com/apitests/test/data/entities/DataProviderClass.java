package com.apitests.test.data.entities;

import org.testng.annotations.DataProvider;


public class DataProviderClass {

    @DataProvider(name = "incorrectSessionId")
    public Object[][] incorrectSessionId(){
        return new Object[][]{
                new Object[]{
                        SessionData.builder()
                                .xSessionId("incorrect-value-4755-a7c0-951111111111")
                                .build()},
                new Object[]{
                        SessionData.builder()
                                .xSessionId(null)
                                .build()},

                new Object[]{
                        SessionData.builder()
                                .xSessionId("")
                                .build()}
        };
    }

}
