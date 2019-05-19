package SeleniumAutomation.UITests;

import SeleniumAutomation.BaseTest;
import SeleniumAutomation.Enums.ECredentials;
import SeleniumAutomation.Pages.HomePage;
import SeleniumAutomation.Pages.LoginPage;
import SeleniumAutomation.Pages.ProfilePage;
import SeleniumAutomation.Utils.ConfigFileReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class StatusBarTextTest extends BaseTest {

    private SoftAssert softAssert = new SoftAssert();

    private ConfigFileReader reader = new ConfigFileReader();

    @Test(priority = 1, description = "Check status bar text changes when credentials are invalid")
    public void statusBarTextWrongCredentialsTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Check status bar text changes when credentials are invalid");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.logIn(reader.getCredentials(ECredentials.ADMIN_USER), "123");
        softAssert.assertEquals("Wrong credentials. You can do it, try again!", loginPage.getStatusText());
        softAssert.assertAll();
    }


    @Test(priority = 1, description = "Check status bar text changes when valid credentials are used after invalid ones")
    public void statusBarTextUpdatesWithValidCredentialsTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Check status bar text changes when valid credentials are used after invalid ones");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.logIn(reader.getCredentials(ECredentials.ADMIN_USER), "123");
        Assert.assertEquals("Wrong credentials. You can do it, try again!", loginPage.getStatusText());
        ProfilePage profilePage = loginPage.logIn(reader.getCredentials(ECredentials.ADMIN_USER), reader.getCredentials(ECredentials.ADMIN_USER_PASS));
        Assert.assertEquals("Logged in as Amazing Admin (a.admin@wearewaes.com)! log out", profilePage.getStatusText());
    }

    @Test(priority = 1, description = "Check status bar text resets when moving to another page after error message has been shown.")
    public void statusBarTextResetsWhenMovingOutOfPageAfterInvalidLoginTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Check status bar text resets when moving to another page after error message has been shown.");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.logIn(reader.getCredentials(ECredentials.ADMIN_USER), "123");
        homePage = loginPage.clickHomeLink();
        Assert.assertEquals("To get the full hero experience, you’ll need to log in.", homePage.getStatusText());
    }

    @Test(priority = 1, description = "Check status bar text changes user logs out")
    public void statusBarTextChangesWhenLogOut(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Check status bar text changes user logs out");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        ProfilePage profilePage = loginPage.logIn(reader.getCredentials(ECredentials.ADMIN_USER), reader.getCredentials(ECredentials.ADMIN_USER_PASS));
        profilePage.clickLogout();
        Assert.assertEquals("To get the full hero experience, you’ll need to log in.", loginPage.getStatusText());
    }
}
