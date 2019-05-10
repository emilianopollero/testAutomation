package SeleniumAutomation;

import SeleniumAutomation.Enums.ECredentials;
import SeleniumAutomation.Pages.DetailsPage;
import SeleniumAutomation.Pages.HomePage;
import SeleniumAutomation.Pages.LoginPage;
import SeleniumAutomation.Pages.ProfilePage;
import SeleniumAutomation.Utils.Driver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static SeleniumAutomation.BasePage.getTitleText;

public class DetailsPageTest extends BaseTest{

    private SoftAssert softAssert = new SoftAssert();

    @Test(priority = 1)
    public void validateDetailsPageUserDetails(){
        System.out.println("This test verifies the details page information");
        Driver.getInstance().get("https://waesworks.bitbucket.io/");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        ProfilePage profilePage = loginPage.logIn(ECredentials.ADMIN_USER.getValue(), ECredentials.ADMIN_USER_PASS.getValue());
        DetailsPage detailsPage = profilePage.clickOnDetails();
        System.out.println("Validating details page");
        softAssert.assertEquals("Your Details", getTitleText());
        softAssert.assertEquals("Name: Amazing Admina", detailsPage.getNameDetailsText());
        softAssert.assertEquals("Email address: a.admin@wearewaes.com", detailsPage.getEmailDetailsText());
        System.out.println("Details page shows correct information");
        softAssert.assertAll();
    }
}
