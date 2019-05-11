package SeleniumAutomation.ApiTests;

import SeleniumAutomation.Api.UserEndpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserInformationEndpointTest {

    @Test(priority = 1)
    public void getUserInformationTest() {
        // This test calls the login api with the different users, checks for a 200 response code and validates all values
        System.out.println("Testing user details response for dev user");
        Response response = UserEndpoint.userDetails("dev");
        Assert.assertEquals(200, response.getStatusCode());
        UserEndpoint devUserEndpoint = new UserEndpoint(response.jsonPath().get());
        Assert.assertEquals(devUserEndpoint.getId(), 2);
        Assert.assertEquals(devUserEndpoint.getName(), "Zuper Dooper Dev");
        Assert.assertEquals(devUserEndpoint.getUsername(), "dev");
        Assert.assertEquals(devUserEndpoint.getEmail(), "zd.dev@wearewaes.com");
        Assert.assertEquals(devUserEndpoint.getSuperpower(), "Debug a repellent factory storage.");
        Assert.assertEquals(devUserEndpoint.getDateOfBirth(), "1999-10-10");
        Assert.assertFalse(devUserEndpoint.getisAdmin());
        System.out.println("User information response for dev user verified");
        response.prettyPrint();

        System.out.println("Testing user details response for admin user");
        response = UserEndpoint.userDetails("admin");
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

        System.out.println("Testing user details response for tester user");
        response = UserEndpoint.userDetails("tester");
        Assert.assertEquals(200, response.getStatusCode());
        UserEndpoint testerUserEndpoint = new UserEndpoint(response.jsonPath().get());
        Assert.assertEquals(testerUserEndpoint.getId(), 3);
        Assert.assertEquals(testerUserEndpoint.getName(), "Al Skept-Cal Tester");
        Assert.assertEquals(testerUserEndpoint.getUsername(), "tester");
        Assert.assertEquals(testerUserEndpoint.getEmail(), "as.tester@wearewaes.com");
        Assert.assertEquals(testerUserEndpoint.getSuperpower(), "Voltage AND Current.");
        Assert.assertEquals(testerUserEndpoint.getDateOfBirth(), "2014-07-15");
        Assert.assertFalse(testerUserEndpoint.getisAdmin());
        System.out.println("User information response for tester user verified");
        response.prettyPrint();
    }

    @Test(priority = 1)
    public void userInformationInvalidUserEndpointTest() {
        // This test calls the user api with invalid values
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing user details response for non existent user");
        System.out.println("----------------------------------------------------------------------");
        Response response = UserEndpoint.userDetails("notAUser");
        Assert.assertEquals(404, response.getStatusCode());
        Assert.assertEquals("Username notAUser does not exist.",response.jsonPath().getString("message"));
        System.out.println("Response is: ");
        response.prettyPrint();
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing user details response for empty user");
        System.out.println("----------------------------------------------------------------------");
        response = UserEndpoint.userDetails("");
        Assert.assertEquals(404, response.getStatusCode());
        Assert.assertEquals("Username  does not exist.",response.jsonPath().getString("message"));
        System.out.println("Response is: ");
        response.prettyPrint();
    }

    @Test(priority = 1)
    public void getAllUsersInformationTest(){
        // This test calls the all users endpoint with valid credentials and checks that it retrieves a list of users
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing that all users endpoint returns a list of users");
        System.out.println("----------------------------------------------------------------------");
        Assert.assertTrue(!UserEndpoint.getAllUsers("admin", "hero").isEmpty(),
                "All users endpoint returned an empty list");
    }

    @Test(priority = 1)
    public void invalidLoginAllUserInformationTest(){
        // This test calls the all users endpoint with invalid credentials
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing non admin user cannot retrieve all users information");
        System.out.println("----------------------------------------------------------------------");
        Assert.assertTrue(400 <= UserEndpoint.getAllUsersResponse("tester", "maniac").getStatusCode(),
                "Non admin user was able to get all users information");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing non existing credentials fail to get all users information");
        System.out.println("----------------------------------------------------------------------");
        Assert.assertTrue(400 <= UserEndpoint.getAllUsersResponse("tester1", "maniac").getStatusCode(),
                "Non admin user was able to get all users information");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing empty credentials fail to get all users information");
        System.out.println("----------------------------------------------------------------------");
        Assert.assertTrue(400 <= UserEndpoint.getAllUsersResponse("", "").getStatusCode(),
                "Non admin user was able to get all users information");
    }
}
