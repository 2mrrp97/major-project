package com.nsec.taskManager.controllers;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nsec.taskManager.models.Admin;
import com.nsec.taskManager.models.Course;
import com.nsec.taskManager.models.Teacher;
import com.nsec.taskManager.models.RegisteredUser;
import com.nsec.taskManager.repositories.CourseRepo;
import com.nsec.taskManager.repositories.RegisteredUserRepo;
import com.nsec.taskManager.repositories.TeacherRepo;
import com.nsec.taskManager.services.CourseService;
import com.nsec.taskManager.services.TeacherService;
import com.nsec.taskManager.services.UserService;

@RestController
public class UnprotectedPagesController {
	@Autowired
	CourseService courseService; 
	@Autowired
	TeacherService teacherService;
	@Autowired
	UserService userService ;

	@Autowired
	CourseRepo courseRepo ;
	
	@GetMapping(value = "/login")
	public ModelAndView getLogin() {
		return new ModelAndView("plain-login");
	}
	
	@GetMapping(value = "/")
	public ModelAndView getHomePage(Model model) {
		return new ModelAndView("home");
	}
	
	@GetMapping(value = "/dashboard")
	public ModelAndView getDashBoard(Model model , Authentication auth) {
		Collection<? extends GrantedAuthority> grantedAuth = auth.getAuthorities();
		String role = "" ;
		for(GrantedAuthority a : grantedAuth) {
			role = a.getAuthority();
		}
		
		System.out.println("logged role " + role);
		if(role.equals("ADMIN")) {
			ModelAndView m = new ModelAndView(role.toLowerCase() + "dashboard");
			m.addObject("courses" , courseRepo.findAll());
			return m;
		}
		return new ModelAndView(role.toLowerCase() + "dashboard");
	}
	
	@GetMapping(value = "courses")
	public ResponseEntity<?> getCourses() {
		List<Course> li = courseService.getAllCourses();
		return new ResponseEntity<List<Course>>(li , HttpStatus.OK);
	}
	
	@GetMapping(value = "coursedetails/{id}")
	public ModelAndView getcd(@PathVariable(name = "id" , required = true) String id) {
		ModelAndView m = new ModelAndView("cdet");
		Course c = courseRepo.findById(Integer.parseInt(id)).orElse(null);
		Set <Teacher> assignedt = c.getTeachers();
		
		if(c != null) {
			c.setTeachers(assignedt);
		}
		
		List<Teacher> t = teacherService.all().stream().filter(x -> {
			return !assignedt.contains(x);
		}).collect(Collectors.toList());
		
		m.addObject("c", c);
		m.addObject("avlt", t);
		return m ;
	}
	
	@PostMapping(value = "coursedetails/{id}")
	public ModelAndView updateteachers(@PathVariable(name = "id" , required = true) String id , HttpServletRequest req) {
		Course c = courseRepo.findById(Integer.parseInt(id)).orElse(null);
		String teacherIds[] = req.getParameterValues("addTeachersIds[]");
		Set<Teacher> assignedTeachers = c.getTeachers();
		
		for(String tid : teacherIds) {
			Teacher t = teacherService.getById(Integer.parseInt(tid));
			assignedTeachers.add(t);
		}
		
		courseRepo.save(c);
		return new ModelAndView("redirect:/coursedetails/" + id);
	}
	
	
	@PostMapping(value = "/addteacher") 
	public ResponseEntity<?> addteacher(@ModelAttribute("teacher") Teacher t){
		
		t.setPassword("1234");
		t.setRole("TEACHER");
		
		Teacher addedt = teacherService.addNewTeacher(t);
		
		System.out.println(t.toString());
		
		return new ResponseEntity<Teacher>(addedt, HttpStatus.OK);
	}
	
	@GetMapping(value = "/addteacher") 
	public ModelAndView showaddteacherform(Model model){
		model.addAttribute("teacher" , new Teacher());
		return new ModelAndView("addteacherform");
	}
	
	@GetMapping(value = "/addcourse") 
	public ModelAndView showaddcourseform(Model model){
		model.addAttribute("course" , new Course());
		List<Teacher> teachers = teacherService.all();
		model.addAttribute("teachers" , teachers);
		return new ModelAndView("addcourseform");
	}
	
	@PostMapping(value = "/addcourse") 
	public String addc(HttpServletRequest req){
		String sd = req.getParameter("startDate");
		String ed = req.getParameter("endDate");
		String dsc = req.getParameter("description");
		String [] teachers = req.getParameterValues("teachers[]");
		
		Course c = new Course(sd , ed , dsc);
		Course addedC = courseRepo.save(c);
		for(String id : teachers) {
			Integer tid = Integer.parseInt(id);
			Teacher t = teacherService.getById(tid);
			addedC.addTeacher(t);
		}
		
		courseRepo.save(addedC);
		
		return sd + " end d " + ed + " dsc " + dsc + " teachers " + Arrays.toString(teachers);
	}
	
	@GetMapping(value = "rmvt/tid/{tid}/cid/{cid}")
	public ModelAndView rmvteacher(@PathVariable(name = "tid" , required = true) Integer tid , @PathVariable(name = "cid" , required = true) Integer cid) {
		ModelAndView m = new ModelAndView("cdet");
		Teacher t = teacherService.getById(tid);
		Course c = courseRepo.findById(cid).orElse(null);
		Set <Teacher> assignedt = c.getTeachers();
		assignedt.remove(t);
		courseRepo.save(c);
		
		return new ModelAndView("redirect:/coursedetails/"+cid);
	}
}
