package SeleniumAutomation;

import SeleniumAutomation.Enums.ECredentials;
import SeleniumAutomation.Enums.EUsers;
import SeleniumAutomation.Pages.HomePage;
import SeleniumAutomation.Pages.LoginPage;
import SeleniumAutomation.Pages.ProfilePage;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test(priority = 1, description = "Login admin credentials test")
    @Description("This test verifies that admin users can login using valid credentials and that the proper profile page is shown to them")
    public void validAdminLoginTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver, wait);
        driver.get("https://waesworks.bitbucket.io/");
        LoginPage loginPage = homePage.clickLogin();
        ProfilePage profilePage = loginPage.logIn(ECredentials.ADMIN_USER, ECredentials.ADMIN_USER_PASS);
        profilePage.validateProfilePage(EUsers.ADMIN);
    }

    @Test(priority = 1, description = "Login dev credentials test")
    @Description("This test verifies that dev users can login using valid credentials and that the proper profile page is shown to them")
    public void validDevLoginTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver, wait);
        driver.get("https://waesworks.bitbucket.io/");
        LoginPage loginPage = homePage.clickLogin();
        ProfilePage profilePage = loginPage.logIn(ECredentials.DEV_USER, ECredentials.DEV_USER_PASS);
        profilePage.validateProfilePage(EUsers.DEV);
    }

    @Test(priority = 1, description = "Login dev credentials test")
    @Description("This test verifies that dev users can login using valid credentials and that the proper profile page is shown to them")
    public void validTesterLoginTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver, wait);
        driver.get("https://waesworks.bitbucket.io/");
        LoginPage loginPage = homePage.clickLogin();
        ProfilePage profilePage = loginPage.logIn(ECredentials.TESTER_USER, ECredentials.TESTER_USER_PASS);
        profilePage.validateProfilePage(EUsers.TESTER);
    }
}
