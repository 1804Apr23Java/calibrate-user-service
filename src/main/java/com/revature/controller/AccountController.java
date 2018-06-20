package com.revature.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Account;
import com.revature.dto.AccountDTO;
import com.revature.exception.InvalidLoginException;
import com.revature.service.AccountService;
import com.revature.util.LoginData;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@GetMapping("/{id}")
	public ResponseEntity<AccountDTO> getAccount(@PathVariable int id) {
		return new ResponseEntity<AccountDTO>(new AccountDTO(accountService.getAccount(id)), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<AccountDTO>loginPost(@RequestBody LoginData loginData) {
		
		Account account = accountService.login(loginData.getEmail(), loginData.getPassword());
		if (account.equals(null)) {
			throw new InvalidLoginException();
		} else {
			
			return new ResponseEntity<AccountDTO>(
					new AccountDTO(accountService.login(loginData.getEmail(), loginData.getPassword())), HttpStatus.OK);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<AccountDTO> add(@RequestParam String email, @RequestParam String password,
			@RequestParam String firstName, @RequestParam String lastName) {
		
		return new ResponseEntity<AccountDTO>(
				new AccountDTO(accountService.addAccount(new Account(firstName, lastName, password, email, false,
						true))),
				HttpStatus.OK);
	}

	@PostMapping("/email/update")
	public ResponseEntity<AccountDTO> updateEmail(@RequestParam int accountId, @RequestParam String newEmail) {
		return null;
	}
	
	@PostMapping("/firstname/update")
	public ResponseEntity<AccountDTO> updateFirstName(@RequestParam int accountId, @RequestParam String newEmail) {
		return null;
	}
	
	@PostMapping("/lastname/update")
	public ResponseEntity<AccountDTO> updateLastName(@RequestParam int accountId, @RequestParam String newEmail) {
		return null;
	}
	
}
