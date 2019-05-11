package SeleniumAutomation.Pages;

import SeleniumAutomation.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeVisible;


public class NewUserPage extends BasePage {
    @FindBy(css = "[class] p:nth-child(2)")
    private WebElement firstProfileText;

    public NewUserPage() {
        PageFactory.initElements(driver, this);
    }

    public String getFirstProfileText() {
        waitForElementToBeVisible(firstProfileText);
        return firstProfileText.getText();
    }
}
