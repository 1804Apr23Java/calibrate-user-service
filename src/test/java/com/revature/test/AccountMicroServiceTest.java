package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.beans.Account;
import com.revature.repository.AccountRepository;
import com.revature.service.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AccountMicroServiceTest {
	
	@TestConfiguration
	static class AccountServiceTestContextConfiguration {
		
		@Bean
		public AccountService accountService() {
			return new AccountService();
		}
		
	}
		
	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountRepository accountRepository;
	
//	@MockBean
//	private AccountRepository accountRepository;
	
	@Test 
	public void getOneAccountTest() {
		assertEquals(1, accountService.getAccount(1).getId());
	}
	
	@Test 
	public void getAllAccountsTest() {
		assertEquals(2, accountService.getAllAccounts().size());
	}
	
	@Test 
	public void addAccountTest() {
		Account account = accountService.addAccount(new Account("JJ", "M", "pass", "newEmail@email.com", false, true));
		assertEquals(3, account.getId());
		accountRepository.delete(account.getId());
		assertEquals(2, accountService.getAllAccounts().size()); // making sure table is reset to 3 accounts

	}
	
	@Test 
	public void updateEmailTest() {

		assertEquals("jj@email.com", accountService.updateEmail(1, "jj@email.com").getEmail());
	}
	
	@Test 
	public void updateFirstNameTest() {
		assertEquals("JJ", accountService.updateFirstName(1, "JJ").getFirstName());

	}
	
	@Test 
	public void updateLastNameTest() {
		assertEquals("LastName", accountService.updateLastName(1, "LastName").getLastName());

	}
	
	@Test
	public void testUserCanLogin() {
		assertEquals("shivam.aashir@gmail.com", accountService.login("shivam.aashir@gmail.com", "password").getEmail());	
	}
	
	@Test 
	public void testUserLoginFails() {
		assertNull(accountService.login("shivam.aashir@gmail.com", "notpass"));	
	}
	
	@Test
	public void testAccountStateIsUpdatedFromTrueToFalse() {
		assertEquals(false, accountService.updateAccountState(1).getIsActive());
	}
	
//	@Test
//	public void testAccountStateIsUpdatedFromFalseToTrue() {
//		assertEquals(false, accountService.updateAccountState(2).getIsActive());
//	}
}
