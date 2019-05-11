package SeleniumAutomation.ApiTests;

import SeleniumAutomation.Api.UserEndpoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class DeleteUserTest {

    @Test(priority = 1)
    public void userDeleteTest() throws JsonProcessingException {
        // This test calls the login api with the different users, checks for a 200 response code and validates all values
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Validating that a user can be deleted");
        System.out.println("----------------------------------------------------------------------");
        ArrayList allUsers = UserEndpoint.getAllUsers("admin", "hero");
        UserEndpoint userToBeDeleted = (UserEndpoint) allUsers.get(allUsers.size() - 1);
        UserEndpoint.deleteUser("admin", "hero", userToBeDeleted);
        allUsers = UserEndpoint.getAllUsers("admin", "hero");
        UserEndpoint lastUser = (UserEndpoint) allUsers.get(allUsers.size() - 1);
        Assert.assertNotEquals(userToBeDeleted.getId(), lastUser.getId(), "User was not deleted and it is still " +
                "present on the system with id" + userToBeDeleted.getId());
    }

    @Test(priority = 1)
    public void invalidCredentialsUserDeleteTest() throws JsonProcessingException {
        // This test calls the login api with the different users, checks for a 200 response code and validates all values
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Validating that non admin users cannot delete");
        System.out.println("----------------------------------------------------------------------");
        ArrayList allUsers = UserEndpoint.getAllUsers("admin", "hero");
        int allUsersSize = UserEndpoint.getAllUsers("admin", "hero").size();
        UserEndpoint userToBeDeleted = (UserEndpoint) allUsers.get(allUsers.size() - 1);
        Assert.assertTrue(400 <= UserEndpoint.deleteUser("tester", "maniac",
                userToBeDeleted).getStatusCode(), "Non admin user was able to delete an user");
        Assert.assertEquals(allUsersSize, UserEndpoint.getAllUsers("admin", "hero").size(),
                "Non admin user credentials delete request was sent and amount of users decreased");
    }

    @Test(priority = 1)
    public void nonExistentUserDeleteTest() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing deleting a non existent user");
        System.out.println("----------------------------------------------------------------------");
        JsonObject createBody = new JsonObject();
        createBody.addProperty("id", "1000000");
        createBody.addProperty("name", "nonexistant");
        createBody.addProperty("username", "nonexistant");
        createBody.addProperty("email", "nonexistant@nonexistant.com");
        createBody.addProperty("superpower", "nonexistant");
        createBody.addProperty("dateOfBirth", "2090-12-01");
        createBody.addProperty("isAdmin", false);
        createBody.addProperty("password", "nonexistant");
        int allUsersSize = UserEndpoint.getAllUsers("admin", "hero").size();
        Assert.assertTrue(400 <= RestAssured.given().log().all()
                        .auth()
                        .preemptive()
                        .basic("admin", "hero")
                        .body(createBody.toString())
                        .contentType(String.valueOf(ContentType.APPLICATION_JSON))
                        .when()
                        .put("http://localhost:8081/waesheroes/api/v1/users").getStatusCode(),
                "Successful update response for non existent user");
        System.out.println("Validating that amount of users has not decreased");
        Assert.assertEquals(allUsersSize, UserEndpoint.getAllUsers("admin", "hero").size(),
                "Non existent user delete request was sent and amount of users decreased");
    }
}
