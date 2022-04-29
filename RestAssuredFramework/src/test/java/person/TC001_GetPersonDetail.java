package person;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GetPersonDetail {
	
	@Test
	public void getAllPerson() {
		RestAssured.baseURI = "http://localhost:3000/";
		RequestSpecification httprequest = RestAssured.given();
		Response response = httprequest.request(Method.GET,"/persons");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is " +responseBody);
		int statusCode = response.getStatusCode();
		System.out.println("Statuc Code is " +statusCode);
		AssertJUnit.assertEquals(statusCode, 200);
		String statusLine = response.getStatusLine();
		System.out.println("Statuc Line is " +statusLine);
		AssertJUnit.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

}
