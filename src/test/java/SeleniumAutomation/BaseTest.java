package SeleniumAutomation;


import SeleniumAutomation.Utils.Driver;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

public class BaseTest implements ITestListener {
//Sets the driver to headless or chrome before running the test
    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) {
        if (browser == null) {
            Driver.init(System.getProperty("browser"));
        } else {
            Driver.init(browser);
        }
    }
//This method calls the browser instance, deletes cookies, maximizes its window and sets the BasePage driver variable
    @BeforeMethod
    public void openBrowser() {
        Driver.getInstance();
        Driver.getInstance().get("https://waesworks.bitbucket.io/");
        Driver.getInstance().manage().deleteAllCookies();
        Driver.getInstance().manage().window().maximize();
        BasePage.setDriver(Driver.getInstance());
    }
//Quits the driver after each test
    @AfterMethod
    public void teardown() {
        Driver.quit();
    }

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
        if (!result.getTestClass().getName().contains("ApiTests")){
            try {
                TakesScreenshot ts = (TakesScreenshot) Driver.getInstance();
                File src = ts.getScreenshotAs(OutputType.FILE);
                String dest = System.getProperty("user.dir") + "/screenshots/" + result.getMethod().getMethodName() + ".png";
                File target = new File(dest);
                FileUtils.copyFile(src, target);
//            test.log(Status.FAIL, "Screenshot").addScreenCaptureFromPath("C:\\testAutomation\\screenshots\\validUsernameInUppercaseLogin.jpg");
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