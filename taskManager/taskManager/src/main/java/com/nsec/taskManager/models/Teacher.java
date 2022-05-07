package com.nsec.taskManager.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Teacher extends RegisteredUser{
	@ManyToMany(targetEntity = Course.class, 
			cascade = { CascadeType.PERSIST , CascadeType.MERGE , CascadeType.DETACH , CascadeType.REFRESH } , fetch = FetchType.EAGER)
    @JoinTable(
        name = "course_teachers", 
        joinColumns = { @JoinColumn(name = "teacher_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    Set<Course> courses ;
	

	public Teacher() {
		super();
	}

	public Teacher( String name, String emailId, String password, String role, String dob) {
		super(name, emailId, password, role, dob);
		this.courses = new HashSet<>();
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public void addCourse(Course c) {
		if(courses == null)
			courses = new HashSet<>();
		
		courses.add(c);
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", emailId=" + emailId + ", password="
				+ password + ", role=" + role + ", dob=" + dob + "]";
	}
}
