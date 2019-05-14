package SeleniumAutomation.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestReporter implements ITestListener {

    private static ExtentReports reports;
    protected static ExtentTest test;

    public void onStart(ITestContext context) {
        System.out.println("on start");
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/target/extent-report/testReport.html");
        htmlReporter.config().setAutoCreateRelativePathMedia(true);
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);
    }

    public void onTestStart(ITestResult result) {
        System.out.println("on test start");
        test = reports.createTest(result.getMethod().getDescription());
        test.log(Status.INFO, result.getMethod().getMethodName() + "test is started");
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("on test success");
        test.log(Status.PASS, result.getMethod().getMethodName() + "test is passed");
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("on test failure");
        test.log(Status.FAIL, result.getMethod().getMethodName() + " test is failed");
        test.log(Status.FAIL, result.getThrowable());
        if (!result.getTestClass().getName().contains("ApiTests")) {
            try {
                TakesScreenshot ts = (TakesScreenshot) Driver.getInstance();
                File src = ts.getScreenshotAs(OutputType.FILE);
                String dest = System.getProperty("user.dir") + "/screenshots/" + result.getMethod().getMethodName() + ".png";
                File target = new File(dest);
                FileUtils.copyFile(src, target);
                test.addScreenCaptureFromPath(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("on test skipped");
        test.log(Status.SKIP, result.getMethod().getMethodName() + "test is skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("on test sucess within percentage");
    }

    public void onFinish(ITestContext context) {
        System.out.println("on finish");
        Driver.quit();
        reports.flush();
    }
}
