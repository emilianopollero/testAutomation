package SeleniumAutomation.Pages;

import SeleniumAutomation.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeClickable;

public class SignUpPage extends BasePage {
    @FindBy(id = "username_input")
    private WebElement usernameField;
    @FindBy(id = "password_input")
    private WebElement passwordField;
    @FindBy(id = "name_input")
    private WebElement nameField;
    @FindBy(id = "email_input")
    private WebElement emailField;
    @FindBy(id = "day_select")
    private WebElement calendarDay;
    @FindBy(id = "month_select")
    private WebElement calendarMonth;
    @FindBy(id = "year_select")
    private WebElement calendarYear;
    @FindBy(id = "submit_button")
    private WebElement submitBtn;

    public SignUpPage() {
        PageFactory.initElements(driver, this);
    }

    public NewUserPage signUp(String username, String password, String name, String email,
                              String day, Month month, String year) {
        System.out.println("Signing up new user");
        enterUsername(username);
        enterPassword(password);
        enterName(name);
        enterEmail(email);
        selectDay(day);
        selecMonth(month);
        selectYear(year);
        submit();
        return new NewUserPage();
    }

    public NewUserPage signUp(String username, String password, String name, String email,
                              String day, String month, String year) {
        System.out.println("Signing up new user");
        enterUsername(username);
        enterPassword(password);
        enterName(name);
        enterEmail(email);
        selectDay(day);
        selecMonth(month);
        selectYear(year);
        submit();
        return new NewUserPage();
    }

    public void enterUsername(String username) {
        System.out.println("Entering username: " + username);
        waitForElementToBeClickable(usernameField);
        enterText(usernameField, username);
    }

    public void enterPassword(String password) {
        System.out.println("Entering password: " + password);
        waitForElementToBeClickable(passwordField);
        enterText(passwordField, password);
    }

    public void enterName(String name) {
        System.out.println("Entering name: " + name);
        waitForElementToBeClickable(nameField);
        enterText(nameField, name);
    }

    public void enterEmail(String email) {
        System.out.println("Entering email: " + email);
        waitForElementToBeClickable(emailField);
        enterText(emailField, email);
    }

    public void selectDay(String day) {
        System.out.println("Selecting day: " + day);
        getSelectElement(calendarDay).selectByValue(day);
    }

    public void selecMonth(Month month) {
        Locale locale = Locale.ENGLISH;
        System.out.println("Selecting month: " + month.getDisplayName(TextStyle.FULL, locale));
        getSelectElement(calendarMonth).selectByValue(month.getDisplayName(TextStyle.FULL, locale));
    }

    public void selecMonth(String month) {
        Locale locale = Locale.ENGLISH;
        System.out.println("Selecting month: " + month);
        getSelectElement(calendarMonth).selectByValue(month);
    }

    public void selectYear(String year) {
        System.out.println("Selecting year: " + year);
        getSelectElement(calendarYear).selectByValue(year);
    }

    public void submit() {
        waitForElementToBeClickable(submitBtn);
        scrollIntoElement(submitBtn);
        submitBtn.click();
    }
}
