package com.mx.testframework.ws;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Response {

    private io.restassured.response.Response response;

    public ResponseValidator validate(){
        return new ResponseValidator(response.then().log().body());
    }
}
