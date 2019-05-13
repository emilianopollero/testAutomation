package SeleniumAutomation.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;

public class Driver {
    private static String browserName;
    private static WebDriver driver;

    // Returns driver object
    public static WebDriver getInstance() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    // Sets driver browser
    public static void init(String browser) {
        browserName = browser;
    }

    // Creates driver object using browser variable
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
            case "edge":
                file = new File("/testAutomation/drivers/MicrosoftWebDriver.exe");
                path = file.getAbsolutePath();
                System.out.println(path);
                System.setProperty("webdriver.edge.driver", path);
                EdgeOptions options = new EdgeOptions();
                options.setCapability("InPrivate", true);
                driver = new EdgeDriver(options);
                break;
        }
        return driver;
    }

    // Quits the driver
    public static void quit() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }
}
