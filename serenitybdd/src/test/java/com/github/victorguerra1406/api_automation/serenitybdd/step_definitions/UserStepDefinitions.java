package com.github.victorguerra1406.api_automation.serenitybdd.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;

import java.util.List;
import java.util.Map;

import static com.github.victorguerra1406.api_automation.serenitybdd.utils.RequestUtils.newRequest;
import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UserStepDefinitions {

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
        fieldsAndValues.forEach((key, value) -> then().body(key, equalTo(value)));
    }
}
