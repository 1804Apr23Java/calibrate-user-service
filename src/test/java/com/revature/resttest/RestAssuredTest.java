package com.revature.resttest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.AccountDTO;
import com.revature.exception.InvalidLoginException;
import com.revature.repository.AccountRepository;
import com.revature.service.AccountService;
import com.revature.util.LoginData;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestAssuredTest {

	@Autowired
	AccountService accountService;

	@Autowired
	AccountRepository accountRepository;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@BeforeClass
	public static void setBaseUri() {
		RestAssured.port = 8764;
	}

	@Test
	public void testCorrectStatusCodeIsGivenForGetAllAccounts() {

		Response resp = RestAssured.get("/all");

		int code = resp.getStatusCode();

		assertEquals(code, 200);
	}

	@Test
	public void testAllAccountsAreReturned() {

		RestAssured.get("/all").then().assertThat().body("size()", equalTo(3));
	}

	@Test
	public void testAnAccountIsReturnedById() {

		RestAssured.get("/user/1").then().assertThat().body("accountId", equalTo(1));

	}

	@Test
	public void testEmailIsUpdatedUsingPatch() {

		String email = accountService.getAccount(2).getEmail();

		RestAssured.given().formParam("newEmail", "j@email.com").expect().body(equalTo(
				"{\"accountId\":2,\"firstName\":\"shivam\",\"lastName\":\"A\",\"email\":\"j@email.com\",\"isAdmin\":true,\"password\":\"\",\"isActive\":true}"))
				.when().patch("/email/2");
		accountService.updateEmail(2, email);
	}

	@Test
	public void testFirstNameIsUpdatedUsingPatch() {

		String firstName = accountService.getAccount(1).getFirstName();

		RestAssured.given().formParam("firstName", "kev").expect().body(equalTo(
				"{\"accountId\":1,\"firstName\":\"kev\",\"lastName\":\"M\",\"email\":\"collinmeaney375@gmail.com\",\"isAdmin\":true,\"password\":\"\",\"isActive\":true}"))
				.when().patch("/firstname/1");
		accountService.updateFirstName(1, firstName);
	}

	@Test
	public void testLastNameIsUpdatedUsingPatch() {
		String lastName = accountService.getAccount(1).getLastName();
		RestAssured.given().formParam("lastName", "A").expect().body(equalTo(
				"{\"accountId\":1,\"firstName\":\"collin\",\"lastName\":\"A\",\"email\":\"collinmeaney375@gmail.com\",\"isAdmin\":true,\"password\":\"\",\"isActive\":true}"))
				.when().patch("/lastname/1");
		accountService.updateLastName(1, lastName);
	}

	@Test
	public void testAccountStateIsUpdatedWithPatch() {
		Boolean accountState = accountService.getAccount(1).getIsActive();
		RestAssured.given().formParam("lastName", "A").expect().body(equalTo(
				"{\"accountId\":1,\"firstName\":\"collin\",\"lastName\":\"M\",\"email\":\"collinmeaney375@gmail.com\",\"isAdmin\":true,\"password\":\"\",\"isActive\":false}"))
				.when().patch("/active-state/1");
		accountService.updateAccountState(1);
	}

	@Test
	public void testLoginWithPost() throws JsonProcessingException {

		LoginData loginData2 = new LoginData();
		loginData2.setEmail("collinmeaney375@gmail.com");
		loginData2.setPassword("password");

		ObjectMapper objectMapper = new ObjectMapper();
		String loginDataStr = objectMapper.writeValueAsString(loginData2);

		Response response = RestAssured.given().contentType(ContentType.JSON).with().body(loginDataStr).post("/login");
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

		RestAssured.given().contentType(ContentType.JSON).with().body(loginDataStr).post("/login");

	}

	@Test
	public void testRegisterAccountWithPost() {

		// int accountId, String firstName, String lastName, String email, boolean
		// isAdmin, String password, boolean isActive
		Response response = RestAssured.given().body(new AccountDTO(0, "Dave", "B", "D.email.com", false, "pass", true))
				/*
				 * .formParam("email", "D@email.com") .formParam("password", "pass")
				 * .formParam("firstName", "Dave") .formParam("lastName", "B")
				 */
				.post("/register");

		assertEquals(200, response.getStatusCode());

		accountRepository.delete(4);
	}

}
