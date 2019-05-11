package SeleniumAutomation.ApiTests;

import SeleniumAutomation.Api.UserEndpoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Instant;

public class SignUpEndpointTest {

    @Test(priority = 1)
    public void createUserEndpointTest() throws JsonProcessingException {
        // This test calls the signup endpoint, creates a new user and validates the created user values
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Test creation of new user");
        System.out.println("----------------------------------------------------------------------");
        UserEndpoint newUser = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(), "emiliano.pollero"
                + Instant.now(), "test" + Instant.now() + "@test.com", UserEndpoint.getRandomSuperpower(), "1982-06-19", false);
        newUser.setPassword("123456");
        Response response = UserEndpoint.createUser(newUser);
        System.out.println("Response is");
        response.prettyPrint();
        Assert.assertEquals(201, response.getStatusCode());
        UserEndpoint createdUser = new UserEndpoint(response.jsonPath().get());
        Assert.assertEquals(createdUser.getId(), newUser.getId());
        Assert.assertEquals(createdUser.getName(), newUser.getName());
        Assert.assertEquals(createdUser.getUsername(), newUser.getUsername());
        Assert.assertEquals(createdUser.getEmail(), newUser.getEmail());
        Assert.assertEquals(createdUser.getSuperpower(), newUser.getSuperpower());
        Assert.assertEquals(createdUser.getDateOfBirth(), newUser.getDateOfBirth());
        Assert.assertEquals(createdUser.getisAdmin(), newUser.getisAdmin());
    }

    @Test(priority = 1)
    public void createUserInvalidValuesEndpointTest() {
        // This test calls the signup endpoint with invalid values
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
        Response response = RestAssured.given().log().all()
                .body(createBody.toString())
                .contentType(String.valueOf(ContentType.APPLICATION_JSON))
                .when()
                .post("http://localhost:8081/waesheroes/api/v1/users");
        Assert.assertEquals(400, response.getStatusCode());
    }

}
