package com.nsec.taskManager.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsec.taskManager.models.Course;
import com.nsec.taskManager.repositories.CourseRepo;


@Service
@Transactional
public class CourseService {
	@Autowired
	CourseRepo courseRepo ;
	
	public List<Course> getAllCourses(){
		return courseRepo.findAll();
	}
	
	public Course addNewCourse(Course course) {
		return courseRepo.save(course);
	}
	
	public Course getCourseById(Integer id) throws Exception {
		return courseRepo.findById(id).orElseThrow(() -> new Exception("Course not found with id " + id));
	}
}

