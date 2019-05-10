package SeleniumAutomation.Api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserDetailsResponse {
private final int id;
private final String name;
private final String username;
private final String email;
private final String superpower;
private final String dateOfBirth;
private final boolean isAdmin;

    public UserDetailsResponse(JsonPath loginResponse) {
        this.id = loginResponse.getInt("id");
        this.name = loginResponse.getString("name");
        this.username = loginResponse.getString("username");
        this.email = loginResponse.getString("email");
        this.superpower = loginResponse.getString("superpower");
        this.dateOfBirth = loginResponse.getString("dateOfBirth");
        this.isAdmin = loginResponse.getBoolean("isAdmin");
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public static Response login(String username, String password) {
        return RestAssured.given()
                .auth()
                .preemptive()
                .basic(username, password)
                .when()
                .get("http://localhost:8081/waesheroes/api/v1/users/access");
    }

    public static Response userDetails(String username) {
        return RestAssured.given()
                .when()
                .queryParam("username", username)
                .get("http://localhost:8081/waesheroes/api/v1/users/details");
    }
}
