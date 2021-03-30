package com.github.victorguerra1406.api_automation.serenitybdd_screenplay.endpoints;

public enum Endpoint {
    USERS("users"),
    USERS_ID("users/{userId}");

    private String endpoint;

    Endpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String path() {
        return this.endpoint;
    }
}
