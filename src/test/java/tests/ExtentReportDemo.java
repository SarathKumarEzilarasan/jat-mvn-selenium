package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.TestNgListener;

public class ExtentReportDemo extends BaseTest {

    ExtentTest extentTest;
    ExtentReports extentReports;

    @BeforeTest
    public void startReport(ITestContext context) {
        extentReports = (ExtentReports) context.getAttribute("extentReporter");
    }

    @Test
    public void test(ITestContext context) {
        extentTest = extentReports.createTest("Verify the payment for UPI");
        driver.get("https://www.google.com");
        context.setAttribute("extentTest", extentTest);
        System.out.println(1/0);
    }

    @AfterTest
    public void endReport() {

    }
}
