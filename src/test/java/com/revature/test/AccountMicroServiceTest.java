package com.revature.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.beans.Account;
import com.revature.service.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AccountMicroServiceTest {
		
	@Autowired
	AccountService as;
	
//	@Test 
//	public void getOneAccountTest() {
//		assertEquals(1, as.getAccount(1).getId());
//	}
	
//	@Test 
//	public void getAllAccountsTest() {
//		assertEquals(2, as.getAllAccounts().size());
//	}
	
//	@Test 
//	public void addAccountTest() {
//		Account account = new Account("JJ", "M", "pass", "j@email.com", false,
//				true);
//		int newId = as.addAccount(account).getId();
//		System.out.println("***************** " + newId + " ***************** ");
//		assertEquals(3, newId);
//	}
	
//	@Test 
//	public void updateEmailTest() {
//
//		assertEquals("j@email.com", as.updateEmail(1, "j@email.com").getEmail());
//	}
	
//	@Test 
//	public void updateFirstNameTest() {
//		assertEquals("JJ", as.updateFirstName(1, "JJ").getFirstName());
//
//	}
	
//	@Test 
//	public void updateLastNameTest() {
//		assertEquals("LastName", as.updateLastName(1, "LastName").getLastName());
//
//	}
	
//	@Test
//	public void testUserCanLogin() {
//		assertEquals("collinmeaney375@gmail.com", as.login("collinmeaney375@gmail.com", "password").getEmail());	
//	}
	
//	@Test 
//	public void testUserLoginFails() {
//		assertEquals(null, as.login("collinmeaney375@gmail.com", "notpass"));	
//	}
}
