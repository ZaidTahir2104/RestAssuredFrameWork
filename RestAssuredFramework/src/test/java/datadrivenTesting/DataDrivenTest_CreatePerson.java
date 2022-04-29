package datadrivenTesting;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_CreatePerson {
	
	@SuppressWarnings("unchecked")
	@Test(dataProvider="personData")
	void creatEmployee(String fname, String lname, String id, String age, String address, String phoneNumber) {
		RestAssured.baseURI = "http://localhost:3000/";
		RequestSpecification httprequest = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("firstName", fname);
		requestParams.put("lastName", lname);
		requestParams.put("id", id);
		requestParams.put("age", age);
		requestParams.put("address", address);
		requestParams.put("phoneNumbers", phoneNumber);
		httprequest.header("content-type","application/json");
		httprequest.body(requestParams.toJSONString());
		Response response = httprequest.request(Method.POST,"/persons");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is " +responseBody);
		AssertJUnit.assertEquals(responseBody.contains(fname), true);
		AssertJUnit.assertEquals(responseBody.contains(lname), true);
		AssertJUnit.assertEquals(responseBody.contains(id), true);
		AssertJUnit.assertEquals(responseBody.contains(age), true);
		AssertJUnit.assertEquals(responseBody.contains(address), true);
		AssertJUnit.assertEquals(responseBody.contains(phoneNumber), true);
		
		int statusCode = response.getStatusCode();
		System.out.println("Statuc Code is " +statusCode);
		AssertJUnit.assertEquals(statusCode, 201);
	}
	@DataProvider(name="personData")
	String [][] personData() throws IOException{
		String path = System.getProperty("user.dir")+"/src/test/java/datadrivenTesting/PersonData.xlsx";
		
		int rowcount = XLUtils.getRowCount(path, "person");
		int colcount = XLUtils.getCellCount(path, "person", 1);
		String data[][] = new String[rowcount][colcount];
		
		for(int i=1;i<=rowcount;i++) {
			for(int j=0;j<colcount;j++){
				data[i-1][j] = XLUtils.getCellData(path, "person", i, j);
				
			}
		}
//		String data[][] = {{"Zaid","Tahir","1","29","Gulshan e Lahore , Lahore","123-456-789"},
//						   {"Musa","Zaid","2","8","Gulshan e Lahore , Lahore","123-456-789"}};
		return(data);
	}

}
