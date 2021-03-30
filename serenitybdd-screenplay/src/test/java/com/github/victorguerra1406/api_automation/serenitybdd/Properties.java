package com.github.victorguerra1406.api_automation.serenitybdd;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class Properties {

    public static final String BASE_URI = "base.uri";

    public static String getProperty(String key) {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
        return EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(key);
    }
}
