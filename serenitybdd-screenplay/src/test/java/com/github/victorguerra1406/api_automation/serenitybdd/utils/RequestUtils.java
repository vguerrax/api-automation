package com.github.victorguerra1406.api_automation.serenitybdd.utils;

import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

import static com.github.victorguerra1406.api_automation.serenitybdd.Properties.BASE_URI;
import static com.github.victorguerra1406.api_automation.serenitybdd.Properties.getProperty;

public class RequestUtils {

    public static RequestSpecification newRequest() {
        return SerenityRest.given()
                .baseUri(getProperty(BASE_URI));
    }
}
