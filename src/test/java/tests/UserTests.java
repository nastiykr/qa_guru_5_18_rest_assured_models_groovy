package tests;

import lombok.UserData;
import lombok.UserCreateUpdateLogin;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class UserTests {

    @Test
    void createUser() {
        // @formatter:off
        Specs.request
                    .body("{ \"name\": \"nasst\", \"job\": \"qa\" }")
                .when()
                    .post("/api/users")
                    .then()
                    .log().body()
                    .statusCode(201)
                    .body("name", is("nasst"))
                    .extract().as(UserCreateUpdateLogin.class);
        // @formatter:on
    }

    @Test
    void updateUserInfo() {
        // @formatter:off
        Specs.request
                    .body("{ \"name\": \"nast\"}")
                .when()
                    .put("/api/users/2")
                .then()
                    .statusCode(200)
                    .body("name", is("nast"))
                    .log().body()
                    .extract().as(UserCreateUpdateLogin.class);
        // @formatter:on
    }

    @Test
    void listUsersTest() {
        // @formatter:off
        Specs.request
                .when()
                    .get("/users?page=2")
                .then()
                    .log().body()
                    .statusCode(200)
                .body("data.findAll{it.last_name = 'Funke'}.email.flatten()",
                        hasItem("tobias.funke@reqres.in"));
        // @formatter:on
    }

    @Test
    void getInfoUser() {
        // @formatter:off
        Specs.request
                .when()
                    .get("/users/2")
                .then()
                    .log().body()
                    .statusCode(200)
                    .extract().as(UserData.class);
        // @formatter:on
    }

    @Test
    void loginWithNoPassword() {
        // @formatter:off
        Specs.request
                    .body("{ \"email\": \"peter@klaven\" }")
                .when()
                    .post("/api/login")
                .then()
                    .log().body()
                    .statusCode(201)
                    .extract().as(UserCreateUpdateLogin.class);
        // @formatter:on
    }
}
