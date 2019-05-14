package SeleniumAutomation.ApiTests;

import SeleniumAutomation.Api.UserEndpoint;
import SeleniumAutomation.Enums.ECredentials;
import SeleniumAutomation.Utils.ConfigFileReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class UserInformationEndpointTest {

    ConfigFileReader reader = new ConfigFileReader();

    // This test calls the user information endpoint and checks correct values are returned
    @Test(priority = 2, description = "API: Validate user details response")
    public void getUserInformationTest() {
        System.out.println("Testing user details response for admin user");
        Response response = UserEndpoint.userDetails("admin");
        Assert.assertEquals(200, response.getStatusCode());
        UserEndpoint adminUserEndpoint = new UserEndpoint(response.jsonPath().get());
        Assert.assertEquals(adminUserEndpoint.getId(), 1);
        Assert.assertEquals(adminUserEndpoint.getName(), "Amazing Admin");
        Assert.assertEquals(adminUserEndpoint.getUsername(), "admin");
        Assert.assertEquals(adminUserEndpoint.getEmail(), "a.admin@wearewaes.com");
        Assert.assertEquals(adminUserEndpoint.getSuperpower(), "Change the course of a waterfall.");
        Assert.assertEquals(adminUserEndpoint.getDateOfBirth(), "1984-09-18");
        Assert.assertTrue(adminUserEndpoint.getisAdmin());
        System.out.println("User information response for admin user verified");
        response.prettyPrint();
    }

    // This test calls the user api with invalid values
    @Test(priority = 2, description = "API: Validate user details response for non existent user")
    public void userInformationInvalidUserEndpointTest() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing user details response for non existent user");
        System.out.println("----------------------------------------------------------------------");
        Response response = UserEndpoint.userDetails("notAUser");
        Assert.assertEquals(404, response.getStatusCode());
        Assert.assertEquals("Username notAUser does not exist.", response.jsonPath().getString("message"));
        System.out.println("Response is: ");
        response.prettyPrint();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing user details response for empty user");
        System.out.println("----------------------------------------------------------------------");
        response = UserEndpoint.userDetails("");
        Assert.assertEquals(404, response.getStatusCode());
        Assert.assertEquals("Username  does not exist.", response.jsonPath().getString("message"));
        System.out.println("Response is: ");
        response.prettyPrint();
    }

    // This test calls the all users endpoint with valid credentials and checks that it retrieves non empty list of users
    @Test(priority = 2, description = "API: Validate all users response")
    public void getAllUsersInformationTest() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing that all users endpoint returns a list of users");
        System.out.println("----------------------------------------------------------------------");
        ArrayList<UserEndpoint> allUsers = UserEndpoint.getAllUsers(reader.getCredentials(ECredentials.ADMIN_USER),
                reader.getCredentials(ECredentials.ADMIN_USER_PASS));
        Assert.assertTrue(!allUsers.isEmpty(),"All users endpoint returned an empty list");
        Assert.assertEquals(allUsers.get(0).getId(), 1);
        Assert.assertEquals(allUsers.get(0).getName(), "Amazing Admin");
        Assert.assertEquals(allUsers.get(0).getUsername(), "admin");
        Assert.assertEquals(allUsers.get(0).getEmail(), "a.admin@wearewaes.com");
        Assert.assertEquals(allUsers.get(0).getSuperpower(), "Change the course of a waterfall.");
        Assert.assertEquals(allUsers.get(0).getDateOfBirth(), "1984-09-18");
        Assert.assertTrue(allUsers.get(0).getisAdmin());
        System.out.println("User information response for admin user verified");
    }

    // These tests call the all users endpoint with invalid credentials
    @Test(priority = 2, description = "API: Validate non admin user cannot retrieve all users information")
    public void invalidLoginAllUserInformationTest() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing non admin user cannot retrieve all users information");
        System.out.println("----------------------------------------------------------------------");
        UserEndpoint nonAdminUser = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        nonAdminUser.setPassword("123456");
        UserEndpoint.createUser(nonAdminUser);
        Assert.assertTrue(400 <= UserEndpoint.getAllUsersResponse(nonAdminUser.getUsername(),
                nonAdminUser.getPassword()).getStatusCode(),
                "Non admin user was able to get all users information");
    }

    @Test(priority = 2, description = "API: Validate empty credentials fail to get all users information")
    public void noCredentialsGetAllInfoTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing empty credentials fail to get all users information");
        System.out.println("----------------------------------------------------------------------");
        Assert.assertTrue(400 <= UserEndpoint.getAllUsersResponse("", "").getStatusCode(),
                "Empty user credentials was able to get all users information");
    }

    @Test(priority = 2, description = "API: Validate non existing credentials fail to get all users information")
    public void invalidCredentialsGetAllInfoTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing non existing credentials fail to get all users information");
        System.out.println("----------------------------------------------------------------------");
        Assert.assertTrue(400 <= UserEndpoint.getAllUsersResponse("noUsername", "noPassword").getStatusCode(),
                "Non existent user was able to get all users information");
    }
}
