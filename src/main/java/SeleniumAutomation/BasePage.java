package SeleniumAutomation;

import SeleniumAutomation.Utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    public static WebDriver driver;

    public static void setDriver(WebDriver driverInstance) {
        driver = driverInstance;
    }

    public static WebElement returnNonStaleElement(By by){
        WebElement nonStaleElement = Driver.getInstance().findElement(by);
        int count = 0;
        try {
            nonStaleElement.isEnabled();
            return nonStaleElement;
        }catch (StaleElementReferenceException e){
            while (count < 4) {
                try {
                    //If exception generated that means It Is not able to find element then catch block will handle It.
                    nonStaleElement = Driver.getInstance().findElement(by);
                    nonStaleElement.isEnabled();
                    break;
                } catch (StaleElementReferenceException f) {
                    System.out.println("Trying to recover from a stale element :" + f.getMessage());
                    count += 1;
                }
            }
        }
        return nonStaleElement;
    }
}
