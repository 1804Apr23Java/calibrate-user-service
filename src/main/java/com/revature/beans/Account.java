package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Account {
		
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountSequence")
	@SequenceGenerator(allocationSize = 1, name = "accountSequence", sequenceName = "SQ_USER_PK")
	@Column(name = "ACCOUNT_ID")
	private int id;	
	
	@Column(name = "FIRSTNAME", nullable = false)
	private String firstName;
	
	@Column(name = "LASTNAME", nullable = false)
	private String lastName;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;
	
	@Column(name = "ISADMIN", nullable = false)
	private boolean isAdmin;
	
	@Column(name = "ISACTIVE", nullable = false)
	private boolean isActive;
	

	public Account(int id, String firstName, String lastName, String password, String email, boolean isAdmin,
			boolean isActive) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.isAdmin = isAdmin;
		this.isActive = isActive;
	}
	
	

	public Account(String firstName, String lastName, String password, String email, boolean isAdmin,
			boolean isActive) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;		
		this.password = password;
		this.email = email;
		this.isAdmin = isAdmin;
		this.isActive = isActive;
	}



	public Account() {
		super();
	}


	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
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
	
	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", email=" + email + ", isAdmin=" + isAdmin + ", isActive=" + isActive + "]";
	}

}
