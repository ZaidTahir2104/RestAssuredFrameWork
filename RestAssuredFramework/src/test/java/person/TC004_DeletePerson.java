package person;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_DeletePerson {
	
	@Test
	public void deletePerson() {
		RestAssured.baseURI = "http://localhost:3000/";
		RequestSpecification httprequest = RestAssured.given();
		Response response = httprequest.request(Method.DELETE,"/persons/2");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is " +responseBody);
		int statusCode = response.getStatusCode();
		System.out.println("Statuc Code is " +statusCode);
		AssertJUnit.assertEquals(statusCode, 200);
		String contentType = response.header("Content-Type");
		System.out.println("Content-Type is " +contentType);
		AssertJUnit.assertEquals(contentType, "application/json; charset=utf-8");
		String vary = response.header("Vary");
		System.out.println("Vary is " +vary);
		AssertJUnit.assertEquals(vary, "Origin, Accept-Encoding");
		
	}

}
