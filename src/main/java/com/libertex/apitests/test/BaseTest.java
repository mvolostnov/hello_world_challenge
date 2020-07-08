package com.libertex.apitests.test;

import com.libertex.apitests.webservices.WebService;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.mx.testframework.utils.ExtentManager;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

@Slf4j
public class BaseTest {

    protected static final WebService ws = new WebService();
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;

    @BeforeSuite(alwaysRun = true)
    public void browserSetup(){
        RestAssured.baseURI = "http://localhost:8080/challenge";
    }

    @BeforeClass
    public void beforeClass() {
        extentReports = ExtentManager.createInstance("extent.html");
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
        extentReports.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void beforeEachTest() {
        extentTest = extentReports.createTest(getClass().getName());
    }


    @AfterMethod
    public static void afterMethod(ITestResult result) {
        addReport(result);
    }

    public static void addReport(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail(result.getThrowable());
        }
        else if (result.getStatus() == ITestResult.SKIP)
            extentTest.skip(result.getThrowable());
        else
            extentTest.pass("Test passed");
        extentReports.flush();
    }

}
