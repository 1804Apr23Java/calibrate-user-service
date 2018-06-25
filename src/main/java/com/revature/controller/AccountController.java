package com.revature.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Account;
import com.revature.dto.AccountDTO;
import com.revature.exception.InvalidLoginException;
import com.revature.service.AccountService;
import com.revature.util.LoginData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(value = "Account REST Controller")
public class AccountController {

	@Autowired
	private AccountService accountService;

	
    @ApiOperation(value = "Get user account by Id")
	@GetMapping("/user/{id}")
	public ResponseEntity<AccountDTO> getAccountById(@PathVariable int id) {
		return new ResponseEntity<AccountDTO>(new AccountDTO(accountService.getAccount(id)), HttpStatus.OK);
	}
    
    @ApiOperation(value = "Post email and password")
	@PostMapping("/login")
	public @ResponseBody ResponseEntity<AccountDTO>loginPost(@RequestBody LoginData loginData) {
		
		Account account = accountService.login(loginData.getEmail(), loginData.getPassword());
		if (account == null) {
			throw new InvalidLoginException();
		}
			
		return new ResponseEntity<AccountDTO>(
				new AccountDTO(account), HttpStatus.OK);
	
	}
    
    @ApiOperation(value = "Post new account information")
	@PostMapping("/register")
	public ResponseEntity<AccountDTO> register(@RequestParam String email, @RequestParam String password,
			@RequestParam String firstName, @RequestParam String lastName) {
		
		return new ResponseEntity<AccountDTO>(
				new AccountDTO(accountService.addAccount(new Account(firstName, lastName, password, email, false,
						true))),
				HttpStatus.OK);
	}
    
    @ApiOperation(value = "Update email by id")
	@PatchMapping("/email/{id}")
	public ResponseEntity<AccountDTO> updateEmailById(@PathVariable int id, @RequestParam String newEmail) {
		
		return new ResponseEntity<AccountDTO>(new AccountDTO(accountService.updateEmail(id, newEmail)), HttpStatus.OK);
	}
    
    @ApiOperation(value = "Update first name by id")
	@PatchMapping("/firstname/{id}")
	public ResponseEntity<AccountDTO> updateFirstNameById(@PathVariable int id, @RequestParam String firstName) {
		
		return new ResponseEntity<AccountDTO>(new AccountDTO(accountService.updateFirstName(id, firstName)), HttpStatus.OK);
	}
    
    @ApiOperation(value = "Update last name by id")
	@PatchMapping("/lastname/{id}")
	public ResponseEntity<AccountDTO> updateLastNameById(@PathVariable int id, @RequestParam String lastName) {
		
		return new ResponseEntity<AccountDTO>(new AccountDTO(accountService.updateLastName(id, lastName)), HttpStatus.OK);
	}
	
    @ApiOperation(value = "Get all accounts")
	@GetMapping("/all")
	public ResponseEntity<List<AccountDTO>> getAllAccounts() {
		
		List<Account> allAccounts = accountService.getAllAccounts();
		List<AccountDTO> accounts = new ArrayList<>();
		
		for (Account account: allAccounts) {
			accounts.add(new AccountDTO(account));
		}
		
		return new ResponseEntity<List<AccountDTO>>(accounts, HttpStatus.OK);	
	}
	
}
