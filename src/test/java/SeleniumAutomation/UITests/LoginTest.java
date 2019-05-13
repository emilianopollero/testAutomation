package SeleniumAutomation.UITests;

import SeleniumAutomation.BaseTest;
import SeleniumAutomation.Enums.ECredentials;
import SeleniumAutomation.Pages.HomePage;
import SeleniumAutomation.Pages.LoginPage;
import SeleniumAutomation.Pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static SeleniumAutomation.BasePage.driver;
import static SeleniumAutomation.BasePage.getTitleText;

public class LoginTest extends BaseTest {

    private SoftAssert softAssert = new SoftAssert();

    //    This test verifies admin user can login and profile page for admin user is presented
    @Test(priority = 1, description = "UI: Validate admin user can login and proper profile page is shown")
    public void validAdminLoginTest() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("This test verifies that admin users can login using valid credentials and that the proper " +
                "profile page is shown to them");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        ProfilePage profilePage = loginPage.logIn(ECredentials.ADMIN_USER.getValue(), ECredentials.ADMIN_USER_PASS.getValue());
        Assert.assertTrue(driver.getCurrentUrl().contains("app/profile"));
        System.out.println("Validating profile page for admin user");
        softAssert.assertEquals("Your Profile", getTitleText());
        softAssert.assertEquals("How are you doing, Amazing Admin?", profilePage.getFirstProfileText());
        softAssert.assertEquals("Your super power: Change the course of a waterfall.", profilePage.getSecondProfileText());
        Assert.assertTrue(profilePage.checkAdminTableIsDisplayed());
        System.out.println("Admin user correctly logged in");
        softAssert.assertAll();
    }

    //    This test verifies dev user can login and profile page for dev user is presented
    @Test(priority = 1, description = "UI: Validate dev user can login and proper profile page is shown")
    public void validDevLoginTest() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("This test verifies that dev users can login using valid credentials and that the proper " +
                "profile page is shown to them");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        ProfilePage profilePage = loginPage.logIn(ECredentials.DEV_USER.getValue(), ECredentials.DEV_USER_PASS.getValue());
        Assert.assertTrue(driver.getCurrentUrl().contains("app/profile"));
        System.out.println("Validating profile page for dev user");
        softAssert.assertEquals("How are you doing, Zuper Dooper Dev?", profilePage.getFirstProfileText());
        softAssert.assertEquals("Your super power: Debug a repellent factory storage.", profilePage.getSecondProfileText());
        System.out.println("Dev user correctly logged in");
        softAssert.assertAll();
    }

    //    This test verifies tester user can login and profile page for tester user is presented
    @Test(priority = 1, description = "UI: Validate tester user can login and proper profile page is shown")
    public void validTesterLoginTest() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("This test verifies that tester users can login using valid credentials and that the proper " +
                "profile page is shown to them");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        ProfilePage profilePage = loginPage.logIn(ECredentials.TESTER_USER.getValue(), ECredentials.TESTER_USER_PASS.getValue());
        Assert.assertTrue(driver.getCurrentUrl().contains("app/profile"));
        System.out.println("Validating profile page for tester user");
        softAssert.assertEquals("How are you doing, Al Skept-Cal Tester?", profilePage.getFirstProfileText());
        softAssert.assertEquals("Your super power: Voltage AND Current.", profilePage.getSecondProfileText());
        System.out.println("Tester user correctly logged in");
        softAssert.assertAll();
    }

    //    This test verifies user can log out of the application
    @Test(priority = 1, description = "UI: Validate user can logout")
    public void logOutTest() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("This test verifies that the user can log out of the application");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        ProfilePage profilePage = loginPage.logIn(ECredentials.TESTER_USER.getValue(), ECredentials.TESTER_USER_PASS.getValue());
        profilePage.clickLogout();
        Assert.assertEquals("Log In", getTitleText());
        System.out.println("User successfully logged out of the application");
    }

    //    This test verifies that username is not case sensitive
    @Test(priority = 1, description = "UI: Validate username is not case sensitive")
    //No specific requirement for this
    public void validUsernameInUppercaseLogin() throws InterruptedException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("This test verifies that username is not case sensitive");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.logIn(ECredentials.TESTER_USER.getValue().toUpperCase(), ECredentials.TESTER_USER_PASS.getValue());
        Thread.sleep(1000);
        Assert.assertTrue(driver.getCurrentUrl().contains("app/profile"));
    }

//    These tests verify that the system handles invalid credentials properly
    @Test(priority = 1, description = "UI: Validate incorrect username login")
    public void invalidLoginWrongUsernameTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("This test checks that user is not logged using a non existent username");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.logIn("seven", "hero");
        System.out.println("Validating that user has not been logged in");
        softAssert.assertEquals("Log In", getTitleText());
        softAssert.assertAll();
    }

    @Test(priority = 1, description = "UI: Validate incorrect password login")
    public void invalidLoginWrongPasswordTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("This test checks that user is not logged using an incorrect password");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.logIn("admin", "seven");
        System.out.println("Validating that user has not been logged in");
        softAssert.assertEquals("Log In", getTitleText());
        softAssert.assertAll();
    }

    @Test(priority = 1, description = "UI: Validate empty username login")
    public void invalidLoginEmptyUsernameTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("This test checks that user is not logged using an empty username");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.logIn("", "hero");
        System.out.println("Validating that user has not been logged in");
        softAssert.assertEquals("Log In", getTitleText());
        softAssert.assertAll();
    }

    @Test(priority = 1, description = "UI: Validate empty password login")
    public void invalidLoginEmptyPasswordTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("This test checks that user is not logged using an empty password");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.logIn("admin", "");
        System.out.println("Validating that user has not been logged in");
        softAssert.assertEquals("Log In", getTitleText());
        softAssert.assertAll();
    }

    @Test(priority = 1, description = "UI: Validate empty credentials login")
    public void invalidLoginEmptyCredentialsTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("This test checks that user is not logged using no credentials");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.logIn("", "");
        System.out.println("Validating that user has not been logged in");
        softAssert.assertEquals("Log In", getTitleText());
        softAssert.assertAll();
    }

    @Test(priority = 1, description = "UI: Validate login password is case sensitive")
    public void invalidLoginCaseSensitivePasswordTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("This test checks that password is case sensitive");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.logIn("admin", "HERO");
        System.out.println("Validating that user has not been logged in");
        softAssert.assertEquals("Log In", getTitleText());
        softAssert.assertAll();
    }

    @Test(priority = 1, description = "UI: Validate no whitespaces are allowed on username")
    public void invalidLoginWhiteSpacesInUsernameTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("This test checks that whitespaces on username are not allowed");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.logIn("admin ", "hero");
        System.out.println("Validating that user has not been logged in");
        softAssert.assertEquals("Log In", getTitleText());
        softAssert.assertAll();
    }

    @Test(priority = 1, description = "UI: Validate no whitespaces are allowed on password")
    public void invalidLoginWhiteSpacesInPasswordTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("This test checks that whitespaces on password are not allowed");
        System.out.println("----------------------------------------------------------------------");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        loginPage.logIn("admin", "hero ");
        System.out.println("Validating that user has not been logged in");
        softAssert.assertEquals("Log In", getTitleText());
        softAssert.assertAll();
    }
}
