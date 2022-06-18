package com.nsec.taskManager.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nsec.taskManager.models.Course;
import com.nsec.taskManager.models.Student;
import com.nsec.taskManager.models.Teacher;
import com.nsec.taskManager.repositories.CourseRepo;
import com.nsec.taskManager.services.TeacherService;

@RestController
public class CourseDetailsPageController {
	@Autowired
	CourseRepo courseRepo ;
	@Autowired
	TeacherService teacherService;
	
	@GetMapping(value = "coursedetails/{id}")
	public ModelAndView getCoursedetailsPage(@PathVariable(name = "id" , required = true) String id , Model model) {
		ModelAndView m = new ModelAndView("cdet");
		Course c = courseRepo.findById(Integer.parseInt(id)).orElse(null);
		Set <Teacher> assignedt = c.getTeachers();
		Set<Student> enrolledStudents = c.getEnrolledStudents();
		
		if(c != null) {
			c.setTeachers(assignedt);
			c.setEnrolledStudents(enrolledStudents);
			c.setAssignments(c.getAssignments());
		}
		
		List<Teacher> t = teacherService.all().stream().filter(x -> {
			return !assignedt.contains(x);
		}).collect(Collectors.toList());
		
		m.addObject("c", c);
		m.addObject("avlt", t);
		m.addAllObjects(model.asMap());
		
		return m ;
	}
	
	@PostMapping(value = "coursedetails/{id}")
	public ModelAndView updateEnrolledTeachers(@PathVariable(name = "id" , required = true) String id , HttpServletRequest req) {
		Course c = courseRepo.findById(Integer.parseInt(id)).orElse(null);
		String teacherIds[] = req.getParameterValues("addTeachersIds[]");
		Set<Teacher> assignedTeachers = c.getTeachers();
		
		if(teacherIds != null) {
			for(String tid : teacherIds) {
				Teacher t = teacherService.getById(Integer.parseInt(tid));
				assignedTeachers.add(t);
			}
		}
		
		courseRepo.save(c);
		return new ModelAndView("redirect:/coursedetails/" + id);
	}

	@GetMapping(value = "rmvt/tid/{tid}/cid/{cid}")
	public ModelAndView rmvteacher(@PathVariable(name = "tid" , required = true) Integer tid , @PathVariable(name = "cid" , required = true) Integer cid) {
		Teacher t = teacherService.getById(tid);
		Course c = courseRepo.findById(cid).orElse(null);
		Set <Teacher> assignedTeachers = c.getTeachers();
		assignedTeachers.remove(t);
		courseRepo.save(c);
		
		return new ModelAndView("redirect:/coursedetails/"+cid);
	}
	
	
	@GetMapping(value = "delete/teacher/tid/{tid}/cid/{cid}")
	public ModelAndView deleteTeacher(@PathVariable(name = "tid" , required = true) Integer tid , @PathVariable(name = "cid" , required = true) Integer cid) {
		Teacher t = teacherService.getById(tid);
		Set<Course> enrolledCourses = t.getCourses();
		for(Course c : enrolledCourses) {
			t.removeCourse(c);
		}
		teacherService.delete(t);
		
		return new ModelAndView("redirect:/coursedetails/"+cid);
	}
}
