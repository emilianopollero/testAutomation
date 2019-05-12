package SeleniumAutomation.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class Driver {
    private static String browserName;
    private static WebDriver driver;
// Driver singleton
    public static WebDriver getInstance() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    public static void init(String browser) {
        browserName = browser;
    }

    private static WebDriver createDriver() {
        switch (browserName.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "/testAutomation/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "headless":
                File file = new File("/testAutomation/drivers/chromedriver.exe");
                String path = file.getAbsolutePath();
                System.out.println(path);
                System.setProperty("webdriver.chrome.driver", path);
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                break;
        }
        return driver;
    }

    public static void quit() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }
}
