package SeleniumAutomation.ApiTests;

import SeleniumAutomation.Api.UserEndpoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpEndpointTest {

    // This test calls the signUp endpoint, creates a new user and validates the created user values
    @Test(priority = 2)
    public void createUserEndpointTest() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Test creation of new users");
        System.out.println("----------------------------------------------------------------------");
        UserEndpoint newUser = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        newUser.setPassword("123456");
        Response response = UserEndpoint.createUser(newUser);
        System.out.println("Response is");
        response.prettyPrint();
        Assert.assertEquals(201, response.getStatusCode());
        System.out.println("Validating new created user values match expected values");
        UserEndpoint createdUser = new UserEndpoint(response.jsonPath().get());
        Assert.assertEquals(createdUser.getId(), newUser.getId());
        Assert.assertEquals(createdUser.getName(), newUser.getName());
        Assert.assertEquals(createdUser.getUsername(), newUser.getUsername());
        Assert.assertEquals(createdUser.getEmail(), newUser.getEmail());
        Assert.assertEquals(createdUser.getSuperpower(), newUser.getSuperpower());
        Assert.assertEquals(createdUser.getDateOfBirth(), newUser.getDateOfBirth());
        Assert.assertEquals(createdUser.getisAdmin(), newUser.getisAdmin());
    }

    // This test calls the signUp endpoint with invalid values
    @Test(priority = 2)
    public void createUserInvalidValuesEndpointTest() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Test invalid values on post body");
        System.out.println("----------------------------------------------------------------------");
        JsonObject createBody = new JsonObject();
        createBody.addProperty("id", "hello");
        createBody.addProperty("name", 1);
        createBody.addProperty("username", true);
        createBody.addProperty("email", 1);
        createBody.addProperty("superpower", false);
        createBody.addProperty("dateOfBirth", 1982);
        createBody.addProperty("isAdmin", "true");
        createBody.addProperty("password", 123456);
        Assert.assertEquals(400,
                RestAssured.given().log().all()
                        .body(createBody.toString())
                        .contentType(String.valueOf(ContentType.APPLICATION_JSON))
                        .when()
                        .post("http://localhost:8081/waesheroes/api/v1/users").getStatusCode(),
                "Successful create user response for invalid values");
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Test create user with already taken username");
        System.out.println("----------------------------------------------------------------------");
        UserEndpoint user = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        user.setPassword("123456");
        UserEndpoint.createUser(user);
        UserEndpoint newUserWithSameUsername = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        newUserWithSameUsername.setUsername(user.getUsername());
        Response createResponse = UserEndpoint.createUser(user);
        Assert.assertEquals(403, createResponse.getStatusCode());
        Assert.assertEquals("Username or email already registered. Please select different values.", createResponse.jsonPath().getString("message"));
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Test create user with already taken email");
        System.out.println("----------------------------------------------------------------------");
        user = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        user.setPassword("123456");
        UserEndpoint.createUser(user);
        newUserWithSameUsername = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        newUserWithSameUsername.setEmail(user.getEmail());
        createResponse = UserEndpoint.createUser(user);
        Assert.assertEquals(403, createResponse.getStatusCode());
        Assert.assertEquals("Username or email already registered. Please select different values.", createResponse.jsonPath().getString("message"));
    }
}
