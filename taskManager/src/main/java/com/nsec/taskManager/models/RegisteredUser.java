package com.nsec.taskManager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class RegisteredUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id ;
	
	@Column(name = "name" , nullable = false)
	String name ;
	
	@Column(name = "email_id" , nullable = false , updatable = false , unique = true)
	String emailId ; // will be used as login user name
	
	@Column(name = "password"  , nullable = false , columnDefinition="MEDIUMTEXT")
	String password;
	
	@Column(name = "role" , nullable = false , updatable = false)
	String role ;
	
	@Column(name = "dob" , nullable = false)
	String dob;
	
	
	public RegisteredUser() {
		super();
	}

	
	public RegisteredUser(String name, String emailId, String password, String role, String dob) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.password = password;
		this.role = role;
		this.dob = dob;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
}
