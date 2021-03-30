package com.github.victorguerra1406.api_automation.serenitybdd.step_definitions;

import com.github.victorguerra1406.api_automation.serenitybdd.models.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import static com.github.victorguerra1406.api_automation.serenitybdd.utils.DateUtils.actualDateFormatted;
import static com.github.victorguerra1406.api_automation.serenitybdd.utils.JsonUtils.toJson;
import static com.github.victorguerra1406.api_automation.serenitybdd.utils.RequestUtils.newRequest;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.*;

public class UserStepDefinitions {
    @Given("{string} is a valid user")
    public void isAValidUser(String actor) {
    }

    @When("I made a GET request to the endpoint {string}")
    public void iMadeAGetRequestToTheEndpoint(String endpoint) {
        newRequest()
                .when().get(endpoint);
    }

    @Then("I should see the status code {int}")
    public void iShouldSeeTheStatusCode(int statusCode) {
        then().statusCode(statusCode);
    }

    @And("I should see the list of users with the following fields")
    @And("I should see the pagination metadata with the following fields")
    public void iShouldSeeTheListOfUsersWithTheFollowingFields(List<String> fields) {
        fields.forEach(field ->
                then().body(field, notNullValue())
        );
    }

    @And("I should see the following fields with the respective values")
    public void iShouldSeeTheFollowingFieldsWithTheRespectiveValues(Map<String, String> fieldsAndValues) {
        fieldsAndValues.forEach((key, value) -> {
            if ("data.id".equals(key))
                then().body(key, equalTo(Integer.parseInt(value)));
            else
                then().body(key, equalTo(value));
        });
    }

    @When("I inform the data to create a new user")
    @When("I inform the data to update a user")
    public void iInformTheDataToCreateANewUser() {
        User user = new User();
        user.setName("João da Silva");
        user.setJob("Leader");
        setSessionVariable("BODY").to(user);
    }

    @And("I made a POST request to the endpoint {string}")
    public void iMadeAPOSTRequestToTheEndpointUsers(String endpoint) {
        User user = sessionVariableCalled("BODY");
        newRequest()
                .body(toJson(user))
                .when().post(endpoint);
    }

    @And("I should see the field {string} with the generated value")
    public void iShouldSeeTheFieldIdWithTheGeneratedValue(String field) {
        then().body(field, notNullValue());
    }

    @And("I should see the field {string} with the actual date and time")
    public void iShouldSeeTheFieldCreatedAtWithTheActualDateAndTime(String field) {
        then().body(field, startsWith(actualDateFormatted("yyyy-MM-dd'T'HH:mm", TimeZone.getTimeZone("Etc/GMT"))));
    }

    @And("I made a PUT request to the endpoint {string}")
    public void iMadeAPUTRequestToTheEndpointUsers(String endpoint) {
        User user = sessionVariableCalled("BODY");
        newRequest()
                .body(toJson(user))
                .when().put(endpoint);
    }

    @When("I made a DELETE request to the endpoint {string}")
    public void iMadeADELETERequestToTheEndpointUsers(String endpoint) {
        newRequest()
                .when().delete(endpoint);
    }
}
