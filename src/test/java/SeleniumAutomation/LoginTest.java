package SeleniumAutomation;

import SeleniumAutomation.Enums.ECredentials;
import SeleniumAutomation.Pages.HomePage;
import SeleniumAutomation.Pages.LoginPage;
import SeleniumAutomation.Pages.ProfilePage;
import SeleniumAutomation.Utils.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static SeleniumAutomation.BasePage.getTitleText;

public class LoginTest extends BaseTest {

    private SoftAssert softAssert = new SoftAssert();

    @Test(priority = 1)
    public void validAdminLoginTest() {
        System.out.println("This test verifies that admin users can login using valid credentials and that the proper " +
                "profile page is shown to them");
        Driver.getInstance().get("https://waesworks.bitbucket.io/");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        ProfilePage profilePage = loginPage.logIn(ECredentials.ADMIN_USER.getValue(), ECredentials.ADMIN_USER_PASS.getValue());
        System.out.println("Validating profile page for admin user");
        softAssert.assertEquals("Your Profile", getTitleText());
        softAssert.assertEquals("How are you doing, Amazing Admin?", profilePage.getFirstProfileText());
        softAssert.assertEquals("Your super power: Change the course of a waterfall.", profilePage.getSecondProfileText());
        Assert.assertTrue(profilePage.checkAdminTableIsDisplayed());
        System.out.println("Admin user correctly logged in");
        softAssert.assertAll();
    }

    @Test(priority = 1)
    public void validDevLoginTest() {
        System.out.println("This test verifies that dev users can login using valid credentials and that the proper " +
                "profile page is shown to them");
        Driver.getInstance().get("https://waesworks.bitbucket.io/");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        ProfilePage profilePage = loginPage.logIn(ECredentials.DEV_USER.getValue(), ECredentials.DEV_USER_PASS.getValue());
        System.out.println("Validating profile page for dev user");
        softAssert.assertEquals("How are you doing, Zuper Dooper Dev?", profilePage.getFirstProfileText());
        softAssert.assertEquals("Your super power: Debug a repellent factory storage.", profilePage.getSecondProfileText());
        System.out.println("Dev user correctly logged in");
        softAssert.assertAll();
    }

    @Test(priority = 1)
    public void validTesterLoginTest() {
        System.out.println("This test verifies that tester users can login using valid credentials and that the proper " +
                "profile page is shown to them");
        Driver.getInstance().get("https://waesworks.bitbucket.io/");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        ProfilePage profilePage = loginPage.logIn(ECredentials.TESTER_USER.getValue(), ECredentials.TESTER_USER_PASS.getValue());
        System.out.println("Validating profile page for tester user");
        softAssert.assertEquals("How are you doing, Al Skept-Cal Tester?", profilePage.getFirstProfileText());
        softAssert.assertEquals("Your super power: Voltage AND Current.", profilePage.getSecondProfileText());
        System.out.println("Tester user correctly logged in");
        softAssert.assertAll();
    }

//    @Test(priority = 1)
//    public void validUsernameInUppercaseLogin() {
//        System.out.println("This test verifies that tester users can login using valid credentials and that the proper" +
//                " profile page is shown to them");
//        Driver.getInstance().get("https://waesworks.bitbucket.io/");
//        HomePage homePage = new HomePage();
//        LoginPage loginPage = homePage.clickLogin();
//        ProfilePage profilePage = loginPage.logIn(ECredentials.TESTER_USER.getValue().toUpperCase(), ECredentials.TESTER_USER_PASS.getValue());
//        System.out.println("Validating profile page for tester user");
//        softAssert.assertEquals("How are you doing, Al Skept-Cal Tester?", profilePage.getFirstProfileText());
//        softAssert.assertEquals("Your super power: Voltage AND Current.", profilePage.getSecondProfileText());
//        System.out.println("Tester user correctly logged in");
//        softAssert.assertAll();
//    }

    @Test(priority = 2)
    public void invalidLoginTest() {
        System.out.println("This test verifies that the system handles invalid credentials properly");
        Driver.getInstance().get("https://waesworks.bitbucket.io/");
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.clickLogin();
        String[] cases = {"wrongUsername", "wrongPassword", "emptyUsername", "emptyPassword", "empty",
                "caseSensitiveUsername", "caseSensitivePassword"};
        for (String value : cases) {
            switch (value) {
                case "wrongUsername":
                    loginPage.logIn("seven", "hero");
                    System.out.println("Validating that user has not been logged in");
                    softAssert.assertEquals("Log In", getTitleText());
                    softAssert.assertAll();
                    break;
                case "wrongPassword":
                    loginPage.logIn("admin", "seven");
                    System.out.println("Validating that user has not been logged in");
                    softAssert.assertEquals("Log In", getTitleText());
                    softAssert.assertAll();
                    break;
                case "emptyUsername":
                    loginPage.logIn("", "hero");
                    System.out.println("Validating that user has not been logged in");
                    softAssert.assertEquals("Log In", getTitleText());
                    softAssert.assertAll();
                    break;
                case "emptyPassword":
                    loginPage.logIn("admin", "");
                    System.out.println("Validating that user has not been logged in");
                    softAssert.assertEquals("Log In", getTitleText());
                    softAssert.assertAll();
                    break;
                case "empty":
                    loginPage.logIn("", "");
                    System.out.println("Validating that user has not been logged in");
                    softAssert.assertEquals("Log In", getTitleText());
                    softAssert.assertAll();
                    break;
                case "caseSensitivePassword":
                    loginPage.logIn("admin", "HERO");
                    System.out.println("Validating that user has not been logged in");
                    softAssert.assertEquals("Log In", getTitleText());
                    softAssert.assertAll();
                    break;
                case "caseSensitiveUsername":
                    loginPage.logIn("ADMIN", "hero");
                    System.out.println("Validating that user has not been logged in");
                    softAssert.assertEquals("Log In", getTitleText());
                    softAssert.assertAll();
                    break;
            }
        }
    }
}
