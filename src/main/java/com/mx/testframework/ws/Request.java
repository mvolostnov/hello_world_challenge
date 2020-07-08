package com.mx.testframework.ws;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Request {

    public Response executeGet(Map<String, Object> headers, String uri) {

        return new Response(given()
                .headers(headers)
                .log().headers()
                .when()
                .log().uri()
                .get(uri));
    }
}
