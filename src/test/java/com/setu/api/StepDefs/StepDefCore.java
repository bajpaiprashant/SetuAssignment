package com.setu.api.StepDefs;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import com.setu.api.core.Rest;
import com.setu.api.core.Utils;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import static org.assertj.core.api.Assertions.*;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class StepDefCore {
	
	public static ReadContext resJsonContext;
	public static String stringReqPayLoad;
	public static Response response;
	public static String url;
	public static Headers jsonContentHeader;
	private Scenario scenario;
	public static List<Header> listHeaders=new ArrayList<Header>();
	
	@Before
	public void before(Scenario scenario) {
		this.scenario=scenario;
	}
	@Given("^I set endpoint as \"([^\"]*)\" with endpoint \"([^\"]*)\"$")
	public static void setEndpoint(String hostPort, String api) {
		hostPort= Utils.analyzeValue(hostPort);
		System.out.println(hostPort);
		api=Utils.analyzeValue(api);
		url=hostPort+api;
	}

	@Given("^I authenticate with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void basicAuth(String email , String password){
		String username= Utils.analyzeValue(email);
		String token=Utils.analyzeValue(password);
		String authString=  Rest.basicAuth(username,token);

		listHeaders.add(new Header("Authorization ", " Basic "+authString));
		jsonContentHeader=new Headers(listHeaders);

 	}
	
	@Given("^I set endpoint as \"([^\"]*)\" with endpoint \"([^\"]*)\" along with \"([^\"]*)\"$")
	public void setEndpointAppID(String hostPort, String api,String path2) throws Exception,IOException{
		hostPort= Utils.analyzeValue(hostPort);
		path2=Utils.analyzeValue(path2);
		api=Utils.analyzeValue(api);
		url=hostPort+api+path2;

	}
	@Given("^I set endpoint as \"([^\"]*)\" with endpoint \"([^\"]*)\" along with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void setEndpointAppID(String hostPort, String api,String path2,String path3) throws Exception,IOException{
		hostPort= Utils.analyzeValue(hostPort);
		path2=Utils.analyzeValue(path2);
		path3=Utils.analyzeValue(path3);
		api=Utils.analyzeValue(api);
		url=hostPort+api+path2+path3;

	}
	
	@Given("^I use json template \"([^\"]*)\"$")
	public static void loadTemplateJSON(String jsonFileName) throws FileNotFoundException, IOException {
		StepDefCore.stringReqPayLoad=IOUtils.toString(new FileReader("src/test/resources"+jsonFileName));
	}
	
	@When("^I invoke the GET API$")
	public void invokeAPI() {
		response=Rest.getResouce(url, jsonContentHeader);
		resJsonContext=JsonPath.parse(response.getBody().asString());
		//assertThat(response.getStatusCode()).isEqualTo(200);
		jsonContentHeader=null;
		listHeaders.clear();
	}
	
	@Then("^I invoke the POST API with json payload$")
	public static void invokePostAPIWithPayload() {
		response=Rest.postResource( url,stringReqPayLoad, jsonContentHeader);
		resJsonContext=JsonPath.parse(response.getBody().asString());
		System.out.println(response.getBody().asString());
		//assertThat(response.getStatusCode()).isEqualTo(200);
		jsonContentHeader=null;
		listHeaders.clear();
	}
	
	@Then("^I invoke the PUT API with json payload$")
	public static void invokePutAPIWithPayload() {
		response=Rest.putResource(url,stringReqPayLoad ,jsonContentHeader);
		resJsonContext=JsonPath.parse(response.getBody().asString());
		assertThat(response.getStatusCode()).isEqualTo(200);
		jsonContentHeader=null;
		listHeaders.clear();
	}
	
	@Then("^I invoke the PUT API with json payload and expect HTTP Code \"([^\"]*)\"$")
	public static void invokePutAPIWithPayload(int expectedStatusCode) {
		response=Rest.putResource(url,stringReqPayLoad ,jsonContentHeader);
		resJsonContext=JsonPath.parse(response.getBody().asString());
		assertThat(response.getStatusCode()).isEqualTo(expectedStatusCode);
		jsonContentHeader=null;
		listHeaders.clear();
	}
	
	@Then("^I verify the status code to be \"([^\"]*)\"$")
	public void verifyStatusCode(int expectedStatusCode) {
		int restStatusCode=response.getStatusCode();
		scenario.write("Status Code:"+restStatusCode);
		assertThat(expectedStatusCode).isEqualTo(restStatusCode);
	}
	
	@Then("^I add request header \"([^\"]*)\" as \"([^\"]*)\"$")
	public static void addRequestHeaders(String key,String value) {
		String value1=Utils.analyzeValue(value);
		listHeaders.add(new Header(key,value1));
		jsonContentHeader=new Headers(listHeaders);
		System.out.println(jsonContentHeader);
		
				
	}
	
	@Then("^I count (.*) from \"([^\"]*)\" and save to \"([^\"]*)\"$")
	public void countOccurences(String pattern,String target,String targetCountSave) {
		pattern=Utils.analyzeValue(pattern);
		String strTarget=Utils.analyzeValue(target);
		System.out.println(strTarget);
		Integer strCount=org.apache.commons.lang3.StringUtils.countMatches(strTarget, pattern);
		System.out.println("Count:"+strCount);
		Utils.analyzeValue(strCount+targetCountSave);
	}
	
	@Then("^I print request$")
	public void printFullRequest() {
		scenario.write("Request:"+stringReqPayLoad);
	}
	@Then("^I print response$")
	public void printFullResponse() {
		scenario.write("Response:"+response.getBody().asString());
	}
	
	@Then("^I print from file \"([^\"]*)\"$")
	public void printFullResponse(String propValue) {
		propValue=Utils.analyzeValue(propValue);
		scenario.write("FileValue:"+propValue);
		
	}
	@Then("^I verify request and response$")
	public void verifyRequest() {
		assertThat(stringReqPayLoad).isEqualTo(response.getBody().asString());
	}
	
	@Then("^I invoke the DELETE$")
	public void invokeDelete() {
		response=Rest.deleteResource(url,jsonContentHeader);
		resJsonContext=JsonPath.parse(response.getBody().asString());
		assertThat(200).isEqualTo(response.getStatusCode());
	}
	

	
	@Then("^I save request to \"([^\"]*)\"$")
    public void storeFullRequest(String fileName) {
	   try {
		FileWriter file=new FileWriter("src/test/resources/data/payload"+fileName);
		file.write(stringReqPayLoad.toString());
		file.close();
		
		
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
	}
}
    @Then("^I save response to \"([^\"]*)\"$")
    public void saveFullResponse(String fileKey) {
    	String fileName= fileKey.split("/")[0];
    	String key=fileKey.split("/")[1];
    	Utils.saveData(fileName, key, response.getBody().asString());
    }
    


	@After
    public void afterScenario() {
    	System.out.println("After Scenario");
    	jsonContentHeader=null;
    	listHeaders.clear();
    }
	@Then("^I wait for \"([^\"]*)\" seconds$")
    public void waitForSeconds(int waitTime) throws Exception{
    	Thread.sleep(waitTime);
    }

	@Then("^I invoke the POST API with multipart payload \"([^\"]*)\" and \"([^\"]*)\"$")
	public void invokeAPIwithMultiPartPayload(String json,String wsdl) {
		try {
			response=Rest.postResponseMultiPart(StepDefCore.url, json, wsdl, jsonContentHeader);
			System.out.println(response.getStatusCode());
			assertThat(200).isEqualTo(response.getStatusCode());
			resJsonContext=JsonPath.parse(response.getBody().asString());

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
