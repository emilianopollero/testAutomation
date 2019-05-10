package SeleniumAutomation.Pages;

import SeleniumAutomation.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeVisible;

public class LoginPage extends BasePage{
    @FindBy(id = "username_input")
    private WebElement usernameField;
    @FindBy(id = "password_input")
    private WebElement passwordField;
    @FindBy(id = "login_button")
    private WebElement loginBtn;
    @FindBy(css = "main h1")
    private WebElement title;

    public LoginPage() {
        PageFactory.initElements(driver, this);
        List<WebElement> requiredElements = new ArrayList<>();
        requiredElements.add(usernameField);
        requiredElements.add(passwordField);
        requiredElements.add(loginBtn);
        loadPage(requiredElements);
        Assert.assertEquals(getTitleText(), "Log In");
        Assert.assertTrue(driver.getCurrentUrl().contains("app/login"));
    }

    public ProfilePage logIn(String username, String password){
        System.out.println("Entering username on login username field");
        waitForElementToBeVisible(usernameField);
        usernameField.clear();
        usernameField.sendKeys(username);
        System.out.println("Entering password on login password field");
        passwordField.clear();
        passwordField.sendKeys(password);
        loginBtn.click();
        return new ProfilePage();
    }
}
