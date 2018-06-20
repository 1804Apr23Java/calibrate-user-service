package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Account;
import com.revature.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;

	public Account getAccount(int id) {
		Account account = accountRepository.findAccountById(id);
		account.setPassword("");
		return account;
	}

	public Account addAccount(Account account) {
		account =  accountRepository.save(account);
		account.setPassword("");
		return account;
	}

	
	public Account updateEmail(int id, String email) {
		Account account =  accountRepository.findAccountById(id);
		account.setEmail(email);
		account.setPassword("");
		return account;
	}

	public Account login(String email, String password) {
		Account account = accountRepository.findAccountByEmail(email);
		if (account.equals(null)) {
			return null;
		}
		
		if (account.getPassword().equals(password)) {
			account.setPassword("");
			return account;
		} else {
			return null;
		}		
	}
}
