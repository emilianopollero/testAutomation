package SeleniumAutomation.UITests;

import SeleniumAutomation.BaseTest;
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
        System.out.println("----------------------------------------------------------------------");
        System.out.println("This test verifies users can signUp and the proper profile page is shown to them");
        System.out.println("----------------------------------------------------------------------");
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
        System.out.println("----------------------------------------------------------------------");
        System.out.println("This test validates required signUp fields");
        System.out.println("----------------------------------------------------------------------");
        Driver.getInstance().get("https://waesworks.bitbucket.io/");
        HomePage homePage = new HomePage();
        SignUpPage signUpPage = homePage.clickSignUp();
        String[] cases = {"noUsername", "noPassword", "noName", "noEmail", "noDay",
                "noMonth", "noYear", "incorrectFormatEmail", "nothingBefore@", "invalidCharactersBefore@" +
                "invalidCharactersAfter@"};
        for (String value : cases) {
            switch (value) {
                // This case verifies that username is mandatory
                case "noUsername":
                    signUpPage.signUp("", "123456", "Emiliano",
                            "test@test.com", "19", Month.JUNE, "1982");
                    System.out.println("Validating user has not been signed");
                    Assert.assertEquals(getTitleText(), "Sign Up");
                    System.out.println("User with no username was not registered");
                    break;
                case "noPassword":
                    // This case verifies that password is mandatory
                    signUpPage.signUp("emiliano.pollero", "", "Emiliano",
                            "test@test.com", "19", Month.JUNE, "1982");
                    System.out.println("Validating user has not been signed");
                    Assert.assertEquals(getTitleText(), "Sign Up");
                    System.out.println("User with no password was not registered");
                    break;
                case "noName":
                    // This case verifies that name is mandatory
                    signUpPage.signUp("emiliano,pollero", "123456", "",
                            "test@test.com", "19", Month.JUNE, "1982");
                    System.out.println("Validating user has not been signed");
                    Assert.assertEquals(getTitleText(), "Sign Up");
                    System.out.println("User with no name was not registered");
                    break;
                case "noEmail":
                    // This case verifies that email is mandatory
                    signUpPage.signUp("emiliano,pollero", "123456", "Emiliano",
                            "", "19", Month.JUNE, "1982");
                    System.out.println("Validating user has not been signed");
                    Assert.assertEquals(getTitleText(), "Sign Up");
                    System.out.println("User with no email was not registered");
                    break;
                case "incorrectFormatEmail":
                    // Incorrect format email test
                    signUpPage.signUp("emiliano,pollero", "123456", "Emiliano",
                            "email", "19", Month.JUNE, "1982");
                    System.out.println("Validating user has not been signed");
                    Assert.assertEquals(getTitleText(), "Sign Up");
                    System.out.println("User with incorrect email was not registered");
                    break;
                case "nothingBefore@":
                    // Incorrect format email test
                    signUpPage.signUp("emiliano,pollero", "123456", "Emiliano",
                            "@email.com", "19", Month.JUNE, "1982");
                    System.out.println("Validating user has not been signed");
                    Assert.assertEquals(getTitleText(), "Sign Up");
                    System.out.println("User with incorrect email was not registered");
                    break;
                case "nothingAfter@":
                    // Incorrect format email test
                    signUpPage.signUp("emiliano,pollero", "123456", "Emiliano",
                            "email@", "19", Month.JUNE, "1982");
                    System.out.println("Validating user has not been signed");
                    Assert.assertEquals(getTitleText(), "Sign Up");
                    System.out.println("User with incorrect email was not registered");
                    break;
                case "invalidCharactersBefore@":
                    // Incorrect format email test
                    signUpPage.signUp("emiliano,pollero", "123456", "Emiliano",
                            "’àáâãäåæçèéêë@mail.com", "19", Month.JUNE, "1982");
                    System.out.println("Validating user has not been signed");
                    Assert.assertEquals(getTitleText(), "Sign Up");
                    System.out.println("User with incorrect email was not registered");
                    break;
                case "invalidCharactersAfter@":
                    // Incorrect format email test
                    signUpPage.signUp("emiliano,pollero", "123456", "Emiliano",
                            "پ محسوس کر سک@email", "19", Month.JUNE, "1982");
                    System.out.println("Validating user has not been signed");
                    Assert.assertEquals(getTitleText(), "Sign Up");
                    System.out.println("User with incorrect email was not registered");
                    break;
                case "noDay":
                    // This case verifies that day is mandatory
                    signUpPage.signUp("emiliano,pollero", "123456", "Emiliano",
                            "test@test.com", "", Month.JUNE, "1982");
                    System.out.println("Validating user has not been signed");
                    Assert.assertEquals(getTitleText(), "Sign Up");
                    System.out.println("User with no email was not registered");
                    break;
                case "noMonth":
                    // This case verifies that month is mandatory
                    signUpPage.signUp("emiliano,pollero", "123456", "Emiliano",
                            "test@test.com", "19", "", "1982");
                    System.out.println("Validating user has not been signed");
                    Assert.assertEquals(getTitleText(), "Sign Up");
                    System.out.println("User with no email was not registered");
                    break;
                case "noYear":
                    // This case verifies that year is mandatory
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
