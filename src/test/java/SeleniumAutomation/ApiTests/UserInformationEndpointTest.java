package SeleniumAutomation.ApiTests;

import SeleniumAutomation.Api.UserEndpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserInformationEndpointTest {

    // This test calls the user information endpoint and checks correct values are returned
    @Test(priority = 2)
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
    @Test(priority = 2)
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
    @Test(priority = 2)
    public void getAllUsersInformationTest() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing that all users endpoint returns a list of users");
        System.out.println("----------------------------------------------------------------------");
        Assert.assertTrue(!UserEndpoint.getAllUsers("admin", "hero").isEmpty(),
                "All users endpoint returned an empty list");
    }

    // These tests call the all users endpoint with invalid credentials
    @Test(priority = 2)
    public void invalidLoginAllUserInformationTest() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing non admin user cannot retrieve all users information");
        System.out.println("----------------------------------------------------------------------");
        Assert.assertTrue(400 <= UserEndpoint.getAllUsersResponse("tester", "maniac").getStatusCode(),
                "Non admin user was able to get all users information");
    }

    @Test(priority = 2)
    public void noCredentialsGetAllInfoTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing empty credentials fail to get all users information");
        System.out.println("----------------------------------------------------------------------");
        Assert.assertTrue(400 <= UserEndpoint.getAllUsersResponse("", "").getStatusCode(),
                "Empty user credentials was able to get all users information");
    }

    @Test(priority = 2)
    public void invalidCredentialsGetAllInfoTest(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing non existing credentials fail to get all users information");
        System.out.println("----------------------------------------------------------------------");
        Assert.assertTrue(400 <= UserEndpoint.getAllUsersResponse("admin1", "hero").getStatusCode(),
                "Non existent user was able to get all users information");
    }
}
