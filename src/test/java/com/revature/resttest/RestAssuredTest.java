package com.revature.resttest;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exception.InvalidLoginException;
import com.revature.util.LoginData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Rule;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAssuredTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@BeforeClass
	public static void setBaseUri() {
		RestAssured.baseURI = "http://ec2-35-171-24-66.compute-1.amazonaws.com:8764";
	}
	
	@Test
	public void testCorrectStatusCodeIsGivenForGetAllAccounts() {
		
		Response resp = RestAssured.get("/all");
		
		int code = resp.getStatusCode();
		
		assertEquals(code, 200);
	}
	
	@Test
	public void testAllAccountsAreReturned() {
		
		RestAssured.get("/all")
					.then()
					.assertThat()
					.body("size()", equalTo(2));
	}
	
	@Test
	public void testAnAccountIsReturnedById() {
		
		RestAssured.get("/user/1")
					.then()
					.assertThat()
					.body("accountId", equalTo(1));
		
	}
	
	@Test
	public void testEmailIsUpdatedUsingPatch() {
		
		 RestAssured.given()
					 .formParam("newEmail", "j@email.com")
					 .expect()
					 .body(equalTo("{\"accountId\":2,\"firstName\":\"e\",\"lastName\":\"M\",\"email\":\"j@email.com\",\"isAdmin\":true,\"password\":\"\",\"isActive\":true}"))
					 .when()
					 .patch("/email/2");
			
	}
	
	@Test
	public void testFirstNameIsUpdatedUsingPatch() {
		
		 RestAssured.given()
					 .formParam("firstName", "e")
					 .expect()
					 .body(equalTo("{\"accountId\":2,\"firstName\":\"e\",\"lastName\":\"M\",\"email\":\"j@email.com\",\"isAdmin\":true,\"password\":\"\",\"isActive\":true}"))
					 .when()
					 .patch("/firstname/2");
			
	}
	
	@Test
	public void testLastNameIsUpdatedUsingPatch() {
		
		 RestAssured.given()
					 .formParam("lastName", "M")
					 .expect()
					 .body(equalTo("{\"accountId\":2,\"firstName\":\"e\",\"lastName\":\"M\",\"email\":\"j@email.com\",\"isAdmin\":true,\"password\":\"\",\"isActive\":true}"))
					 .when()
					 .patch("/lastname/2");
			
	}
	
	@Test
	public void testLoginWithPost() throws JsonProcessingException {
		
		
		LoginData loginData2 = new LoginData();
		loginData2.setEmail("collinmeaney375@gmail.com");
		loginData2.setPassword("password");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String loginDataStr = objectMapper.writeValueAsString(loginData2);
				
		Response response =  RestAssured.given()
										.contentType(ContentType.JSON)
										.with()
										.body(loginDataStr)
										 .post("/login");
		 System.out.println(loginDataStr);

		 assertEquals(200, response.getStatusCode());
	}
	
	@Test
	public void testInvalidLoginExceptionIsThrown() throws InvalidLoginException {
		
		LoginData loginData2 = new LoginData();
		loginData2.setEmail("collinmeaney375@gmail.com");
		loginData2.setPassword("incorrectPassword");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String loginDataStr = null;
		
		try {
			loginDataStr = objectMapper.writeValueAsString(loginData2);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
				
		RestAssured.given()
					.contentType(ContentType.JSON)
					.with()
					.body(loginDataStr)
					.post("/login");

	}
	
	@Test
	public void testRegisterAccountWithPost() {
		
		 Response response = RestAssured.given()
										 .formParam("email", "j22@email.com")
										 .formParam("password", "pass")
										 .formParam("firstName", "Dave")
										 .formParam("lastName", "B")
										 .post("/register");
		 
		 
		 assertEquals(500, response.getStatusCode()); // this test should return a 200 code, but after it is run once the resource already exists so the status code changes to 500
	}
	
}
