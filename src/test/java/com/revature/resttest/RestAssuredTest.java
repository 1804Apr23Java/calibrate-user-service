package com.revature.resttest;

import org.junit.Test;

import com.revature.dto.AccountDTO;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredTest {
	
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
					.body("size()", equalTo(3));
	}
	
	@Test
	public void testAnAccountIsReturnedById() {
		
		RestAssured.get("/user/1")
					.then()
					.assertThat()
					.body("accountId", equalTo(1));
		
	}
	
//	@Test
//	public void testEmailIsUpdatedUsingPatch() {
//		
//		 RestAssured.given()
//										.pathParam("id", 2)
//										.formParam("newEmail", "j@email.com")
//										.expect()
//										.body(equalTo("{\"accountId\": 2,\"firstName\": \"shivam\",\"lastName\": \"A\",\"email\": \"shivam.aashir@gmail.com\",\"isAdmin\": true,\"password\": \"\",\"isActive\": true}"))
//										.when()
//								is		.patch("/email/2");
//		
//		assertEquals(200, response.getStatusCode());
//		
//	}
	
	
}
