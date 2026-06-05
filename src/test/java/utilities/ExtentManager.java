package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            String timeStamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss")
                            .format(new Date());

            String reportPath =
                    System.getProperty("user.dir")
                    + "/Reports/ExtentReport_"
                    + timeStamp
                    + ".html";

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(reportPath);

            spark.config().setDocumentTitle("Tiara Automation Report");
            spark.config().setReportName("Regression Test Results");

            extent = new ExtentReports();

            extent.attachReporter(spark);

            extent.setSystemInfo("Tester", "Kunal");
            extent.setSystemInfo("Environment", "Staging");
            extent.setSystemInfo("Browser", "Chrome");
        }

        return extent;
    }
}