package com.nsec.taskManager.models;

import javax.persistence.Entity;

@Entity
public class Admin extends RegisteredUser{
	public Admin(String name, String emailId, String password, String role, String dob) {
		super( name, emailId, password, role, dob);
	}
	
	public Admin() {
		super();
	}
}
