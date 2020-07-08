package com.mx.testframework.ws;

import io.restassured.response.ValidatableResponseOptions;
import lombok.AllArgsConstructor;

import static org.hamcrest.Matchers.equalTo;

@AllArgsConstructor
public class ResponseValidator {
    private final ValidatableResponseOptions validatable;

    public ResponseValidator statusCodeEqualsTo(int statusCode) {
            validatable
                .statusCode(statusCode);
            return this;
    }

    public ResponseValidator bodyParamEqualsTo(String paramName, String paramValue) {
        validatable.body(paramName, equalTo(paramValue));
        return this;
    }
}
