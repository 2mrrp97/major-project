package com.nsec.taskManager.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nsec.taskManager.models.Answer;
import com.nsec.taskManager.models.Assignment;
import com.nsec.taskManager.models.Course;
import com.nsec.taskManager.models.Student;
import com.nsec.taskManager.models.Teacher;
import com.nsec.taskManager.repositories.AnswerFileRepo;
import com.nsec.taskManager.repositories.CourseRepo;
import com.nsec.taskManager.repositories.StudentRepo;
import com.nsec.taskManager.services.CourseService;
import com.nsec.taskManager.services.TeacherService;
import com.nsec.taskManager.services.UserService;

@RestController
public class DashboardPagesController {
	@Autowired
	CourseService courseService; 
	@Autowired
	TeacherService teacherService;
	@Autowired
	UserService userService ;

	@Autowired
	CourseRepo courseRepo ;
	@Autowired
	StudentRepo studentRepo ;
	@Autowired
	AnswerFileRepo ansRepo ;
	
	
	@Autowired 
	PasswordEncoder encoder ;
	
	@GetMapping(value = "/dashboard")
	public ModelAndView getDashBoard(Model model , Authentication auth) {
		Collection<? extends GrantedAuthority> grantedAuth = auth.getAuthorities();
		String role = "" ;

		for(GrantedAuthority a : grantedAuth) {
			role = a.getAuthority();
		}
		
		System.out.println("logged role " + role);
		if(role.equals("ROLE_ADMIN")) {
			ModelAndView m = new ModelAndView("admindashboard");
			m.addObject("courses" , courseRepo.findAll());
			return m;
		}
		else if(role.equals("ROLE_TEACHER")) {
			ModelAndView m = new ModelAndView("admindashboard");
			String un = auth.getName();
			Teacher t = teacherService.getByEmail(un);
			
			m.addObject("courses" , t.getCourses());
			return m;
		}
		else if (role.equals("ROLE_STUDENT")) {
			ModelAndView m = new ModelAndView("studentdashboard");
			String un = auth.getName();
			Student s = studentRepo.findByEmailId(un).orElse(null);
			m.addObject("s" , s);
			
			Map<Assignment , Answer> map = new HashMap<>() ; 
			for(Assignment ass : s.getCourse().getAssignments()) {
				map.put(ass, ansRepo.findByStudentAndAssignment(s , ass).orElse(null));
			}
			
			
			m.addObject("assmap" , map);
			System.out.println(map);
			return m;
		}
		
		return new ModelAndView(role.toLowerCase() + "dashboard");
	}
}
