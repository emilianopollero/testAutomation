package SeleniumAutomation.Pages;

import SeleniumAutomation.BasePage;
import SeleniumAutomation.Interfaces.HeaderInterface;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeVisible;

public class LoginPage extends BasePage implements HeaderInterface {
    @FindBy(id = "username_input")
    private WebElement usernameField;
    @FindBy(id = "password_input")
    private WebElement passwordField;
    @FindBy(id = "login_button")
    private WebElement loginBtn;

//    Login page constructor asserts page title is correct and url is correct
    public LoginPage() {
        PageFactory.initElements(driver, this);
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
