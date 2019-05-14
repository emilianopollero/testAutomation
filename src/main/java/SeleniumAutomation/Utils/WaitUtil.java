package SeleniumAutomation.Utils;

import SeleniumAutomation.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WaitUtil extends BasePage {

    private static int timeout = 60;
    private static int tryEverySeconds = 1;
    private static FluentWait waiting = new FluentWait(driver)
            .withTimeout(timeout, TimeUnit.SECONDS)
            .pollingEvery(tryEverySeconds, TimeUnit.SECONDS)
            .ignoring(NoSuchElementException.class);

    //An expectation for checking an element is visible and enabled such that you can click it.
    public static void waitForElementToBeClickable(WebElement element) {
        waiting.until(elementToBeClickable(element));
    }

    //Wait until element is visible
    public static void waitForElementToBeVisible(WebElement element) {
        waiting.until(visibilityOf(element));
    }

}
