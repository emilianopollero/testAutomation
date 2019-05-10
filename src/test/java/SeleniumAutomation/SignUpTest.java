package SeleniumAutomation;

import SeleniumAutomation.Pages.HomePage;
import SeleniumAutomation.Pages.NewUserPage;
import SeleniumAutomation.Pages.SignUpPage;
import SeleniumAutomation.Utils.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Month;

import static SeleniumAutomation.BasePage.getTitleText;

public class SignUpTest extends BaseTest {

    @Test(priority = 1)
    public void validateUserCanSignUp() {
        System.out.println("This test verifies users can signUp and the proper profile page is shown to them");
        Driver.getInstance().get("https://waesworks.bitbucket.io/");
        HomePage homePage = new HomePage();
        SignUpPage signUpPage = homePage.clickSignUp();
        NewUserPage newUserPage = signUpPage.signUp("emiliano.pollero", "123456", "Emiliano",
                "test@test.com", "19", Month.JUNE, "1982");
        System.out.println("Validating profile page for new user");
        Assert.assertEquals(newUserPage.getFirstProfileText(), "Welcome to your new profile page, " + "Emiliano!");
        System.out.println("Profile page for new user is correct");
    }

    @Test(priority = 1)
    public void validateRequiredSignUpFields() {
        System.out.println("This test validates required signUp fields");
        Driver.getInstance().get("https://waesworks.bitbucket.io/");
        HomePage homePage = new HomePage();
        SignUpPage signUpPage = homePage.clickSignUp();
        String[] cases = {"noUsername", "noPassword", "noName", "noEmail", "noDay",
                "noMonth", "noYear"};
        for (String value : cases) {
            switch (value) {
                case "noUsername":
                    signUpPage.signUp("", "123456", "Emiliano",
                            "test@test.com", "19", Month.JUNE, "1982");
                    System.out.println("Validating user has not been signed");
                    Assert.assertEquals(getTitleText(), "Sign Up");
                    System.out.println("User with no username was not registered");
                    break;
                case "noPassword":
                    signUpPage.signUp("emiliano.pollero", "", "Emiliano",
                            "test@test.com", "19", Month.JUNE, "1982");
                    System.out.println("Validating user has not been signed");
                    Assert.assertEquals(getTitleText(), "Sign Up");
                    System.out.println("User with no password was not registered");
                    break;
                case "noName":
                    signUpPage.signUp("emiliano,pollero", "123456", "",
                            "test@test.com", "19", Month.JUNE, "1982");
                    System.out.println("Validating user has not been signed");
                    Assert.assertEquals(getTitleText(), "Sign Up");
                    System.out.println("User with no name was not registered");
                    break;
                case "noEmail":
                    signUpPage.signUp("emiliano,pollero", "123456", "Emiliano",
                            "", "19", Month.JUNE, "1982");
                    System.out.println("Validating user has not been signed");
                    Assert.assertEquals(getTitleText(), "Sign Up");
                    System.out.println("User with no email was not registered");
                    break;
                case "noDay":
                    signUpPage.signUp("emiliano,pollero", "123456", "Emiliano",
                            "test@test.com", "", Month.JUNE, "1982");
                    System.out.println("Validating user has not been signed");
                    Assert.assertEquals(getTitleText(), "Sign Up");
                    System.out.println("User with no email was not registered");
                    break;
                case "noMonth":
                    signUpPage.signUp("emiliano,pollero", "123456", "Emiliano",
                            "test@test.com", "19", "", "1982");
                    System.out.println("Validating user has not been signed");
                    Assert.assertEquals(getTitleText(), "Sign Up");
                    System.out.println("User with no email was not registered");
                    break;
                case "noYear":
                    signUpPage.signUp("emiliano,pollero", "123456", "Emiliano",
                            "test@test.com", "19", Month.JUNE, "");
                    System.out.println("Validating user has not been signed");
                    Assert.assertEquals(getTitleText(), "Sign Up");
                    System.out.println("User with no email was not registered");
                    break;
            }
        }
    }
}
