package SeleniumAutomation;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    WebDriver driver;
    WebDriverWait wait;


    @BeforeMethod
    public void setup () {
        //Create a Chrome driver. All test and page classes use this driver.
        System.setProperty("webdriver.chrome.driver", "\\testAutomation\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();

        //Create a wait. All test and page classes use this wait.
        wait = new WebDriverWait(driver,15);

        //Maximize Window
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown () {
        driver.quit();
    }
}