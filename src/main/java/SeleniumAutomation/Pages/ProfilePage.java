package SeleniumAutomation.Pages;

import SeleniumAutomation.BasePage;
import SeleniumAutomation.Interfaces.HeaderInterface;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeClickable;
import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeVisible;

public class ProfilePage extends BasePage implements HeaderInterface {
    @FindBy(css = "[class] p:nth-child(2)")
    private WebElement firstProfileText;
    @FindBy(css = "[class] p:nth-child(3)")
    private WebElement secondProfileText;
    @FindBy(css = "#users_list_table")
    private WebElement adminUsersTable;

    public ProfilePage() {
        PageFactory.initElements(driver, this);
    }

    //    Method to return String text of the first profile text
    public String getFirstProfileText() {
        waitForElementToBeVisible(firstProfileText);
        return firstProfileText.getText();
    }

    //    Method to return String text of the second profile text
    public String getSecondProfileText() {
        waitForElementToBeVisible(secondProfileText);
        return secondProfileText.getText();
    }

    //    Returns boolean true or false if the admin users table is shown or not
    public boolean checkAdminTableIsDisplayed() {
        return adminUsersTable.isDisplayed();
    }
}
