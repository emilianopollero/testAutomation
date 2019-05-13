package SeleniumAutomation.UITests;

import SeleniumAutomation.BaseTest;
import SeleniumAutomation.Enums.ECredentials;
import SeleniumAutomation.Pages.DetailsPage;
import SeleniumAutomation.Pages.HomePage;
import SeleniumAutomation.Pages.LoginPage;
import SeleniumAutomation.Pages.ProfilePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static SeleniumAutomation.BasePage.getTitleText;

public class DetailsPageTest extends BaseTest {

    private SoftAssert softAssert = new SoftAssert();

    // These tests verify the details page information for several users
    @Test(priority = 1, description = "UI: Validate the profile page information for the admin user")
    public void validateDetailsPageUserDetails() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("This test verified the profile page information for the admin user");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        ProfilePage profilePage = loginPage.logIn(ECredentials.ADMIN_USER.getValue(), ECredentials.ADMIN_USER_PASS.getValue());
        DetailsPage detailsPage = profilePage.clickOnDetails();
        System.out.println("Validating details page");
        softAssert.assertEquals("Your Details", getTitleText());
        softAssert.assertEquals("Name: Amazing Admin", detailsPage.getNameDetailsText());
        softAssert.assertEquals("Email address: a.admin@wearewaes.com", detailsPage.getEmailDetailsText());
        softAssert.assertAll();
    }

    @Test(priority = 1, description = "UI: Validate the profile page information for the dev user")
    public void validateDetailsPageDevDetailsTest() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("This test verified the profile page information for the dev user");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        ProfilePage profilePage = loginPage.logIn(ECredentials.DEV_USER.getValue(), ECredentials.DEV_USER_PASS.getValue());
        DetailsPage detailsPage = profilePage.clickOnDetails();
        System.out.println("Validating details page");
        softAssert.assertEquals("Your Details", getTitleText());
        softAssert.assertEquals("Name: Zuper Dooper Dev", detailsPage.getNameDetailsText());
        softAssert.assertEquals("Email address: zd.dev@wearewaes.com", detailsPage.getEmailDetailsText());
        softAssert.assertAll();
    }

    @Test(priority = 1, description = "UI: Validate the profile page information for the tester user")
    public void validateDetailsPageTesterDetailsTest() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("This test verified the profile page information for the tester user");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        ProfilePage profilePage = loginPage.logIn(ECredentials.TESTER_USER.getValue(), ECredentials.TESTER_USER_PASS.getValue());
        DetailsPage detailsPage = profilePage.clickOnDetails();
        System.out.println("Validating details page");
        softAssert.assertEquals("Your Details", getTitleText());
        softAssert.assertEquals("Name: Al Skept-Cal Tester", detailsPage.getNameDetailsText());
        softAssert.assertEquals("Email address: as.tester@wearewaes.com", detailsPage.getEmailDetailsText());
        softAssert.assertAll();
    }
}
