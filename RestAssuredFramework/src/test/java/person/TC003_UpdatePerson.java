package person;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_UpdatePerson {
	@SuppressWarnings("unchecked")
	@Test
	public void updatePerson() {
		RestAssured.baseURI = "http://localhost:3000/";
		RequestSpecification httprequest = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("firstName", "Ahmad");
		requestParams.put("lastName", "Musa");
		requestParams.put("address", "Gulshan e Lahore , Lahore");
		requestParams.put("phoneNumbers", "123-456-789");
		httprequest.header("content-type","application/json");
		httprequest.body(requestParams.toJSONString());
		Response response = httprequest.request(Method.PATCH,"/persons/2");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is " +responseBody);
		int statusCode = response.getStatusCode();
		System.out.println("Statuc Code is " +statusCode);
		AssertJUnit.assertEquals(statusCode, 200);
		String success = response.jsonPath().get("firstName");
		AssertJUnit.assertEquals(success, "Ahmad");
		
		String contentType = response.header("Content-Type");
		System.out.println("Content-Type is " +contentType);
		AssertJUnit.assertEquals(contentType, "application/json; charset=utf-8");
		String vary = response.header("Vary");
		System.out.println("Vary is " +vary);
		AssertJUnit.assertEquals(vary, "Origin, Accept-Encoding");
	}


}
