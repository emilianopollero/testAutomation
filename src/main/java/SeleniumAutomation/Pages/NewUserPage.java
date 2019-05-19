package SeleniumAutomation.Pages;

import SeleniumAutomation.BasePage;
import SeleniumAutomation.Interfaces.HeaderInterface;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeVisible;


public class NewUserPage extends BasePage implements HeaderInterface {
    @FindBy(css = "[class] p:nth-child(2)")
    private WebElement welcomeText;

    public NewUserPage() {
        PageFactory.initElements(driver, this);
    }

//    Method to return String text of the welcome message
    public String getWelcomeText() {
        waitForElementToBeVisible(welcomeText);
        return welcomeText.getText();
    }
}
