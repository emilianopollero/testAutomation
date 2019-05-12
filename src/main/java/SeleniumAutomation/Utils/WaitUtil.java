package SeleniumAutomation.Utils;

import SeleniumAutomation.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    public static void waitUntilAlertIsPresent() {
        waiting.until(ExpectedConditions.alertIsPresent());
    }

    //An expectation for checking WebElement with given locator has attribute which contains specific value
    public static void waitUntilElementAttributeContains(WebElement element, String attribute, String attributeValue) {
        waiting.until(attributeContains(element, attribute, attributeValue));
    }

    //An expectation for checking WebElement with given locator has attribute with a specific value
    public static void waitUntilAttributeToBe(WebElement element, String attribute, String attributeValue) {
        waiting.until(attributeToBe(element, attribute, attributeValue));
    }

    //An expectation for checking WebElement any non empty value for given attribute
    public static void waitUntilAttributeToBeNotEmpty(WebElement element, String attribute) {
        waiting.until(attributeToBeNotEmpty(element, attribute));
    }

    //An expectation for checking if the given element is selected.
    public static void waitUntilElementIsSelected(WebElement element) {
        waiting.until(elementToBeSelected(element));
    }

    //An expectation for checking an element is visible and enabled such that you can click it.
    public static void waitForElementToBeClickable(WebElement element) {
        waiting.until(elementToBeClickable(element));
    }

    //An expectation for checking whether the given frame is available to switch to.
    public static void waitForFrameToBeAvailableAndSwitchToIt(WebElement element) {
        waiting.until(frameToBeAvailableAndSwitchToIt(element));
    }

    //An expectation for checking the element to be invisible
    public static void waitForInvisibilityOfElement(WebElement element) {
        waiting.until(invisibilityOf(element));
    }

    //An expectation with the logical opposite condition of the given condition.
    public static void waitUntilConditionIsFalse(ExpectedCondition condition) {
        waiting.until(not(condition));
    }

    //Wait until an element is no longer attached to the DOM.
    public static void waitForElementToBeStale(WebElement element) {
        waiting.until(stalenessOf(element));
    }

    //Wait until element text matches
    public static void waitForElementTextToMatch(By by, String text) {
        waiting.until(textToBe(by, text));
    }

    //Wait until element text is present
    public static void waitForTextToBePresentInElement(WebElement element, String text) {
        waiting.until(textToBePresentInElement(element, text));
    }

    //Wait until element text is present
    public static void waitForTextToBePresentInElement(By by, String text) {
        waiting.until(textToBePresentInElementLocated(by, text));
    }

    //Wait until page title is exactly the given string
    public static void waitUntilPageTitleIs(String title) {
        waiting.until(titleIs(title));
    }

    //Wait until page title contains the given string
    public static void waitUntilPageTitleContains(String title) {
        waiting.until(titleContains(title));
    }

    //Wait until page url contains the given string
    public static void waitUntilUrlContains(String urlText) {
        waiting.until(urlContains(urlText));
    }

    //Wait until page url is exactly the given string
    public static void waitUntilUrlIs(String urlText) {
        waiting.until(urlToBe(urlText));
    }

    //Wait until element is visible
    public static void waitForElementToBeVisible(WebElement element) {
        waiting.until(visibilityOf(element));
    }

    //Wait until element is visible
    public static void waitForElementToBeVisibleBy(By by) {
        waiting.until(invisibilityOfElementLocated(by));
    }


    //Wait until all element are visible
    public static void waitForAllElementsToBeVisible(WebElement... element) {
        waiting.until(visibilityOfAllElements(element));
    }
}
