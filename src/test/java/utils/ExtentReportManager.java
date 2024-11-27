package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports createInstance() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/extent-report.html");

        sparkReporter.config().setDocumentTitle("TechState Automation Report");

        extent = new ExtentReports();

        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tested By", "Beksultan");
        extent.setSystemInfo("Environment", "PRODUCTION");

        return extent;
    }

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }
}
