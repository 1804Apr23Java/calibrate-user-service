package com.revature.dto;

import com.revature.beans.Account;

public class AccountDTO {
	
	private int accountId;
	private String firstName;
	private String lastName;
	private String email;
	private boolean isAdmin;
	private String password;
	private boolean isActive;
	
	public AccountDTO(int accountId, String firstName, String lastName, String email, boolean isAdmin, String password, boolean isActive) {
		super();
		this.accountId = accountId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isAdmin = isAdmin;
		this.password = password;
		this.isActive = isActive;
	}
	
	public AccountDTO(Account account) {
		super();
		this.accountId = account.getId();
		this.firstName = account.getFirstName();
		this.lastName = account.getLastName();
		this.email = account.getEmail();
		this.isAdmin = account.getIsAdmin();
		this.password = account.getPassword();
		this.isActive = account.getIsActive();
	}
	
	public AccountDTO() {
		super();
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	

}
