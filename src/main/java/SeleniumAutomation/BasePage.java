package SeleniumAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    //Constructor
    public BasePage (WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    //Click Method by selector
    public void click (By elementLocation) {
        driver.findElement(elementLocation).click();
    }

    //Click Method By WebElement
    public void click(WebElement element){
        element.click();
    }

    //Write Text by selector
    public void writeText (By elementLocation, String text) {
        driver.findElement(elementLocation).clear();
        driver.findElement(elementLocation).sendKeys(text);
    }

    //Write Text by element
    public void writeText (WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    //Get element text by selector
    public String readText (By elementLocation) {
        return driver.findElement(elementLocation).getText();
    }

    //Get element text by element
    public String readText (WebElement element) {
        return element.getText();
    }
}
