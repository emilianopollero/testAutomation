package SeleniumAutomation.Pages;

import SeleniumAutomation.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

    @FindBy(id = "login_link")
    private static WebElement loginLink;
    @FindBy(id = "signup_link")
    private static WebElement signupLink;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public LoginPage clickLogin(){
        loginLink.click();
        return new LoginPage();
    }
}
