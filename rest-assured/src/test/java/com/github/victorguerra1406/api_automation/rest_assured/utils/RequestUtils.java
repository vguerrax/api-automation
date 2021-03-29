package com.github.victorguerra1406.api_automation.rest_assured.utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static com.github.victorguerra1406.api_automation.rest_assured.Constants.BASE_URI;

public class RequestUtils {

    public static RequestSpecification newRequest() {
        return RestAssured.given()
                .baseUri(BASE_URI);
    }
}
