package com.revature.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Sql("classpath:populatedb.sql")
public class AccountMicroServiceTest {
	
	
	
	@Test 
	public void firstPlayerScoreTest() {
//		assertEquals();
	}
}
