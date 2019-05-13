package SeleniumAutomation.ApiTests;

import SeleniumAutomation.Api.UserEndpoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateUserEndpointTest {

    // This test calls the update endpoint with the different users, checks for a 200 response code and validates all values
    @Test(priority = 2)
    public void updateUserNameEndpointTest() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Validating that user name can be updated");
        System.out.println("----------------------------------------------------------------------");
        UserEndpoint newUser = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        newUser.setPassword("123456");
        UserEndpoint.createUser(newUser);
        newUser.setName("updated");
        Response response = UserEndpoint.updateUser("admin", "hero", newUser);
        UserEndpoint updatedUser = new UserEndpoint(response.jsonPath().get());
        System.out.println("Response is");
        response.prettyPrint();
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(newUser.getName(), updatedUser.getName());
    }

    @Test(priority = 2)
    public void updateDateOfBirthEndpointTest() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Validating that user date of birth can be updated");
        System.out.println("----------------------------------------------------------------------");
        UserEndpoint newUser = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        newUser.setPassword("123456");
        UserEndpoint.createUser(newUser);
        newUser.setDateOfBirth("1999-06-18");
        Response response = UserEndpoint.updateUser("admin", "hero", newUser);
        UserEndpoint updatedUser = new UserEndpoint(response.jsonPath().get());
        System.out.println("Response is");
        response.prettyPrint();
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(newUser.getDateOfBirth(), updatedUser.getDateOfBirth());
    }

    @Test(priority = 2)
    public void updateAdminStatusEndpointTest() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Validating that user admin status can be updated");
        System.out.println("----------------------------------------------------------------------");
        UserEndpoint newUser = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        newUser.setPassword("123456");
        UserEndpoint.createUser(newUser);
        newUser.setisAdmin(true);
        Response response = UserEndpoint.updateUser("admin", "hero", newUser);
        UserEndpoint updatedUser = new UserEndpoint(response.jsonPath().get());
        System.out.println("Response is");
        response.prettyPrint();
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(newUser.getisAdmin(), updatedUser.getisAdmin());
    }

    @Test(priority = 2)
    public void updateSuperpowerEndpointTest() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Validating that user superpower can be updated");
        System.out.println("----------------------------------------------------------------------");
        UserEndpoint newUser = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        newUser.setPassword("123456");
        UserEndpoint.createUser(newUser);
        newUser.setSuperpower("updated");
        Response response = UserEndpoint.updateUser("admin", "hero", newUser);
        UserEndpoint updatedUser = new UserEndpoint(response.jsonPath().get());
        System.out.println("Response is");
        response.prettyPrint();
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(newUser.getSuperpower(), updatedUser.getSuperpower());
    }

    @Test(priority = 2)
    public void updatePasswordEndpointTest() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Validating that user password can be updated");
        System.out.println("----------------------------------------------------------------------");
        UserEndpoint newUser = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(), "1982-06-19", false);
        newUser.setPassword("123456");
        UserEndpoint.createUser(newUser);
        String oldPassword = "123456";
        newUser.setPassword("newPassword");
        UserEndpoint.updateUser("admin", "hero", newUser);
        Assert.assertEquals(200, UserEndpoint.login(newUser.getUsername(), "newPassword").getStatusCode());
        Assert.assertNotEquals(newUser.getPassword(), oldPassword);
    }

    @Test(priority = 2)
    public void updateEmailEndpointTest() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Validating that user email can be updated");
        System.out.println("----------------------------------------------------------------------");
        UserEndpoint newUser = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        newUser.setPassword("123456");
        UserEndpoint.createUser(newUser);
        newUser.setEmail("updated@email.com");
        Response response = UserEndpoint.updateUser("admin", "hero", newUser);
        UserEndpoint updatedUser = new UserEndpoint(response.jsonPath().get());
        System.out.println("Response is");
        response.prettyPrint();
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(newUser.getEmail(), updatedUser.getEmail());
    }

    // This test calls the update endpoint with invalid values and checks system response
    @Test(priority = 2)
    public void usernameCannotBeUpdatedTest() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Validating that user username cannot be updated");
        System.out.println("----------------------------------------------------------------------");
        UserEndpoint newUser = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        newUser.setPassword("123456");
        UserEndpoint.createUser(newUser);
        newUser.setUsername("updated");
        Response response = UserEndpoint.updateUser("admin", "hero", newUser);
        Assert.assertTrue(400 <= response.getStatusCode());
        System.out.println("Response is");
        response.prettyPrint();
    }

    @Test(priority = 2)
    public void userIdCannotBeUpdatedTest() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Validating that user id cannot be updated");
        System.out.println("----------------------------------------------------------------------");
        UserEndpoint newUser = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        newUser.setPassword("123456");
        UserEndpoint.createUser(newUser);
        newUser.setId(UserEndpoint.getLastUserId() + 100);
        Response response = UserEndpoint.updateUser("admin", "hero", newUser);
        Assert.assertTrue(400 <= response.getStatusCode());
        response.prettyPrint();
    }

    @Test(priority = 2)
    public void invalidEmailFormatTest() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Validating that email rejects invalid format");
        System.out.println("----------------------------------------------------------------------");
        UserEndpoint newUser = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        newUser.setPassword("123456");
        UserEndpoint.createUser(newUser);
        newUser.setEmail("updated");
        Response response = UserEndpoint.updateUser("admin", "hero", newUser);
        Assert.assertTrue(400 <= response.getStatusCode(), "Update operation was successful with invalid email " +
                "format with response code: " + response.getStatusCode());
    }

    @Test(priority = 2)
    public void invalidDateOfBirthFormatTest() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Validating that user date of birth rejects invalid date");
        System.out.println("----------------------------------------------------------------------");
        UserEndpoint newUser = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        newUser.setPassword("123456");
        UserEndpoint.createUser(newUser);
        newUser.setDateOfBirth("updated");
        Response response = UserEndpoint.updateUser("admin", "hero", newUser);
        Assert.assertTrue(400 <= response.getStatusCode());
        response.prettyPrint();
    }

    // This test calls the update endpoint with non admin credentials and validates that update operation is not allowed
    @Test(priority = 2)
    public void validateOnlyAdminUserCanUpdate() throws JsonProcessingException {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Validating that non admin users cannot update other users");
        System.out.println("----------------------------------------------------------------------");
        UserEndpoint userUpdating = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        userUpdating.setPassword("123456");
        UserEndpoint.createUser(userUpdating);
        UserEndpoint userToBeUpdated = new UserEndpoint(UserEndpoint.getLastUserId() + 1, UserEndpoint.getRandomName(),
                UserEndpoint.getRandomSuperpower(),
                "1982-06-19", false);
        userToBeUpdated.setPassword("123456");
        UserEndpoint.createUser(userToBeUpdated);
        userToBeUpdated.setisAdmin(true);
        Response response = UserEndpoint.updateUser(userUpdating.getUsername(), userUpdating.getPassword(), userToBeUpdated);
        System.out.println("Response is");
        response.prettyPrint();
        Assert.assertTrue(400 <= response.getStatusCode(), "Non admin user performed an update operation and was " +
                "successful with response code: " + response.getStatusCode());
    }

    // This test calls the update endpoint for a non existent user and checks system response
    @Test(priority = 2)
    public void nonExistentUserUpdateTest() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Testing updating a non existent user");
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
        Assert.assertTrue(400 <= RestAssured.given().log().all()
                        .auth()
                        .preemptive()
                        .basic("admin", "hero")
                        .body(createBody.toString())
                        .contentType(String.valueOf(ContentType.APPLICATION_JSON))
                        .when()
                        .put("http://localhost:8081/waesheroes/api/v1/users").getStatusCode(),
                "Successful update response for non existent user");
    }
}
