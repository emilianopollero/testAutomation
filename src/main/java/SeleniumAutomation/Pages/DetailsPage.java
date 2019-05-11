package SeleniumAutomation.Pages;


import SeleniumAutomation.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeClickable;
import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeVisible;

public class DetailsPage extends BasePage{
    @FindBy(css = "ul li:nth-of-type(1)")
    private WebElement nameDetails;
    @FindBy(css = "ul li:nth-of-type(2)")
    private WebElement emailDetails;
    @FindBy(linkText = "log out")
    private WebElement logOutLink;

    public DetailsPage() {
        PageFactory.initElements(driver, this);
    }

    public String getNameDetailsText() {
        waitForElementToBeVisible(nameDetails);
        return nameDetails.getText();
    }

    public String getEmailDetailsText() {
        waitForElementToBeVisible(emailDetails);
        return emailDetails.getText();
    }

    public LoginPage clickLogout(){
        waitForElementToBeClickable(logOutLink);
        logOutLink.click();
        return new LoginPage();
    }
}