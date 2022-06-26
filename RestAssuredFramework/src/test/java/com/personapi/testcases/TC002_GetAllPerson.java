package com.personapi.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import com.personapi.base.TestBase;
import com.personapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

public class TC002_GetAllPerson extends TestBase {
	
	
	@BeforeClass
	public void getAllPerson() throws InterruptedException {
		logger.info("***************** Started TC002_GetPersonDetail **************");
		RestAssured.baseURI = RestUtils.urlBase();
		RequestSpecification httprequest = RestAssured.given();
		response = httprequest.request(Method.GET,"/persons");
		//Thread.sleep(5000);
		}
	@Test
	public void checkResponseBody(){
		logger.info("***************** Checking Response Body **************");
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==> "+responseBody);
		AssertJUnit.assertTrue(responseBody!=null);
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
		logger.info("***************** Finished TC002_GetPersonDetail **************");
	}
}
