package SeleniumAutomation;

import SeleniumAutomation.Enums.ECredentials;
import SeleniumAutomation.Pages.HomePage;
import SeleniumAutomation.Pages.LoginPage;
import SeleniumAutomation.Pages.ProfilePage;
import SeleniumAutomation.Utils.Driver;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseTest {

    private SoftAssert softAssert = new SoftAssert();

    @Test(priority = 1, description = "Login admin credentials test", invocationCount = 3)
    @Description("This test verifies that admin users can login using valid credentials and that the proper profile page is shown to them")
    public void validAdminLoginTest() {
        HomePage homePage = new HomePage();
        Driver.getInstance().get("https://waesworks.bitbucket.io/");
        LoginPage loginPage = homePage.clickLogin();
        ProfilePage profilePage = loginPage.logIn(ECredentials.ADMIN_USER.getValue(), ECredentials.ADMIN_USER_PASS.getValue());
        System.out.println("Validating profile page for admin user");
        softAssert.assertEquals("How are you doing, Amazing Admin?", profilePage.getFirstProfileText());
        softAssert.assertEquals("Your super power: Change the course of a waterfall.", profilePage.getSecondProfileText());
        Assert.assertTrue(profilePage.checkAdminTableIsDisplayed());
        System.out.println("Admin user correctly logged in");
        softAssert.assertAll();
    }

    @Test(priority = 1, description = "Login dev credentials test", invocationCount = 3)
    @Description("This test verifies that dev users can login using valid credentials and that the proper profile page is shown to them")
    public void validDevLoginTest() {
        HomePage homePage = new HomePage();
        Driver.getInstance().get("https://waesworks.bitbucket.io/");
        LoginPage loginPage = homePage.clickLogin();
        ProfilePage profilePage = loginPage.logIn(ECredentials.DEV_USER.getValue(), ECredentials.DEV_USER_PASS.getValue());
        System.out.println("Validating profile page for dev user");
        softAssert.assertEquals("How are you doing, Zuper Dooper Dev?", profilePage.getFirstProfileText());
        softAssert.assertEquals("Your super power: Debug a repellent factory storage.", profilePage.getSecondProfileText());
        System.out.println("Dev user correctly logged in");
        softAssert.assertAll();
    }

    @Test(priority = 1, description = "Login tester credentials test", invocationCount = 3)
    @Description("This test verifies that tester users can login using valid credentials and that the proper profile page is shown to them")
    public void validTesterLoginTest() {
        HomePage homePage = new HomePage();
        Driver.getInstance().get("https://waesworks.bitbucket.io/");
        LoginPage loginPage = homePage.clickLogin();
        ProfilePage profilePage = loginPage.logIn(ECredentials.TESTER_USER.getValue(), ECredentials.TESTER_USER_PASS.getValue());
        System.out.println("Validating profile page for tester user");
        softAssert.assertEquals("How are you doing, Al Skept-Cal Tester?", profilePage.getFirstProfileText());
        softAssert.assertEquals("Your super power: Voltage AND Current.", profilePage.getSecondProfileText());
        System.out.println("Tester user correctly logged in");
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Invalid credentials tests", invocationCount = 3)
    @Description("This test verifies that the system handles invalid credentials properly")
    public void invalidLoginTest() {
        HomePage homePage = new HomePage();
        Driver.getInstance().get("https://waesworks.bitbucket.io/");
        LoginPage loginPage = homePage.clickLogin();
        String[] cases = {"wrongUsername", "wrongPassword", "emptyUsername", "emptyPassword", "empty"};
        for (String value : cases) {
            switch (value) {
                case "wrongUsername":
                    loginPage.logIn("seven", "hero");
                    System.out.println("Validating that user has not been logged in");
                    softAssert.assertEquals(loginPage.getTitleText(), "Log In");
                    softAssert.assertAll();
                    break;
                case "wrongPassword":
                    loginPage.logIn("admin", "seven");
                    System.out.println("Validating that user has not been logged in");
                    softAssert.assertEquals(loginPage.getTitleText(), "Log In");
                    softAssert.assertAll();
                    break;
                case "emptyUsername":
                    loginPage.logIn("", "hero");
                    System.out.println("Validating that user has not been logged in");
                    softAssert.assertEquals(loginPage.getTitleText(), "Log In");
                    softAssert.assertAll();
                    break;
                case "emptyPassword":
                    loginPage.logIn("admin", "");
                    System.out.println("Validating that user has not been logged in");
                    softAssert.assertEquals(loginPage.getTitleText(), "Log In");
                    softAssert.assertAll();
                    break;
                case "empty":
                    loginPage.logIn("", "");
                    System.out.println("Validating that user has not been logged in");
                    softAssert.assertEquals(loginPage.getTitleText(), "Log In");
                    softAssert.assertAll();
                    break;
            }
        }
    }
}
