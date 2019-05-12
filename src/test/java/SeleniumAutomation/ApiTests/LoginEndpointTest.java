package SeleniumAutomation.ApiTests;

import SeleniumAutomation.Api.UserEndpoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginEndpointTest {

    // This test calls the login api with the different users, checks for a 200 response code and validates all values
    @Test(priority = 1)
    public void successfulLoginTest() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing login response for dev user");
        System.out.println("----------------------------------------------------------------------");
        //Setting dev password back to wizard as it is changed in UpdateUserEndpointTest
        UserEndpoint wizardUser = (UserEndpoint) UserEndpoint.getAllUsers("admin", "hero").get(1);
        wizardUser.setPassword("wizard");
        UserEndpoint.updateUser("admin", "hero", wizardUser);
        Response response = UserEndpoint.login("dev", "wizard");
        Assert.assertEquals(200, response.getStatusCode());
        UserEndpoint devLoginResponse = new UserEndpoint(response.jsonPath().get());
        Assert.assertEquals(devLoginResponse.getId(), 2);
        Assert.assertEquals(devLoginResponse.getName(), "Zuper Dooper Dev");
        Assert.assertEquals(devLoginResponse.getUsername(), "dev");
        Assert.assertEquals(devLoginResponse.getEmail(), "zd.dev@wearewaes.com");
        Assert.assertEquals(devLoginResponse.getSuperpower(), "Debug a repellent factory storage.");
        Assert.assertEquals(devLoginResponse.getDateOfBirth(), "1999-10-10");
        Assert.assertFalse(devLoginResponse.getisAdmin());
        System.out.println("Login response for dev user verified");
        response.prettyPrint();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing login response for admin user");
        System.out.println("----------------------------------------------------------------------");
        response = UserEndpoint.login("admin", "hero");
        Assert.assertEquals(200, response.getStatusCode());
        UserEndpoint adminLoginResponse = new UserEndpoint(response.jsonPath().get());
        Assert.assertEquals(adminLoginResponse.getId(), 1);
        Assert.assertEquals(adminLoginResponse.getName(), "Amazing Admin");
        Assert.assertEquals(adminLoginResponse.getUsername(), "admin");
        Assert.assertEquals(adminLoginResponse.getEmail(), "a.admin@wearewaes.com");
        Assert.assertEquals(adminLoginResponse.getSuperpower(), "Change the course of a waterfall.");
        Assert.assertEquals(adminLoginResponse.getDateOfBirth(), "1984-09-18");
        Assert.assertTrue(adminLoginResponse.getisAdmin());
        System.out.println("Login response for admin user verified");
        response.prettyPrint();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing login response for tester user");
        System.out.println("----------------------------------------------------------------------");
        response = UserEndpoint.login("tester", "maniac");
        Assert.assertEquals(200, response.getStatusCode());
        UserEndpoint testerLoginResponse = new UserEndpoint(response.jsonPath().get());
        Assert.assertEquals(testerLoginResponse.getId(), 3);
        Assert.assertEquals(testerLoginResponse.getName(), "Al Skept-Cal Tester");
        Assert.assertEquals(testerLoginResponse.getUsername(), "tester");
        Assert.assertEquals(testerLoginResponse.getEmail(), "as.tester@wearewaes.com");
        Assert.assertEquals(testerLoginResponse.getSuperpower(), "Voltage AND Current.");
        Assert.assertEquals(testerLoginResponse.getDateOfBirth(), "2014-07-15");
        Assert.assertFalse(testerLoginResponse.getisAdmin());
        System.out.println("Login response for tester user verified");
        response.prettyPrint();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing login response for tester user with uppercase username");
        System.out.println("----------------------------------------------------------------------");
        response = UserEndpoint.login("TESTER", "maniac");
        Assert.assertEquals(200, response.getStatusCode());
        testerLoginResponse = new UserEndpoint(response.jsonPath().get());
        Assert.assertEquals(testerLoginResponse.getId(), 3);
        Assert.assertEquals(testerLoginResponse.getName(), "Al Skept-Cal Tester");
        Assert.assertEquals(testerLoginResponse.getUsername(), "tester");
        Assert.assertEquals(testerLoginResponse.getEmail(), "as.tester@wearewaes.com");
        Assert.assertEquals(testerLoginResponse.getSuperpower(), "Voltage AND Current.");
        Assert.assertEquals(testerLoginResponse.getDateOfBirth(), "2014-07-15");
        Assert.assertFalse(testerLoginResponse.getisAdmin());
        System.out.println("Login response for tester user verified");
        response.prettyPrint();
    }

    // Call login endpoint with invalid credentials
    @Test(priority = 1)
    public void invalidLoginTest() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing invalid credentials");
        System.out.println("----------------------------------------------------------------------");
        Response response = UserEndpoint.login("dev", "wizar");
        Assert.assertEquals(401, response.getStatusCode());
        Assert.assertEquals("Bad credentials", response.jsonPath().getString("message"));
        System.out.println("Response is: ");
        response.prettyPrint();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing empty username");
        System.out.println("----------------------------------------------------------------------");
        response = UserEndpoint.login("", "wizard");
        Assert.assertEquals(401, response.getStatusCode());
        Assert.assertEquals("Bad credentials", response.jsonPath().getString("message"));
        System.out.println("Response is: ");
        response.prettyPrint();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing empty password");
        System.out.println("----------------------------------------------------------------------");
        response = UserEndpoint.login("dev", "");
        Assert.assertEquals(401, response.getStatusCode());
        Assert.assertEquals("Bad credentials", response.jsonPath().getString("message"));
        System.out.println("Response is: ");
        response.prettyPrint();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing empty credentials");
        System.out.println("----------------------------------------------------------------------");
        response = UserEndpoint.login("", "");
        Assert.assertEquals(401, response.getStatusCode());
        Assert.assertEquals("Bad credentials", response.jsonPath().getString("message"));
        System.out.println("Response is: ");
        response.prettyPrint();
    }

}
