package SeleniumAutomation.Pages;

import SeleniumAutomation.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeVisible;

public class LoginPage extends BasePage {
    @FindBy(id = "username_input")
    private WebElement usernameField;
    @FindBy(id = "password_input")
    private WebElement passwordField;
    @FindBy(id = "login_button")
    private WebElement loginBtn;
    @FindBy(css = "main h1")
    private WebElement title;
    @FindBy(css = "[class^='status-module--status__text']")
    private WebElement statusTextBox;

//    Login page constructor asserts page title is correct and url is correct
    public LoginPage() {
        PageFactory.initElements(driver, this);
        Assert.assertEquals(getTitleText(), "Log In");
        Assert.assertTrue(driver.getCurrentUrl().contains("app/login"));
    }

//    Enters parametrized values username and password on username and password fields, clicks on login button and returns
//    a new ProfilePage
    public ProfilePage logIn(String username, String password) {
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
