package SeleniumAutomation;

import SeleniumAutomation.Api.UserDetailsResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTest {


    @Test(priority = 1)
    public void loginEndpointTest() {
        // This test calls the login api with the different users, checks for a 200 response code and validates all values
        System.out.println("Testing login response for dev user");
        Response response = UserDetailsResponse.login("dev", "wizard");
        Assert.assertEquals(200, response.getStatusCode());
        UserDetailsResponse devLoginResponse = new UserDetailsResponse(response.jsonPath());
        Assert.assertEquals(devLoginResponse.getId(),2);
        Assert.assertEquals(devLoginResponse.getName(),"Zuper Dooper Dev");
        Assert.assertEquals(devLoginResponse.getUsername(),"dev");
        Assert.assertEquals(devLoginResponse.getEmail(),"zd.dev@wearewaes.com");
        Assert.assertEquals(devLoginResponse.getSuperpower(),"Debug a repellent factory storage.");
        Assert.assertEquals(devLoginResponse.getDateOfBirth(),"1999-10-10");
        Assert.assertFalse(devLoginResponse.isAdmin());
        System.out.println("Login response for dev user verified");
        response.prettyPrint();

        System.out.println("Testing login response for admin user");
        response = UserDetailsResponse.login("admin", "hero");
        Assert.assertEquals(200, response.getStatusCode());
        UserDetailsResponse adminLoginResponse = new UserDetailsResponse(response.jsonPath());
        Assert.assertEquals(adminLoginResponse.getId(),1);
        Assert.assertEquals(adminLoginResponse.getName(),"Amazing Admin");
        Assert.assertEquals(adminLoginResponse.getUsername(),"admin");
        Assert.assertEquals(adminLoginResponse.getEmail(),"a.admin@wearewaes.com");
        Assert.assertEquals(adminLoginResponse.getSuperpower(),"Change the course of a waterfall.");
        Assert.assertEquals(adminLoginResponse.getDateOfBirth(),"1984-09-18");
        Assert.assertTrue(adminLoginResponse.isAdmin());
        System.out.println("Login response for admin user verified");
        response.prettyPrint();

        System.out.println("Testing login response for tester user");
        response = UserDetailsResponse.login("tester", "maniac");
        Assert.assertEquals(200, response.getStatusCode());
        UserDetailsResponse testerLoginResponse = new UserDetailsResponse(response.jsonPath());
        Assert.assertEquals(testerLoginResponse.getId(),3);
        Assert.assertEquals(testerLoginResponse.getName(),"Al Skept-Cal Tester");
        Assert.assertEquals(testerLoginResponse.getUsername(),"tester");
        Assert.assertEquals(testerLoginResponse.getEmail(),"as.tester@wearewaes.com");
        Assert.assertEquals(testerLoginResponse.getSuperpower(),"Voltage AND Current.");
        Assert.assertEquals(testerLoginResponse.getDateOfBirth(),"2014-07-15");
        Assert.assertFalse(testerLoginResponse.isAdmin());
        System.out.println("Login response for tester user verified");
        response.prettyPrint();
    }

    @Test(priority = 1)
    public void userInformationEndpointTest(){
        // This test calls the login api with the different users, checks for a 200 response code and validates all values
        System.out.println("Testing user details response for dev user");
        Response response = UserDetailsResponse.userDetails("dev");
        Assert.assertEquals(200, response.getStatusCode());
        UserDetailsResponse devUserDetailsResponse = new UserDetailsResponse(response.jsonPath());
        Assert.assertEquals(devUserDetailsResponse.getId(),2);
        Assert.assertEquals(devUserDetailsResponse.getName(),"Zuper Dooper Dev");
        Assert.assertEquals(devUserDetailsResponse.getUsername(),"dev");
        Assert.assertEquals(devUserDetailsResponse.getEmail(),"zd.dev@wearewaes.com");
        Assert.assertEquals(devUserDetailsResponse.getSuperpower(),"Debug a repellent factory storage.");
        Assert.assertEquals(devUserDetailsResponse.getDateOfBirth(),"1999-10-10");
        Assert.assertFalse(devUserDetailsResponse.isAdmin());
        System.out.println("User information response for dev user verified");
        response.prettyPrint();

        System.out.println("Testing user details response for admin user");
        response = UserDetailsResponse.userDetails("admin");
        Assert.assertEquals(200, response.getStatusCode());
        UserDetailsResponse adminUserDetailsResponse = new UserDetailsResponse(response.jsonPath());
        Assert.assertEquals(adminUserDetailsResponse.getId(),1);
        Assert.assertEquals(adminUserDetailsResponse.getName(),"Amazing Admin");
        Assert.assertEquals(adminUserDetailsResponse.getUsername(),"admin");
        Assert.assertEquals(adminUserDetailsResponse.getEmail(),"a.admin@wearewaes.com");
        Assert.assertEquals(adminUserDetailsResponse.getSuperpower(),"Change the course of a waterfall.");
        Assert.assertEquals(adminUserDetailsResponse.getDateOfBirth(),"1984-09-18");
        Assert.assertTrue(adminUserDetailsResponse.isAdmin());
        System.out.println("User information response for admin user verified");
        response.prettyPrint();

        System.out.println("Testing user details response for tester user");
        response = UserDetailsResponse.userDetails("tester");
        Assert.assertEquals(200, response.getStatusCode());
        UserDetailsResponse testerUserDetailsResponse = new UserDetailsResponse(response.jsonPath());
        Assert.assertEquals(testerUserDetailsResponse.getId(),3);
        Assert.assertEquals(testerUserDetailsResponse.getName(),"Al Skept-Cal Tester");
        Assert.assertEquals(testerUserDetailsResponse.getUsername(),"tester");
        Assert.assertEquals(testerUserDetailsResponse.getEmail(),"as.tester@wearewaes.com");
        Assert.assertEquals(testerUserDetailsResponse.getSuperpower(),"Voltage AND Current.");
        Assert.assertEquals(testerUserDetailsResponse.getDateOfBirth(),"2014-07-15");
        Assert.assertFalse(testerUserDetailsResponse.isAdmin());
        System.out.println("User information response for tester user verified");
        response.prettyPrint();

    }
}
