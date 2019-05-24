package SeleniumAutomation.Pages;


import SeleniumAutomation.BasePage;
import SeleniumAutomation.Interfaces.HeaderInterface;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeVisible;

public class DetailsPage extends BasePage implements HeaderInterface {
    @FindBy(css = "ul li:nth-of-type(1)")
    private WebElement nameDetails;
    @FindBy(css = "ul li:nth-of-type(2)")
    private WebElement emailDetails;

    public DetailsPage() {
        PageFactory.initElements(driver, this);
        System.out.println("Going to the Details Page");
    }

//    Returns a String with the name on the Details Page
    public String getNameDetailsText() {
        waitForElementToBeVisible(nameDetails);
        return nameDetails.getText();
    }

//    Returns a String with the email on the Details Page
    public String getEmailDetailsText() {
        waitForElementToBeVisible(emailDetails);
        return emailDetails.getText();
    }
}