package SeleniumAutomation;

import SeleniumAutomation.Utils.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeVisible;

public class BasePage {
    public static WebDriver driver;

    public static void setDriver(WebDriver driverInstance) {
        driver = driverInstance;
    }

    public static void enterText(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }

    public static Select getSelectElement(WebElement element){
        return new Select(element);
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

    public static void scrollIntoElement(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("var rectSize = arguments[0].getBoundingClientRect();" +
                        "var elementTop = rectSize.top + window.pageYOffset;" +
                        "var middle = elementTop - (window.innerHeight / 2);" +
                        "window.scrollTo(0, middle);", element);
    }

    public static String getTitleText(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForElementToBeVisible(returnNonStaleElement(By.cssSelector("main h1")));
        return driver.findElement(By.cssSelector("main h1")).getText();
    }

    protected void loadPage(List<WebElement> mandatoryElements) {
        //Wait for dom to go to ready state
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        // Wait fo mandatory elements to be clickable
        for (WebElement elm : mandatoryElements) {
            waitForElementToBeVisible(elm);
        }
    }
}
