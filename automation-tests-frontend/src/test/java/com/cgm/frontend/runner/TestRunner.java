package com.cgm.frontend.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.cgm.frontend.stepDefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        },
        tags = "@sanity"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
