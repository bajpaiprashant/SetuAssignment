package com.setu.api.core;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import  static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.specification.ResponseSpecification;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


 
public class Rest {

	private static final Logger LOGGER= LogManager.getLogger(Rest.class);
	private static final String JSON_TYPE= "application/json";
	
	
	public static final Response postResource(final String url,final String jsonPayload, Headers jsonContentHeader) {
		//LOGGER.("Invoke the Rest endpoint[{}] with request payload[{}].",url,jsonPayload);
		if(jsonContentHeader!=null) {
			return given().headers(jsonContentHeader).contentType(Rest.JSON_TYPE).body(jsonPayload).when().post(url);
		}
		else {
			return given().contentType(Rest.JSON_TYPE).body(jsonPayload).when().post(url);
		}
		
	}

	public  static String basicAuth(String email , String password){
		Base64 codec = new Base64();
		String authString = email+ ":" +password;
		System.out.println("auth string: " + authString);
		byte[] authEncBytes = codec.encode(authString.getBytes());
		String authStringEnc = new String(authEncBytes);
		return authStringEnc;
	}
	
	public static final Response putResource(final String url,final String jsonPayload, Headers jsonContentHeader) {
		//LOGGER.("Invoke the Rest endpoint[{}] with request payload[{}].",url,jsonPayload);
		if(jsonContentHeader!=null) {
			return given().headers(jsonContentHeader).contentType(Rest.JSON_TYPE).body(jsonPayload).when().post(url);
		}
		else {
			return given().contentType(Rest.JSON_TYPE).body(jsonPayload).when().put(url);
		}
		
	}
	public static final Response getResouce(final String url, Headers jsonContentHeader) {
		if(jsonContentHeader!=null) {
			return given().headers(jsonContentHeader).contentType(Rest.JSON_TYPE).when().get(url);
		}
		else {
			return given().contentType(Rest.JSON_TYPE).when().get(url);
		}
	}

	public static final Response postResponseMultiPart(String url,String jsonFilePath, String ftFilePath, Headers jsonContentHeader) {
		try {
			File ftl=new File("src/test/resources/" + ftFilePath);

			String reqJson=IOUtils.toString(new java.io.FileReader("src/test/resources" + jsonFilePath));
			if(jsonContentHeader!=null) {
				return given().headers(jsonContentHeader).contentType("multipart/form-data")
						.multiPart("request",reqJson,"application/json").multiPart("file",ftl,"file").when().post(url);
			}
			else {
				return given().contentType("multipart/form-data").multiPart("configuration",reqJson,"application/json").multiPart("configuration-ftl",ftl,"file").when().patch(url);

			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}

	public static final Response putResponseMultiPart(String url,String jsonFilePath, String ftFilePath, Headers jsonContentHeader) {
		try {
			String reqJson=IOUtils.toString(new java.io.FileReader("src/test/resources" + jsonFilePath));
			File ftl=new File("/src/test/resources" + ftFilePath);
			if(jsonContentHeader!=null) {
				return given().headers(jsonContentHeader).contentType("multipart/form-data")
						.multiPart("request",reqJson,"application/json").multiPart("file",ftl,"file").when().put(url);
			}
			else {
				return given().contentType("multipart/form-data").multiPart("configuration",reqJson,"application/json").multiPart("configuration-ftl",ftl,"file").when().patch(url);

			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	
  public static final Response deleteResource(String url, Headers jsonContentHeader) {
	return null;
	  
  }
	
}
	