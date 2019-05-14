package SeleniumAutomation.ApiTests;

import SeleniumAutomation.Api.UserEndpoint;
import SeleniumAutomation.Enums.ECredentials;
import SeleniumAutomation.Utils.ConfigFileReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static SeleniumAutomation.Api.UserEndpoint.deleteUser;

public class DeleteUserTest {

    private ConfigFileReader reader = new ConfigFileReader();

    // This test calls the delete endpoint for a user, checks for a 200 response code and validates user has been removed
    @Test(priority = 2, description = "API: Validate users can be deleted")
    public void userDeleteTest() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Validating that a user can be deleted");
        System.out.println("----------------------------------------------------------------------");
        UserEndpoint newUser = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        newUser.setPassword("123456");
        UserEndpoint.createUser(newUser);
        ArrayList allUsers = UserEndpoint.getAllUsers(reader.getCredentials(ECredentials.ADMIN_USER),
                reader.getCredentials(ECredentials.ADMIN_USER_PASS));
        UserEndpoint userToBeDeleted = (UserEndpoint) allUsers.get(allUsers.size() - 1);
        Response response = deleteUser(reader.getCredentials(ECredentials.ADMIN_USER),
                reader.getCredentials(ECredentials.ADMIN_USER_PASS), userToBeDeleted);
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("User '" + userToBeDeleted.getUsername() + "' removed from database.", response.getBody().prettyPrint());
        allUsers = UserEndpoint.getAllUsers(reader.getCredentials(ECredentials.ADMIN_USER),
                reader.getCredentials(ECredentials.ADMIN_USER_PASS));
        UserEndpoint lastUser = (UserEndpoint) allUsers.get(allUsers.size() - 1);
        Assert.assertNotEquals(userToBeDeleted.getId(), lastUser.getId(), "User was not deleted and it is still " +
                "present on the system with id" + userToBeDeleted.getId());
    }

    // This test calls the delete endpoint with non admin user and checks that the user is not deleted
    @Test(priority = 2, description = "API: Validate non admin users cannot delete another user")
    public void invalidCredentialsUserDeleteTest() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Validating that non admin users cannot delete");
        System.out.println("----------------------------------------------------------------------");
        ArrayList allUsers = UserEndpoint.getAllUsers(reader.getCredentials(ECredentials.ADMIN_USER),
                reader.getCredentials(ECredentials.ADMIN_USER_PASS));
        int allUsersSize = UserEndpoint.getAllUsers(reader.getCredentials(ECredentials.ADMIN_USER),
                reader.getCredentials(ECredentials.ADMIN_USER_PASS)).size();
        UserEndpoint userToBeDeleted = (UserEndpoint) allUsers.get(allUsers.size() - 2);
        UserEndpoint newUser = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        newUser.setPassword("123456");
        UserEndpoint.createUser(newUser);
        Response deleteUserResponse = UserEndpoint.deleteUser(newUser.getUsername(), newUser.getPassword(), userToBeDeleted);
        Assert.assertTrue(400 <= deleteUserResponse.getStatusCode(), "Non admin user was able to delete an user");
        Assert.assertEquals(allUsersSize, UserEndpoint.getAllUsers(reader.getCredentials(ECredentials.ADMIN_USER),
                reader.getCredentials(ECredentials.ADMIN_USER_PASS)).size(),
                "Non admin user credentials delete request was sent and amount of users decreased");
        System.out.println("Response is: ");
        deleteUserResponse.prettyPrint();
    }

    // This test calls the delete endpoint with a non existent user and checks system error handling
    @Test(priority = 2, description = "API: Validate deleting a non existent user error handling")
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
        int allUsersSize = UserEndpoint.getAllUsers(reader.getCredentials(ECredentials.ADMIN_USER),
                reader.getCredentials(ECredentials.ADMIN_USER_PASS)).size();
        Response response = RestAssured.given().log().all()
                .auth()
                .preemptive()
                .basic(reader.getCredentials(ECredentials.ADMIN_USER), reader.getCredentials(ECredentials.ADMIN_USER_PASS))
                .body(createBody.toString())
                .contentType(String.valueOf(ContentType.APPLICATION_JSON))
                .when()
                .delete("http://localhost:8081/waesheroes/api/v1/users");
        Assert.assertEquals("Username nonexistant does not exist.", response.jsonPath().getString("message"));
        Assert.assertTrue(400 <= response.getStatusCode(), "Successful delete response for non existent user");
        System.out.println("Validating that amount of users has not decreased");
        Assert.assertEquals(allUsersSize, UserEndpoint.getAllUsers(reader.getCredentials(ECredentials.ADMIN_USER),
                reader.getCredentials(ECredentials.ADMIN_USER_PASS)).size(),
                "Non existent user delete request was sent and amount of users decreased");
    }
}
