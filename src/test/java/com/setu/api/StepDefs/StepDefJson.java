package com.setu.api.StepDefs;

import org.assertj.core.api.SoftAssertions;

import com.setu.api.core.JsonUtil;
import com.setu.api.core.Utils;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import cucumber.api.Scenario;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefJson {
	
	public ReadContext docJsonContext;
	private Scenario scenario;
	
	public static final Configuration configuration=Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider()).mappingProvider(new JacksonMappingProvider()
			).build();
	
	
	public void before(Scenario scenario) {
		this.scenario=scenario;
	}
	
	@Then("^I print \"([^\"]*)\"$")
	public void printValueFromResponse(String value) {
		String objResult=JsonUtil.readValueFromJSON(StepDefCore.resJsonContext, value.split("-->")[0]);
		if(objResult==null) {
			scenario.write("JsonPath"+value+"not found");
			
		}
		else {
			scenario.write(value+" = " + objResult);
		}
	}
	@Then("^I save value \"([^\"]*)\"$")
	public void saveElement(String value) {
		String objResult=JsonUtil.readValueFromJSON(StepDefCore.resJsonContext, value.split("-->")[0]);
		if(objResult==null) {
			value=value.replace(value.split("-->")[0], "");
			
		}
		else {
			value=value.replace(value.split("-->")[0], objResult);
		}
		Utils.analyzeValue(value);
	}
	@When("^I mention \"([^\"]*)\" as \"([^\"]*)\"$")
	public static void editJSON(String attribute, String value) { 
		value=Utils.analyzeValue(value);
		StepDefCore.stringReqPayLoad=JsonPath.using(configuration).parse(StepDefCore.stringReqPayLoad).set(attribute, value).jsonString();
	}
	
	@Then("^I verify \"([^\"]*)\" as \"([^\"]*)\"$")
	public void readValueFromResponse(String jsonQuery,String expectedValue) {
		String attributeValue=Utils.returnValueFromResponse(StepDefCore.resJsonContext, jsonQuery);
		expectedValue=Utils.analyzeValue(expectedValue);
		assertThat(attributeValue).isEqualTo(expectedValue);
		
	}
	
	@Then("^I verify \"([^\"]*)\" contains text \"([^\"]*)\"$")
	public void readValueFromResponseforSubString(String jsonQuery,String expectedValue) {
		String attributeValue=Utils.returnValueFromResponse(StepDefCore.resJsonContext, jsonQuery);
		expectedValue=Utils.analyzeValue(expectedValue);
		assertThat(attributeValue).containsIgnoringCase(expectedValue);
		
	}
	
	@Then("^ I verify multiple \"([^\"]*)\" as \"([^\"]*)\"$")
	public void verifyMultipleFromResponse(String jsonPaths,String expectedValues) {
		SoftAssertions softAssert=new SoftAssertions();
		expectedValues=expectedValues.replaceAll("ndsp;", " ");
		int i=0;
		System.out.println("Total jsonPaths"+ jsonPaths.split(",").length);
		String strResponse="";
		String[] jsonPathArray=jsonPaths.split(",");
		String[] expectedValue=expectedValues.split(",");
		for(String jsonPath:jsonPathArray) {
			System.out.println(jsonPath);
			strResponse=JsonUtil.readValueFromJSON(StepDefCore.resJsonContext, jsonPath);
			softAssert.assertThat(strResponse).isEqualTo(expectedValue[i]);
			i++;
		}
		softAssert.assertAll();
	}

	@Then("^I verify \"([^\"]*)\" is not NULL$")
	public void readValueFromResponseForNotNull(String jsonQuery) {
		//List<String> output2=StepDefCore.resJsonContext.read(jsonQuery);

		//String attributeValue=String.valueOf(output2.get(0));
		String attributeValue=Utils.returnValueFromResponse(StepDefCore.resJsonContext,jsonQuery);

		System.out.println(attributeValue);
		if(attributeValue=="null") {
			scenario.write(jsonQuery+":"+attributeValue);
			fail("failed");
			
		}
		else {
			//scenario.write(jsonQuery+":"+attributeValue);
			assertNotNull(attributeValue);
		}
	}
	
	
/*	@Then("^ I verify \"([^\"]*)\" is NOT null$")
	public void readValueFromResponseForNotNull(String jsonQuery) {
		List<String> output2=StepDefCore.resJsonContext.read(jsonQuery);
*/
							
	
	
}
