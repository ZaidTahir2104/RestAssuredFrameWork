package person;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_ValidateJSONResponse {
	@Test
	public void validateJSONResponse() {
		RestAssured.baseURI = "http://localhost:3000/";
		RequestSpecification httprequest = RestAssured.given();
		Response response = httprequest.request(Method.GET,"/persons");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is " +responseBody);
		AssertJUnit.assertEquals(responseBody.contains("Zaid"), true);
}
}
