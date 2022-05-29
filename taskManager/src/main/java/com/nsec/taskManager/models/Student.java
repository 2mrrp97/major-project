package com.nsec.taskManager.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Student extends RegisteredUser{
	@ManyToOne(targetEntity = Course.class , fetch = FetchType.EAGER )
	@JoinColumn(name = "course_id" , nullable = true , updatable = false)
	Course course; 
	
	@OneToMany(mappedBy = "student" , targetEntity = Answer.class , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	Set<Answer> uploadedAnswers ;
	
	public Student() {
		super();
	}

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
	
	
	public Set<Answer> getUploadedAnswers() {
		return uploadedAnswers;
	}

	public void setUploadedAnswers(Set<Answer> uploadedAnswers) {
		this.uploadedAnswers = uploadedAnswers;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", emailId=" + emailId + ", role=" + role + ", dob=" + dob + "]";
	}
}
