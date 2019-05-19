package SeleniumAutomation.Interfaces;

import SeleniumAutomation.BasePage;
import SeleniumAutomation.Pages.DetailsPage;
import SeleniumAutomation.Pages.HomePage;
import SeleniumAutomation.Pages.LoginPage;
import SeleniumAutomation.Pages.ProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeClickable;

public class HeaderInterfaceImpl extends BasePage {
    @FindBy(linkText = "log out")
    private static WebElement logOutLink;
    @FindBy(id = "home_link")
    private static WebElement homeLink;
    @FindBy(id = "details_link")
    private static WebElement detailsLink;
    @FindBy(id = "profile_link")
    private static WebElement profileLink;
    @FindBy(id = "status")
    private static WebElement statusBar;

    public HeaderInterfaceImpl(){
        PageFactory.initElements(driver, this);
    }

    static LoginPage clickLogout(){
        waitForElementToBeClickable(logOutLink);
        logOutLink.click();
        return new LoginPage();
    }

    static HomePage clickHomeLink(){
        waitForElementToBeClickable(homeLink);
        homeLink.click();
        return new HomePage();
    }

    static DetailsPage clickDetailsLink(){
        waitForElementToBeClickable(detailsLink);
        detailsLink.click();
        return new DetailsPage();
    }

    static ProfilePage clickProfileLink(){
        waitForElementToBeClickable(profileLink);
        profileLink.click();
        return new ProfilePage();
    }

    static String getStatusText(){
        waitForElementToBeClickable(statusBar);
        return returnNonStaleElement(By.id("status")).getText();
    }
}
