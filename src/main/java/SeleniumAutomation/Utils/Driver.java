package SeleniumAutomation.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermissions;

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
        OsCheck.OSType ostype = OsCheck.getOperatingSystemType();
        switch (ostype) {
            case Windows:
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
                break;
            case Linux:
                switch (browserName){
                    case "chrome":
                        Path path = Paths.get("/testAutomation/drivers/chromedriver");
                        try {
                            Files.setPosixFilePermissions(path, PosixFilePermissions.fromString("r-xr-xr-x"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.setProperty("webdriver.chrome.driver", "/testAutomation/drivers/chromedriver.exe");
                        driver = new ChromeDriver();
                        break;
                    case "headless":
                        File file = new File("/testAutomation/drivers/chromedriver");
                        String stringPath = file.getAbsolutePath();
                        System.setProperty("webdriver.chrome.driver", stringPath);
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("--headless");
                        driver = new ChromeDriver(chromeOptions);
                        break;
                }
            case MacOS:
                switch (browserName)
                {
                    case "Chrome": //CHROME DRIVER
                        System.setProperty("webdriver.chrome.driver", "driver/macChromeDriver");
                        driver = new ChromeDriver();
                        break;
                    case "Headless":
                        System.setProperty("webdriver.chrome.driver", "driver/macChromeDriver");
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("--headless");
                        driver = new ChromeDriver(chromeOptions);
                }
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
