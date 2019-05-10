package SeleniumAutomation.Pages;

import SeleniumAutomation.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeClickable;
import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeVisible;

public class ProfilePage extends BasePage {
    @FindBy(css = "main h1")
    private WebElement title;
    @FindBy(css = "[class] p:nth-child(2)")
    private WebElement firstProfileText;
    @FindBy(css = "[class] p:nth-child(3)")
    private WebElement secondProfileText;
    @FindBy(css = "#users_list_table")
    private WebElement adminUsersTable;
    @FindBy(css = "#details_link")
    private WebElement detailsLink;
    @FindBy(linkText = "log out")
    private WebElement logOutLink;

    public ProfilePage() {
        PageFactory.initElements(driver, this);
    }

    public String getFirstProfileText() {
        waitForElementToBeVisible(firstProfileText);
        return firstProfileText.getText();
    }

    public LoginPage clickLogout(){
        waitForElementToBeClickable(logOutLink);
        logOutLink.click();
        return new LoginPage();
    }

    public String getSecondProfileText() {
        waitForElementToBeVisible(secondProfileText);
        return secondProfileText.getText();
    }

    public boolean checkAdminTableIsDisplayed() {
        return adminUsersTable.isDisplayed();
    }

    public DetailsPage clickOnDetails(){
        waitForElementToBeClickable(detailsLink);
        detailsLink.click();
        return new DetailsPage();
    }
}
