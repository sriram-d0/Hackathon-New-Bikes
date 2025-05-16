package mainclasses;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportGeneration {

    protected static ExtentReports extent; // Shared across all test classes
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>(); // Thread-safe ExtentTest instance

    @BeforeSuite
    public void setupExtentReports() {
        // Initialize the ExtentSparkReporter with the report file path
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        spark.config().setReportName("Automation Test Report");
        spark.config().setDocumentTitle("Test Execution Report");
        spark.config().setTheme(Theme.DARK);
        // Initialize ExtentReports and attach the Spark Reporter
        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Set System Info
        extent.setSystemInfo("Testers", "Uday Sriram, Satish Kumar, Manjusha, Vasavi Priya");
        extent.setSystemInfo("Environment", "Eclipse");
        
    }

    @AfterSuite
    public void tearDownExtentReports() {
        // Flush the report to generate the HTML file
        if (extent != null) {
            extent.flush();
        }
    }

    protected ExtentTest createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest); // Assign it to the thread-local variable
        return extentTest;
    }

    protected ExtentTest getTest() {
        return test.get(); // Retrieve the thread-local ExtentTest instance
    }
}