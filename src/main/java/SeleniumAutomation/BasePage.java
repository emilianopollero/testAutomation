package SeleniumAutomation;

import SeleniumAutomation.Utils.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeVisible;

public class BasePage {
    public static WebDriver driver;

    // Sets driver for base page
    public static void setDriver(WebDriver driverInstance) {
        driver = driverInstance;
    }

    // Clears input field and enters given String
    public static void enterText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    // Returns a selenium Select object from a given WebElement, as PageFactory is not picking up Select objects for
    // initialization, one must initialize the Select as a WebElement on PageFactory, and then use this method to get
    // the Select implementation of it
    public static Select getSelectElement(WebElement element) {
        return new Select(element);
    }

    // This is used when having issues with stale elements, if StaleElementReferenceException is thrown, it will try to
    // get the element again 4 times by using the given By and return a WebElement if successful.
    public static WebElement returnNonStaleElement(By by) {
        WebElement nonStaleElement = Driver.getInstance().findElement(by);
        int count = 0;
        try {
            nonStaleElement.isEnabled();
            return nonStaleElement;
        } catch (StaleElementReferenceException e) {
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

    // Useful for errors of type, "Element not clickable at point xxx, other element would receive the click",
    // use before the action that is presenting that said issue.
    public static void scrollIntoElement(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("var rectSize = arguments[0].getBoundingClientRect();" +
                        "var elementTop = rectSize.top + window.pageYOffset;" +
                        "var middle = elementTop - (window.innerHeight / 2);" +
                        "window.scrollTo(0, middle);", element);
    }

    // Particular to this website, this returns the page subtitle for any of the site pages.
    public static String getTitleText() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForElementToBeVisible(returnNonStaleElement(By.cssSelector("main h1")));
        return driver.findElement(By.cssSelector("main h1")).getText();
    }
}
