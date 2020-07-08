package com.apitests.test.suites.task;

import com.apitests.test.BaseTest;
import com.apitests.test.data.entities.DataProviderClass;
import com.apitests.test.data.entities.SessionData;
import com.apitests.test.data.entities.ClientData;
import com.apitests.webservices.HelloWorldChallengeAPI;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Slf4j
public class HelloTest extends BaseTest {

    //Verify positive scenario with correct values of X-Session-Id
    @Test()
    public void helloRequestCorrectValuesTest(){
        log.info("Start helloRequestCorrectValuesTest: ");

        ws.helloWorldChallengeAPI()
                .getSessionID()
                .sendHelloRequest(HelloWorldChallengeAPI.xSessionId)
                .then()
                .log().body()
                .statusCode(200)
                .body("resultCode", equalTo("Ok"))
                .body("message", equalTo("Hello, " + ClientData.fullName + "!"))
                ;
    }

    //Verify negative scenarios with incorrect values of X-Session-Id
    @Test(dataProvider = "incorrectSessionId", dataProviderClass = DataProviderClass.class)
    public void helloRequestIncorrectValuesTest(SessionData sessionData){
        log.info("Start helloRequestIncorrectValuesTest: ");

        ws.helloWorldChallengeAPI()
                .getSessionID()
                .sendHelloRequest(sessionData.getXSessionId())
                .then()
                .log().body()
                .statusCode(401)
                .body("resultCode", equalTo("Unauthorized"));
 System.out.println("Test1111");
    }

}
