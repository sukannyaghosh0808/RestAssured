package GetTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.* ;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GET {
	

   @BeforeClass
   public void setup()
   {
	   RestAssured.baseURI="https://reqres.in/api/users";
   }
  
   @Test(priority=1)
	public void get()
	{
		
		 given()
		 	.queryParams("page", "2")
		 .when()
		 	.get()
		 .then().statusCode(200).log().all().body("page", equalTo(2)).and().
		 body("data.id[0]",equalTo(7));
	}
	
	@Test(priority=2)
	public  void post()
	{
		String userData = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		
		given().body(userData)
		.when().post().then()
		.statusCode(201).log().all();
		
		Response response = RestAssured.given().body(userData).when().post();
		//response code 200 because of get method ?
		int code = response.getStatusCode();
		System.out.println("post method response code  = "+ code);
		
	}

	@Test(priority=3)
	public void delete()
	{
		RestAssured.basePath="2";		
		Response response= RestAssured.given().when().delete();
		System.out.println("delete method response code " +response.getStatusCode());
	}
	
	@Test(priority=4)
	public void put() {
		RestAssured.basePath="2";
		String payload = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
		Response response = RestAssured.given().body(payload).when().put();
		int resCode = response.getStatusCode();
		System.out.println("put method res code " + resCode);
		System.out.println("put response body-------"+"\n" + response.getBody().asString());
	}
}
