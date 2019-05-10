package SeleniumAutomation.Pages;

import SeleniumAutomation.BasePage;
import SeleniumAutomation.Enums.ECredentials;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

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

    SoftAssert softAssert = new SoftAssert();

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public ProfilePage logIn(ECredentials username, ECredentials password){
        System.out.println("Entering username on login username field");
        waitForElementToBeVisible(usernameField);
        usernameField.clear();
        usernameField.sendKeys(username.getValue());
        System.out.println("Entering password on login password field");
        passwordField.clear();
        passwordField.sendKeys(password.getValue());
        loginBtn.click();
        return new ProfilePage(driver, wait);
    }

    public void validateUserNotLoggedIn(){
        System.out.println("Validating that user has not been logged in");
        waitForElementToBeVisible(title);
            softAssert.assertEquals(title.getText(), "Log In");
            try {
//                clickLogout();
            }catch (NoSuchElementException ignored){}
            softAssert.assertAll();
    }
}
