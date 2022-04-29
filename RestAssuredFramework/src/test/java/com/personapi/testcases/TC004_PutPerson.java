package com.personapi.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import com.personapi.base.TestBase;
import com.personapi.utilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_PutPerson extends TestBase {
	RequestSpecification httprequest;
	Response response;
	String fName = RestUtils.personFName();
	String lName = RestUtils.personLName();
	String personAge = RestUtils.personAge();
	String personId = personID;
	String address = RestUtils.personAddress();
	String phoneNumber = RestUtils.personPhoneNumber();

	@SuppressWarnings("unchecked")
	@BeforeClass
	public void getPerson() throws InterruptedException {
		logger.info("***************** Started TC004_PutPerson **************");
		RestAssured.baseURI = "http://localhost:3000/";
		httprequest = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("firstName", fName);
		requestParams.put("lastName", lName);
		requestParams.put("age", personAge);
		requestParams.put("address", address);
		requestParams.put("phoneNumbers", phoneNumber);
		httprequest.header("content-type","application/json");
		httprequest.body(requestParams.toJSONString());
		System.out.print("Person ID is "+personID);
		response = httprequest.request(Method.PUT,"/persons/"+personID);
		}
	@Test
	public void verifyBody() {
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is " +responseBody);
		AssertJUnit.assertEquals(responseBody.contains(fName), true);
		AssertJUnit.assertEquals(responseBody.contains(lName), true);
		AssertJUnit.assertEquals(responseBody.contains(personID), true);
		AssertJUnit.assertEquals(responseBody.contains(personAge), true);
		AssertJUnit.assertEquals(responseBody.contains(address), true);
		AssertJUnit.assertEquals(responseBody.contains(phoneNumber), true);
		//Thread.sleep(5000);
	}
	@Test
	public void checkStatusCode() {
		logger.info("***************** Checking Status Code **************");
		int statusCode = response.getStatusCode();
		logger.info("Response Code is ==> "+statusCode);
		AssertJUnit.assertEquals(statusCode, 200);
	}
	@Test
	public void checkResponseTime() {
		logger.info("***************** Checking Response Time **************");
		long responseTime = response.getTime();
		logger.info("Response Time is  ==> "+responseTime);
		if (responseTime>2000) {
			logger.warn("Response Time is greater than 2000");
			AssertJUnit.assertTrue(responseTime<2000);
		}
	}
	@Test
	public void checkStatusLine() {
		logger.info("***************** Checking Status Line **************");
		String statusLine = response.getStatusLine();
		logger.info("Response Line is ==> "+statusLine);
		AssertJUnit.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	@Test
	public void checkContentType() {
		logger.info("***************** Checking Content Type **************");
		String contentType = response.header("Content-Type");
		logger.info("Content Type is ==> "+contentType);
	}
	@AfterClass
	public void tearDown() {
		logger.info("***************** Finished TC004_PutPerson **************");
}
}