package com.github.victorguerra1406.api_automation.rest_assured.tests;

import com.github.victorguerra1406.api_automation.rest_assured.models.User;
import org.junit.Test;

import java.util.TimeZone;

import static com.github.victorguerra1406.api_automation.rest_assured.endpoints.Endpoint.USERS;
import static com.github.victorguerra1406.api_automation.rest_assured.endpoints.Endpoint.USERS_ID;
import static com.github.victorguerra1406.api_automation.rest_assured.utils.DateUtils.actualDateFormatted;
import static com.github.victorguerra1406.api_automation.rest_assured.utils.JsonUtils.toJson;
import static com.github.victorguerra1406.api_automation.rest_assured.utils.RequestUtils.newRequest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserTest {

    @Test
    public void listUsers() {
        given(newRequest())
                .when().get(USERS.path())
                .then()
                //Status Code is 200
                .statusCode(200)
                //List is not empty
                .body("data", not(empty()))
                //Id of every listed user is not null
                .body("data.id", everyItem(not(empty())))
                //Email of every listed user is not null
                .body("data.email", everyItem(not(empty())))
                //First Name of every listed user is not null
                .body("data.first_name", everyItem(not(empty())))
                //Last Name of every listed user is not null
                .body("data.last_name", everyItem(not(empty())))
                //Avatar of every listed user is not null
                .body("data.avatar", everyItem(not(empty())))
                //Page is not null
                .body("page", notNullValue())
                //Per Page is not null
                .body("per_page", notNullValue())
                //Total is not null
                .body("total", notNullValue())
                //Total Pages is not null
                .body("total_pages", notNullValue())
                //Support URL is equal to "https://reqres.in/#support-heading"
                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                //Support Text is equal to "To keep ReqRes free, contributions towards server costs are appreciated!"
                .body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

    }

    @Test
    public void getSingleUser() {
        given(newRequest())
                .pathParam("userId", 1)
                .when().get(USERS_ID.path())
                .then()
                //Status Code is 200
                .statusCode(200)
                //Set root path for jsonPath
                .rootPath("data")
                //Id is equal to 1
                .body("id", equalTo(1))
                //Email is equal to "george.bluth@reqres.in"
                .body("email", equalTo("george.bluth@reqres.in"))
                //Fisrt Name is equal to "George"
                .body("first_name", equalTo("George"))
                //Last Name is equal to "Bluth"
                .body("last_name", equalTo("Bluth"))
                //Avatar is equal to "https://reqres.in/img/faces/1-image.jpg"
                .body("avatar", equalTo("https://reqres.in/img/faces/1-image.jpg"));
    }

    @Test
    public void createUser() {
        User user = new User();
        user.setName("João Silva");
        user.setJob("Leader");

        given(newRequest())
                .body(toJson(user))
                .when().post(USERS.path())
                .then()
                //Status Code is 201
                .statusCode(201)
                //Id is not null
                .body("id", notNullValue())
                //Created At is the actual date
                .body("createdAt", startsWith(actualDateFormatted("yyyy-MM-dd'T'HH:mm", TimeZone.getTimeZone("Etc/GMT"))));
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setName("João Silva");
        user.setJob("Leader");

        given(newRequest())
                .body(toJson(user))
                .pathParam("userId", 1)
                .when().put(USERS_ID.path())
                .then()
                //Status Code is 200
                .statusCode(200)
                //Updated At is the actual date
                .body("updatedAt", startsWith(actualDateFormatted("yyyy-MM-dd'T'HH:mm", TimeZone.getTimeZone("Etc/GMT"))));
    }

    @Test
    public void deleteUser() {
        given(newRequest())
                .pathParam("userId", 1)
                .when().delete(USERS_ID.path())
                .then()
                //Status Code is 204
                .statusCode(204);
    }
}
