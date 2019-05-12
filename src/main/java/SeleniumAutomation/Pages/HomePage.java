package SeleniumAutomation.Pages;

import SeleniumAutomation.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeClickable;

public class HomePage extends BasePage {

    @FindBy(id = "login_link")
    private static WebElement loginLink;
    @FindBy(id = "signup_link")
    private static WebElement signupLink;

//    Home page constructor checks that the user is on the Home Page by asserting title and url
    public HomePage() {
        PageFactory.initElements(driver, this);
        Assert.assertEquals(getTitleText(), "WAES Tester Assignment");
        Assert.assertTrue(driver.getCurrentUrl().contains("waesworks.bitbucket.io"));
        System.out.println("Going to home page");
    }

//    Clicks on the Login link and returns a new LoginPage
    public LoginPage clickLogin() {
        waitForElementToBeClickable(loginLink);
        loginLink.click();
        return new LoginPage();
    }

//    Clicks on the Sign Up link and returns a new SignUpPage
    public SignUpPage clickSignUp() {
        waitForElementToBeClickable(signupLink);
        signupLink.click();
        return new SignUpPage();
    }
}
