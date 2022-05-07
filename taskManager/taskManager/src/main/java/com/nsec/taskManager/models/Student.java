package com.nsec.taskManager.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Student extends RegisteredUser{
	@ManyToOne(targetEntity = Course.class , fetch = FetchType.EAGER)
	@JoinColumn(name = "course_id" , nullable = true , updatable = false)
	Course course; 
	
	public Student(String name, String emailId, String password, String role, String dob) {
		super(name, emailId, password, role, dob);
		this.course = null ;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
