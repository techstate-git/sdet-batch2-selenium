package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverManager;
import utils.ExtentReportManager;

import java.util.Base64;

public class Hooks {
    private ExtentReports extent;
    private ExtentTest test;

    @Before
    public void setUp(Scenario scenario) {
        extent = ExtentReportManager.getInstance();
        test = extent.createTest(scenario.getName());

        test.log(Status.INFO, "Starting scenario: " + scenario.getName());

        DriverManager.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        // Capture screenshot if scenario fails
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            test.log(Status.FAIL, "Scenario failed: " + scenario.getName());
            test.addScreenCaptureFromBase64String(Base64.getEncoder().encodeToString(screenshot), "Failure screenshot");
        } else {
            test.log(Status.PASS, "Scenario passed: " + scenario.getName());
        }

        DriverManager.quitDriver();

        extent.flush();
    }
}















