package com.setu.api;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/com/setu/api/Features/test.feature"
		,glue={"com/setu/api/StepDefs/"}
		,plugin = { "pretty", "html:target/cucumber-reports" }
		)

public class Runner {
	
	
}  