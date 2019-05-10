package SeleniumAutomation.Pages;

import SeleniumAutomation.BasePage;
import SeleniumAutomation.Enums.EUsers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static SeleniumAutomation.Utils.WaitUtil.waitForElementToBeVisible;

public class ProfilePage extends BasePage   {
    @FindBy(css = "main h1")
    private WebElement title;
    @FindBy(css = "[class] p:nth-child(2)")
    private WebElement firstProfileText;
    @FindBy(css = "[class] p:nth-child(3)")
    private WebElement secondProfileText;
    @FindBy(css = "#users_list_table")
    private WebElement adminUsersTable;

    public ProfilePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void validateProfilePage(EUsers user){
        SoftAssert softAssert = new SoftAssert();
        waitForElementToBeVisible(firstProfileText);
        switch (user.getValue()){
            case "admin":
                System.out.println("Validating profile page for admin user");

                softAssert.assertEquals("How are you doing, Amazing Admin?", firstProfileText.getText());
                softAssert.assertEquals("Your super power: Change the course of a waterfall.", secondProfileText.getText());
                Assert.assertTrue(adminUsersTable.isDisplayed());
                System.out.println("Admin user correctly logged in");
                break;
            case "dev":
                System.out.println("Validating profile page for dev user");
                softAssert.assertEquals("How are you doing, Zuper Dooper Dev?", firstProfileText.getText());
                softAssert.assertEquals("Your super power: Debug a repellent factory storage.", secondProfileText.getText());
                System.out.println("Dev user correctly logged in");
                break;
            case "tester":
                System.out.println("Validating profile page for tester user");
                softAssert.assertEquals("How are you doing, Al Skept-Cal Tester?", firstProfileText.getText());
                softAssert.assertEquals("Your super power: Voltage AND Current.", secondProfileText.getText());
                System.out.println("Tester user correctly logged in");
                break;
        }
        softAssert.assertAll();
    }
}
