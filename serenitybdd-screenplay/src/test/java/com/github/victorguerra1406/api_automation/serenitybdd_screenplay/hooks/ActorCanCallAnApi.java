package com.github.victorguerra1406.api_automation.serenitybdd_screenplay.hooks;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static com.github.victorguerra1406.api_automation.serenitybdd_screenplay.Properties.BASE_URI;
import static com.github.victorguerra1406.api_automation.serenitybdd_screenplay.Properties.getProperty;

public class ActorCanCallAnApi {

    @Before
    public void setCast() {
        OnStage.setTheStage(
                OnlineCast.whereEveryoneCan(
                        CallAnApi.at(getProperty(BASE_URI))));
    }
}
