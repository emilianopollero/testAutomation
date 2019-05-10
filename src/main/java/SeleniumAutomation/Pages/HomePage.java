package SeleniumAutomation.Pages;

import SeleniumAutomation.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    @FindBy(id = "login_link")
    private static WebElement loginLink;
    @FindBy(id = "signup_link")
    private static WebElement signupLink;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public LoginPage clickLogin(){
        loginLink.click();
        return new LoginPage(driver, wait);
    }
}
