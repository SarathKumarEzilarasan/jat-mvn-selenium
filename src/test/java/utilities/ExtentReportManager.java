package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReportManager extentReportManager;

    public ExtentReports init() {
        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/index.html");
        extentReports.attachReporter(sparkReporter);
        sparkReporter.config().setDocumentTitle("Regression Tests");
        return extentReports;
    }

    public static ExtentReports createExtentReport() {
        if (extentReportManager == null) {
            extentReportManager = new ExtentReportManager();
            return extentReportManager.init();
        }
        return null;
    }

}
