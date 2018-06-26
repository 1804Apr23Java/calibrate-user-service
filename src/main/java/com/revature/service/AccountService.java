package com.revature.service;

import java.util.List;

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
	
	public List<Account> getAllAccounts() {
		
		List<Account> accounts = accountRepository.findAll();
		if (accounts.isEmpty()) {
			return null;
		}
		
		for (Account account: accounts) {
			account.setPassword("");
		}
		
		return accounts;
	}

	public Account addAccount(Account account) {
		Account newAccount = new Account(account.getFirstName(), account.getLastName(), account.getPassword(),
				account.getEmail(), account.getIsAdmin(), account.getIsActive());
		newAccount = accountRepository.save(newAccount);
		return newAccount;
//		Account newAccount = new Account(account);
//		account =  accountRepository.save(account);
//		account.setPassword("");
//		return account;
	}

	
	public Account updateEmail(int id, String email) {
		Account account =  accountRepository.findAccountById(id);
		account.setEmail(email);
		account.setPassword("");
		return accountRepository.save(account);
	}
	
	public Account updateFirstName(int id, String firstName) {
		Account account =  accountRepository.findAccountById(id);
		account.setFirstName(firstName);
		account.setPassword("");
		return accountRepository.save(account);
	}
	
	public Account updateLastName(int id, String lastName) {
		Account account =  accountRepository.findAccountById(id);
		account.setLastName(lastName);
		account.setPassword("");
		return accountRepository.save(account);
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
	
	public Account updateAccountState(int id) {
		
		Account account = accountRepository.findAccountById(id);
		Boolean accountState = account.getIsActive();
		
		if (accountState) {
			account.setIsActive(false);
		} else {
			account.setIsActive(true);
		}
		
		return accountRepository.save(account);
	}
}
