package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

public class TestNgListener implements ITestListener {

    private ExtentReports extentReports;
    private ITestContext context;

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("test failed");
        String testCaseName = result.getMethod().getMethodName();
        String path = BaseTest.takeScreenShot(testCaseName);
        ExtentTest extentTest = (ExtentTest) context.getAttribute("extentTest");
        extentTest.addScreenCaptureFromPath(path);
        extentTest.fail("Payment is unsuccessful");
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        extentReports = ExtentReportManager.createExtentReport();
        context.setAttribute("extentReporter", extentReports);
        this.context = context;
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }

}
