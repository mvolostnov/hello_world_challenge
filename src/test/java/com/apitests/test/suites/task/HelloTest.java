package com.apitests.test.suites.task;

import com.libertex.apitests.test.BaseTest;
import com.libertex.apitests.test.data.entities.ClientData;
import com.libertex.apitests.test.data.entities.DataProviderClass;
import com.libertex.apitests.test.data.entities.SessionData;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class HelloTest extends BaseTest {

    //Verify positive scenario with correct values of X-Session-Id
    @Test()
    public void helloRequestCorrectValuesTest(){
        log.info("Start helloRequestCorrectValuesTest: ");

        String fullName = ClientData.getFullName();
        String username = ClientData.getUserName();

        ws.helloWorldChallengeAPI()
                .getSessionID(fullName, username)
                .sendHelloRequest(/*HelloWorldChallengeAPI.xSessionId*/)
                .validate()
                .statusCodeEqualsTo(200)
                .bodyParamEqualsTo("resultCode", "Ok")
                .bodyParamEqualsTo("message", "Hello, " +  fullName + "!")
 //               .body("message", equalTo("Hello, " +  fullName + "!"))
                ;
    }

    //Verify negative scenarios with incorrect values of X-Session-Id
    @Test(dataProvider = "incorrectSessionId", dataProviderClass = DataProviderClass.class)
    public void helloRequestIncorrectValuesTest(SessionData sessionData){
        log.info("Start helloRequestIncorrectValuesTest: ");

        String fullName = ClientData.getFullName();
        String username = ClientData.getUserName();

        ws.helloWorldChallengeAPI()
//                .getSessionID(fullName, username)
                .fakeSessionID(sessionData.getXSessionId())
                .sendHelloRequest(/*sessionData.getXSessionId()*/)
                .validate()
                .statusCodeEqualsTo(401)
                .bodyParamEqualsTo("resultCode", "Unauthorized");
/*
               .then()
                .log().body()
                .statusCode(401)
                .body("resultCode", equalTo("Unauthorized"));

*/
        System.out.println("Test1111");
    }


}
