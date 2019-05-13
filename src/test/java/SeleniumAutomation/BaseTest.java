package SeleniumAutomation;


import SeleniumAutomation.Utils.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
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
}