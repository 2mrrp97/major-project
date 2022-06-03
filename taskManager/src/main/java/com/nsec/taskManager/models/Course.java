package com.nsec.taskManager.models;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "ongoing_courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	Integer courseId ;
	
	@Column(name = "start_date")
	String startDate ;
	
	@Column(name = "course_name")
	String courseName ;
	
	@Column(name = "end_date")
	String endDate ;
	
	@Column(name = "description")
	String description;
	
	@OneToMany(mappedBy = "course" , targetEntity = Assignment.class , fetch = FetchType.EAGER)
	Set<Assignment> assignments ;
	
	@ManyToMany(targetEntity = Teacher.class , 
			cascade = { CascadeType.PERSIST , CascadeType.MERGE , CascadeType.DETACH , CascadeType.REFRESH }  , fetch = FetchType.EAGER)
    @JoinTable(
        name = "course_teachers", 
        joinColumns = { @JoinColumn(name = "course_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "teacher_id") }
    )
	Set<Teacher> teachers;
	
	@OneToMany(mappedBy = "course" , targetEntity = Student.class , fetch = FetchType.EAGER)
	Set<Student> enrolledStudents ;
	
	public Course() {
		super();
	}

	public Course(String name , String startDate, String endDate, String description) {
		super();
		this.courseName = name ;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public Set<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(Set<Assignment> assignments) {
		this.assignments = assignments;
	}
	public Set<Student> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(Set<Student> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void addStudent(Student s) {
		if(enrolledStudents == null) enrolledStudents = new HashSet<>();
		enrolledStudents.add(s);
	}
	
	public void addAssignment(Assignment a) {
		if(assignments == null)
			assignments = new HashSet<>();
		assignments.add(a);
	}
	public void addTeacher(Teacher t) {
		if(teachers == null)
			teachers = new HashSet<>();
		
		teachers.add(t);
	}

	
	public void removeTeacher(Teacher t) {
		this.teachers.remove(t);
		t.getCourses().remove(this);
	}
	
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", startDate=" + startDate + ", courseName=" + courseName + ", endDate="
				+ endDate + ", description=" + description + ", assignments=" + assignments + ", teachers=" + teachers
				+ ", enrolledStudents=" + enrolledStudents + "]";
	}

}
