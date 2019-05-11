package SeleniumAutomation.Api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.entity.ContentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class UserEndpoint {
    private int id;
    private String name;
    private String username;
    private String email;
    private String superpower;
    private String dateOfBirth;
    private boolean isAdmin;
    private String password;

    public UserEndpoint(HashMap loginResponse) {
        this.id = (int) loginResponse.get("id");
        this.name = (String) loginResponse.get("name");
        this.username = (String) loginResponse.get("username");
        this.email = (String) loginResponse.get("email");
        this.superpower = (String) loginResponse.get("superpower");
        this.dateOfBirth = (String) loginResponse.get("dateOfBirth");
        this.isAdmin = (boolean) loginResponse.get("isAdmin");
    }

    public UserEndpoint(int id, String name, String username, String email, String superpower, String dateOfBirth,
                        boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.superpower = superpower;
        this.dateOfBirth = dateOfBirth;
        this.isAdmin = isAdmin;
    }

    public static String getJson(UserEndpoint user) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(user);
    }

    public static Response createUser(UserEndpoint user) throws JsonProcessingException {
        return RestAssured.given().log().all()
                .body(getJson(user))
                .contentType(String.valueOf(ContentType.APPLICATION_JSON))
                .when()
                .post("http://localhost:8081/waesheroes/api/v1/users");
    }


    public static Response updateUser(String username, String password, UserEndpoint user) throws JsonProcessingException {
        return RestAssured.given().log().all()
                .auth()
                .preemptive()
                .basic(username, password)
                .body(getJson(user))
                .contentType(String.valueOf(ContentType.APPLICATION_JSON))
                .when()
                .put("http://localhost:8081/waesheroes/api/v1/users");
    }

    public static Response deleteUser(String username, String password, UserEndpoint user) throws JsonProcessingException {
        return RestAssured.given().log().all()
                .auth()
                .preemptive()
                .basic(username, password)
                .body(getJson(user))
                .contentType(String.valueOf(ContentType.APPLICATION_JSON))
                .when()
                .delete("http://localhost:8081/waesheroes/api/v1/users");
    }

    public static ArrayList getAllUsers(String username, String password) {
        Response allUsersResponse = RestAssured.given().log().all()
                .auth()
                .preemptive()
                .basic(username, password)
                .when()
                .get("http://localhost:8081/waesheroes/api/v1/users/all");
        ArrayList<HashMap> allUsers = allUsersResponse.jsonPath().get();
        ArrayList<UserEndpoint> result = new ArrayList<>();
        for (HashMap u : allUsers) {
            HashMap user = new HashMap(u);
            result.add(new UserEndpoint(user));
        }
        return result;
    }

    public static Response getAllUsersResponse(String username, String password) {
        return RestAssured.given().log().all()
                .auth()
                .preemptive()
                .basic(username, password)
                .when()
                .get("http://localhost:8081/waesheroes/api/v1/users/all");

    }

    public static Response login(String username, String password) {
        return RestAssured.given().log().all()
                .auth()
                .preemptive()
                .basic(username, password)
                .when()
                .get("http://localhost:8081/waesheroes/api/v1/users/access");
    }

    public static Response userDetails(String username) {
        return RestAssured.given().log().all()
                .when()
                .queryParam("username", username)
                .get("http://localhost:8081/waesheroes/api/v1/users/details");
    }

    public static int getLastUserId() {
        ArrayList allUsers = UserEndpoint.getAllUsers("admin", "hero");
        UserEndpoint lastUser = (UserEndpoint) allUsers.get(allUsers.size() - 1);
        return lastUser.getId();
    }

    public static String getRandomSuperpower() {
        // Selects super power from a random list
        String[] superPower = {"invisibility", "super strength", "invulnerability", "spacewalking", "dancing", "super cook",
                "super tester", "super dev"};
        Random r = new Random();
        int randomNumber = r.nextInt(superPower.length);
        return superPower[randomNumber];
    }

    public static String getRandomName() {
        // Selects name from a random list
        String[] name = {"Aaren", "Abbye", "Adeline", "Adoree", "Roman", "Lionel",
                "Clay", "Philip"};
        Random r = new Random();
        int randomNumber = r.nextInt(name.length);
        return name[randomNumber];
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getSuperpower() {
        return superpower;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean getisAdmin() {
        return isAdmin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setisAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
