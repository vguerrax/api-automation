package com.github.victorguerra1406.api_automation.serenitybdd_screenplay.tests;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/users.feature",
        glue = {"com.github.victorguerra1406.api_automation.serenitybdd_screenplay.step_definitions",
                "com.github.victorguerra1406.api_automation.serenitybdd_screenplay.hooks"})
public class UserTest {
}
