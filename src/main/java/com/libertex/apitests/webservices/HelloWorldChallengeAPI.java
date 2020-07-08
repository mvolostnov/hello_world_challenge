package com.libertex.apitests.webservices;

import com.libertex.apitests.test.data.entities.ClientData;
import com.mx.testframework.ws.Request;
import com.mx.testframework.ws.Response;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@Slf4j
public class HelloWorldChallengeAPI {

    private final String CLIENTS = "/clients";
    private final String LOGIN = "/login";
    private final String HELLO = "/hello";

    private String xSessionId;

  /*  private final String username = ClientData.getUserName();
    private final String fullName = ClientData.getFullName();*/

    public HelloWorldChallengeAPI getSessionID(String fullName, String username) {

/*        final String username = ClientData.getUserName();
        final String fullName = ClientData.getFullName();*/

//Create new Client
        Map<String, Object> clientData = new HashMap<>();
        clientData.put("fullName", fullName);
        clientData.put("username", username);

                given().
                        contentType(JSON)
                        .body(clientData)
                        .log().body()
                        .when()
                        .post(CLIENTS)
                        .then()
                        .statusCode(200)
                        .log().body();
   //                     .extract().response().getHeader("x-session-id");
        log.info("Create new Client with fullName = {} and username = {}", fullName, username);

        log.info("Login as {}", username);

        Map<String, Object> loginData = new HashMap<>();
        loginData.put("username", username);
        log.info("Logined as {}", username);

        xSessionId =
        given().
                contentType(JSON)
                .body(loginData)
                .log().body()
                .when()
                .post(LOGIN)
                .then()
                .statusCode(200)
                .extract().response().getHeader("x-session-id");
        log.info("SessionId ={} ", xSessionId);

        return this;
    }

    public HelloWorldChallengeAPI fakeSessionID(String xSessionId) {
        this.xSessionId = xSessionId;
        return this;
    }

    public Response sendHelloRequest(/*String xSessionId*/) {

        log.info("Send HelloRequest as username = {} and X-Session-Id = {}", ClientData.getUserName(), xSessionId);

        Map<String, Object> sessionData = new HashMap<>();
        sessionData.put("X-Session-Id", xSessionId);

        return new Request().executeGet(sessionData, HELLO);
                /*given()
                .headers(sessionData)
                .log().headers()
                .when()
                .log().uri()
                .get(HELLO)*/
    }
}
