package com.upgrade.tests;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.upgrade.base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginService_POST_Request extends BaseClass {

	// call the parent class constructor
	public LoginService_POST_Request() {
		super();
	}

	@Test(dataProvider = "loginrequestdata")
	public void PostloginRequest_ValidCredentials(String username, String password, int statusCodeExpected) {

		// Specify the base URI
		RestAssured.baseURI = prop.getProperty("loginURI");

		// Request object
		RequestSpecification request = RestAssured.given();

		// Request Payload parameters
		JSONObject requestParams = new JSONObject();
		requestParams.put("username", username);
		requestParams.put("password", password);
		requestParams.put("recaptchaToken", "coding_challenge");

		// Request header details
		request.header("x-cf-source-id", "coding-challenge");
		request.header("x-cf-corr-id", "fbb78faa-be39-4d58-9fb4-7433f1a4930b");
		request.header("Content-Type", "application/json");

		// Add the JSON to the body of the request
		request.body(requestParams.toJSONString());

		// Post the request and check the response
		Response response = request.request(Method.POST);

		// Verify the response status code is 200
		int statusCodeActual = response.getStatusCode();
		Assert.assertEquals(statusCodeActual, statusCodeExpected);

		// Response body
		System.out.println("Response body: " + response.body().asString());

		// Validate ProductType in the response. Condition to be extended later for
		// other status codes.
		if (statusCodeActual == 200) {
			JsonPath jsonpath = response.jsonPath();
			String resFirstname = jsonpath.getString("firstName");
			String productTypeActual = jsonpath.getString("loansInReview.productType").replaceAll("\\[", "")
					.replaceAll("\\]", "");
			Assert.assertEquals(productTypeActual, "PERSONAL_LOAN");

		}

	}

	// Input data for above Post call - with valid and Invalid credentials
	@DataProvider(name = "loginrequestdata")
	public Object[][] inputdata()

	{
		return new Object[][] { { "coding.challenge.login@upgrade.com", "On$3XcgsW#9q", 200 },
				{ "invalid.user@upgrade.com", "On$3XcgsW#9q", 401 } };

	}

}
