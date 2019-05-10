package SeleniumAutomation.Pages;

import SeleniumAutomation.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeClickable;

public class HomePage extends BasePage{

    @FindBy(id = "login_link")
    private static WebElement loginLink;
    @FindBy(id = "signup_link")
    private static WebElement signupLink;

    public HomePage() {
        PageFactory.initElements(driver, this);
        List<WebElement> requiredElements = new ArrayList<>();
        requiredElements.add(loginLink);
        requiredElements.add(signupLink);
        loadPage(requiredElements);
        Assert.assertEquals(getTitleText(), "WAES Tester Assignment");
        Assert.assertTrue(driver.getCurrentUrl().contains("waesworks.bitbucket.io"));
    }

    public LoginPage clickLogin(){
        waitForElementToBeClickable(loginLink);
        loginLink.click();
        return new LoginPage();
    }
    public SignUpPage clickSignUp(){
        waitForElementToBeClickable(signupLink);
        signupLink.click();
        return new SignUpPage();
    }
}
