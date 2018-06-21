package com.revature.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.service.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@Sql("classpath:populatedb.sql")
public class AccountMicroServiceTest {
	
	@Autowired
	AccountService as;
	
	@Test 
	public void getOneAccountTest() {
//		assertEquals();
	}
	
	@Test 
	public void getAllAccountsTest() {
//		assertEquals();
	}
	
	@Test 
	public void addAccountTest() {
//		assertEquals();
	}
}
