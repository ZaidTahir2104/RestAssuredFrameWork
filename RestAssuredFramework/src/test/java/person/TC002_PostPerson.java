package person;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_PostPerson {
		
		@SuppressWarnings("unchecked")
		@Test
		public void postPerson() {
			RestAssured.baseURI = "http://localhost:3000/";
			RequestSpecification httprequest = RestAssured.given();
			JSONObject requestParams = new JSONObject();
			requestParams.put("firstName", "Musa");
			requestParams.put("lastName", "Zaid");
			requestParams.put("id", 2);
			requestParams.put("age", 26);
			requestParams.put("address", "Gulshan e Lahore , Lahore");
			requestParams.put("phoneNumbers", "123-456-789");
			httprequest.header("content-type","application/json");
			httprequest.body(requestParams.toJSONString());
			Response response = httprequest.request(Method.POST,"/persons");
			String responseBody = response.getBody().asString();
			System.out.println("Response Body is " +responseBody);
			int statusCode = response.getStatusCode();
			System.out.println("Statuc Code is " +statusCode);
			AssertJUnit.assertEquals(statusCode, 201);
			int success = response.jsonPath().get("id");
			AssertJUnit.assertEquals(success, 2);
			
			String contentType = response.header("Content-Type");
			System.out.println("Content-Type is " +contentType);
			AssertJUnit.assertEquals(contentType, "application/json; charset=utf-8");
			String vary = response.header("Vary");
			System.out.println("Vary is " +vary);
			AssertJUnit.assertEquals(vary, "Origin, X-HTTP-Method-Override, Accept-Encoding");
			
		}
}
