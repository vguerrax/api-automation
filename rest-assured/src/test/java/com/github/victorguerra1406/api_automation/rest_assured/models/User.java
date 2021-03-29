package com.github.victorguerra1406.api_automation.rest_assured.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private String name;
    private String job;
    private String id;
    private String createdAt;
}
