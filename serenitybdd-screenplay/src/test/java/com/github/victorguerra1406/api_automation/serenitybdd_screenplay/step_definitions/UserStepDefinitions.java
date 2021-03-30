package com.github.victorguerra1406.api_automation.serenitybdd_screenplay.step_definitions;

import com.github.victorguerra1406.api_automation.serenitybdd_screenplay.models.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;

import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import static com.github.victorguerra1406.api_automation.serenitybdd_screenplay.utils.DateUtils.actualDateFormatted;
import static com.github.victorguerra1406.api_automation.serenitybdd_screenplay.utils.JsonUtils.toJson;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class UserStepDefinitions {
    @Given("{string} is a valid user")
    public void isAValidUser(String actor) {
        theActorCalled(actor);
    }

    @When("he makes a GET request to the endpoint {string}")
    public void iMadeAGetRequestToTheEndpoint(String endpoint) {
        theActorInTheSpotlight().attemptsTo(
                Get.resource(endpoint)
        );
    }

    @Then("he should see the status code {int}")
    public void iShouldSeeTheStatusCode(int statusCode) {
        theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(lastResponse().statusCode())
                                .isEqualTo(statusCode)
                );
    }

    @And("he should see the list of users with the following fields")
    @And("he should see the pagination metadata with the following fields")
    public void iShouldSeeTheListOfUsersWithTheFollowingFields(List<String> fields) {
        fields.forEach(field ->
                theActorInTheSpotlight()
                        .attemptsTo(
                                Ensure.that(lastResponse().jsonPath().getString(field))
                                        .isNotNull()
                        )
        );
    }

    @And("he should see the following fields with the respective values")
    public void iShouldSeeTheFollowingFieldsWithTheRespectiveValues(Map<String, String> fieldsAndValues) {
        fieldsAndValues.forEach((key, value) ->
                theActorInTheSpotlight()
                        .attemptsTo(
                                Ensure.that(lastResponse().jsonPath().getString(key))
                                        .isEqualTo(value)
                        )
        );
    }

    @When("he informs the data to create a new user")
    @When("he informs the data to update a user")
    public void iInformTheDataToCreateANewUser() {
        User user = new User();
        user.setName("João da Silva");
        user.setJob("Leader");
        setSessionVariable("BODY").to(user);
    }

    @And("he makes a POST request to the endpoint {string}")
    public void iMadeAPOSTRequestToTheEndpointUsers(String endpoint) {
        User user = sessionVariableCalled("BODY");
        theActorInTheSpotlight().attemptsTo(
                Post.to(endpoint)
                        .with(request -> request.body(toJson(user)))
        );
    }

    @And("he should see the field {string} with the generated value")
    public void iShouldSeeTheFieldIdWithTheGeneratedValue(String field) {
        theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(lastResponse().jsonPath().getString(field))
                                .isNotNull()
                );
    }

    @And("he should see the field {string} with the actual date and time")
    public void iShouldSeeTheFieldCreatedAtWithTheActualDateAndTime(String field) {
        theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(lastResponse().jsonPath().getString(field))
                                .startsWith(actualDateFormatted("yyyy-MM-dd'T'HH:mm", TimeZone.getTimeZone("Etc/GMT")))
                );
    }

    @And("he makes a PUT request to the endpoint {string}")
    public void iMadeAPUTRequestToTheEndpointUsers(String endpoint) {
        User user = sessionVariableCalled("BODY");
        theActorInTheSpotlight().attemptsTo(
                Put.to(endpoint)
                        .with(request -> request.body(toJson(user)))
        );
    }

    @When("he makes a DELETE request to the endpoint {string}")
    public void iMadeADELETERequestToTheEndpointUsers(String endpoint) {
        theActorInTheSpotlight().attemptsTo(
                Delete.from(endpoint)
        );
    }
}
