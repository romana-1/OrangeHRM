package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extentReports;

    public static ExtentReports getReportInstance() {

        if (extentReports == null) {

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter("reports/ExtentReport.html");

            sparkReporter.config().setReportName("OrangeHRM Automation Report");
            sparkReporter.config().setDocumentTitle("Test Execution Report");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);

            extentReports.setSystemInfo("Project", "OrangeHRM Automation");
            extentReports.setSystemInfo("Tester", "QA Automation");
            extentReports.setSystemInfo("Browser", "Chrome");
            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
            extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
            extentReports.setSystemInfo("Environment", "OrangeHRM Demo");
        }

        return extentReports;
    }
}
