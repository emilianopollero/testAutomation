package SeleniumAutomation.ApiTests;

import SeleniumAutomation.Api.UserEndpoint;
import SeleniumAutomation.Enums.ECredentials;
import SeleniumAutomation.Utils.ConfigFileReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginEndpointTest {

    private ConfigFileReader reader = new ConfigFileReader();

    // This test calls the login api with the different users, checks for a 200 response code and validates all values
    @Test(priority = 2, description = "API: Validate user can successfully login")
    public void successfulLoginTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing login response for admin user");
        System.out.println("----------------------------------------------------------------------");
        Response response = UserEndpoint.login(reader.getCredentials(ECredentials.ADMIN_USER),
                reader.getCredentials(ECredentials.ADMIN_USER_PASS));
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
    }

    @Test(priority = 2, description = "API: Validate newly created non admin user can login")
    public void successfulLoginNewNonAdminUser() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing login response for new non admin user");
        System.out.println("----------------------------------------------------------------------");
        UserEndpoint newUser = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        newUser.setPassword("123456");
        UserEndpoint.createUser(newUser);
        Response response = UserEndpoint.login(newUser.getUsername(), newUser.getPassword());
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

    @Test(priority = 2, description = "API: Validate successful login with uppercase username")
    public void successfulLoginUppercaseUsername(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing login response for admin user with uppercase username");
        System.out.println("----------------------------------------------------------------------");
        Response response = UserEndpoint.login(reader.getCredentials(ECredentials.ADMIN_USER).toUpperCase(),
                reader.getCredentials(ECredentials.ADMIN_USER_PASS));
        Assert.assertEquals(200, response.getStatusCode());
        UserEndpoint adminLoginResponse = new UserEndpoint(response.jsonPath().get());
        Assert.assertEquals(adminLoginResponse.getId(), 1);
        Assert.assertEquals(adminLoginResponse.getName(), "Amazing Admin");
        Assert.assertEquals(adminLoginResponse.getUsername(), "admin");
        Assert.assertEquals(adminLoginResponse.getEmail(), "a.admin@wearewaes.com");
        Assert.assertEquals(adminLoginResponse.getSuperpower(), "Change the course of a waterfall.");
        Assert.assertEquals(adminLoginResponse.getDateOfBirth(), "1984-09-18");
        Assert.assertTrue(adminLoginResponse.getisAdmin());
        response.prettyPrint();
    }

    // Call login endpoint with invalid credentials
    @Test(priority = 2, description = "API: Validate system response for invalid credentials")
    public void invalidLoginTest() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing invalid credentials");
        System.out.println("----------------------------------------------------------------------");
        Response response = UserEndpoint.login(reader.getCredentials(ECredentials.ADMIN_USER), "123456");
        Assert.assertEquals(401, response.getStatusCode());
        Assert.assertEquals("Bad credentials", response.jsonPath().getString("message"));
        System.out.println("Response is: ");
        response.prettyPrint();
    }

    @Test(priority = 2, description = "API:  Validate system response for empty username")
    public void negativeLoginEmptyUsernameTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing empty username");
        System.out.println("----------------------------------------------------------------------");
        Response response = UserEndpoint.login("", reader.getCredentials(ECredentials.ADMIN_USER_PASS));
        Assert.assertEquals(401, response.getStatusCode());
        Assert.assertEquals("Bad credentials", response.jsonPath().getString("message"));
        System.out.println("Response is: ");
        response.prettyPrint();
    }

    @Test(priority = 2, description = "API:  Validate system response for empty password")
    public void negativeLoginEmptyPasswordTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing empty password");
        System.out.println("----------------------------------------------------------------------");
        Response response = UserEndpoint.login(reader.getCredentials(ECredentials.ADMIN_USER), "");
        Assert.assertEquals(401, response.getStatusCode());
        Assert.assertEquals("Bad credentials", response.jsonPath().getString("message"));
        System.out.println("Response is: ");
        response.prettyPrint();
    }

    @Test(priority = 2, description = "API:  Validate system response for empty credentials")
    public void negativeLoginEmptyCredentialsTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing empty credentials");
        System.out.println("----------------------------------------------------------------------");
        Response response = UserEndpoint.login("", "");
        Assert.assertEquals(401, response.getStatusCode());
        Assert.assertEquals("Bad credentials", response.jsonPath().getString("message"));
        System.out.println("Response is: ");
        response.prettyPrint();
    }

}
