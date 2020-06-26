package com.apitests.webservices;

import com.apitests.test.data.entities.ClientData;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@Slf4j
public class HelloWorldChallengeAPI {
    public static String xSessionId;

    public HelloWorldChallengeAPI getSessionID() {

        log.info("Login as {}", ClientData.username);

        Map<String, Object> loginData = new HashMap<>();
        loginData.put("username", ClientData.username);

        xSessionId =
        given().
                contentType(JSON)
                .body(loginData)
                .log().body()
                .when()
                .post(Endpoints.login)
                .then()
                .statusCode(200)
                .extract().response().getHeader("x-session-id");
        log.info("SessionId ={} ", xSessionId);

        return this;
    }


    public Response sendHelloRequest(String xSessionId) {

        log.info("Send HelloRequest as username = {} and X-Session-Id = {}", ClientData.username, xSessionId);

        Map<String, Object> sessionData = new HashMap<>();
        sessionData.put("X-Session-Id", xSessionId);

        return given()
                .headers(sessionData)
                .log().headers()
                .when()
                .log().uri()
                .get(Endpoints.helloRequest);
    }
}
