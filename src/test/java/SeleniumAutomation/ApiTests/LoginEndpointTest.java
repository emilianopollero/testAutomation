package SeleniumAutomation.ApiTests;

import SeleniumAutomation.Api.UserEndpoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginEndpointTest {

    // This test calls the login api with the different users, checks for a 200 response code and validates all values
    @Test(priority = 2)
    public void successfulLoginTest() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing login response for admin user");
        System.out.println("----------------------------------------------------------------------");
        Response response = UserEndpoint.login("admin", "hero");
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
        System.out.println("Testing login response for admin user with uppercase username");
        System.out.println("----------------------------------------------------------------------");
        response = UserEndpoint.login("ADMIN", "hero");
        Assert.assertEquals(200, response.getStatusCode());
        adminLoginResponse = new UserEndpoint(response.jsonPath().get());
        Assert.assertEquals(adminLoginResponse.getId(), 1);
        Assert.assertEquals(adminLoginResponse.getName(), "Amazing Admin");
        Assert.assertEquals(adminLoginResponse.getUsername(), "admin");
        Assert.assertEquals(adminLoginResponse.getEmail(), "a.admin@wearewaes.com");
        Assert.assertEquals(adminLoginResponse.getSuperpower(), "Change the course of a waterfall.");
        Assert.assertEquals(adminLoginResponse.getDateOfBirth(), "1984-09-18");
        Assert.assertTrue(adminLoginResponse.getisAdmin());
        response.prettyPrint();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing login response for new non admin user");
        System.out.println("----------------------------------------------------------------------");
        UserEndpoint newUser = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        newUser.setPassword("123456");
        UserEndpoint.createUser(newUser);
        response = UserEndpoint.login(newUser.getUsername(), newUser.getPassword());
        Assert.assertEquals(200, response.getStatusCode());
        UserEndpoint newUserLoginResponse = new UserEndpoint(response.jsonPath().get());
        Assert.assertEquals(newUserLoginResponse.getId(), newUser.getId());
        Assert.assertEquals(newUserLoginResponse.getName(), newUser.getName());
        Assert.assertEquals(newUserLoginResponse.getUsername(), newUser.getUsername());
        Assert.assertEquals(newUserLoginResponse.getEmail(), newUser.getEmail());
        Assert.assertEquals(newUserLoginResponse.getSuperpower(), newUser.getSuperpower());
        Assert.assertEquals(newUserLoginResponse.getDateOfBirth(), newUser.getDateOfBirth());
        Assert.assertFalse(newUserLoginResponse.getisAdmin());
        response.prettyPrint();
    }

    // Call login endpoint with invalid credentials
    @Test(priority = 2)
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
