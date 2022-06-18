package com.nsec.taskManager.controllers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nsec.taskManager.models.Admin;
import com.nsec.taskManager.models.Answer;
import com.nsec.taskManager.models.Assignment;
import com.nsec.taskManager.models.Course;
import com.nsec.taskManager.models.Teacher;
import com.nsec.taskManager.models.RegisteredUser;
import com.nsec.taskManager.models.Remark;
import com.nsec.taskManager.models.Student;
import com.nsec.taskManager.repositories.CourseRepo;
import com.nsec.taskManager.repositories.RegisteredUserRepo;
import com.nsec.taskManager.repositories.RemarkRepo;
import com.nsec.taskManager.repositories.StudentRepo;
import com.nsec.taskManager.repositories.TeacherRepo;
import com.nsec.taskManager.repositories.AnswerFileRepo;
import com.nsec.taskManager.services.AnswerFileService;
import com.nsec.taskManager.services.CourseService;
import com.nsec.taskManager.services.TeacherService;
import com.nsec.taskManager.services.UserService;

@RestController
public class UnprotectedPagesController implements ErrorController{
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
	RemarkRepo remarkRepo ;
	
	
	@Autowired 
	PasswordEncoder encoder ;
	

	@GetMapping(value = "/accessDenied")
	public ModelAndView accessDenied() {
		return new ModelAndView("errorView/403");
	}
	
	@PostMapping(value = "/remarks") 
	public ModelAndView addRemark(HttpServletRequest req , @RequestParam(name = "studentId" , required = true) Integer studentId){
		ModelAndView mv = new ModelAndView("redirect:/studentdetails/" + studentId);
		String content = req.getParameter("remark");
		String ansId = req.getParameter("ansId");
		
		Answer ans = ansRepo.getById(ansId);
		Remark r = new Remark(content);
		remarkRepo.save(r);
		ans.setRemark(r);
		ansRepo.save(ans);
		return mv;
	}
	
	@GetMapping(value = "/resetpwd")
	public ModelAndView showResetPassword(Model model) {
		ModelAndView mv = new ModelAndView("resetpassword");
		mv.addAllObjects(model.asMap());
		return mv;
	}
	
	@PostMapping(value = "/resetpwd")
	public ModelAndView handleResetPassword(HttpServletRequest req, Authentication auth , RedirectAttributes rda) throws Exception {
		String oldpwd = req.getParameter("oldPwd");
		String newpwd = req.getParameter("newPwd");
		String un = auth.getName();
		
		try {
			RegisteredUser u = userService.getUserByEmailId(un);
			String savedpwd = u.getPassword();
			if(encoder.matches(oldpwd , savedpwd)) {
				u.setPassword(newpwd);
				userService.addNewUser(u);
				req.logout();
				return new ModelAndView("redirect:/");
			}
			else {
				System.out.println("here");
				ModelAndView mv = new ModelAndView("redirect:/resetpwd");
				rda.addFlashAttribute("errorMssg" , "Old Password does not Match");
				return mv;
			}
		}
		catch (Exception ex) {
			
		}
		
		System.out.println("op " + oldpwd + " np " + newpwd + " pr " + un);
		return new ModelAndView("resetpassword");
	}
	
	
	@GetMapping(value = "/login")
	public ModelAndView getLogin(Authentication auth) {
		if(auth != null)
			return new ModelAndView("redirect:/dashboard");
		
		return new ModelAndView("plain-login");
	}
	
	@GetMapping(value = "/")
	public ModelAndView getHomePage(Authentication auth) {
		if(auth != null)
			return new ModelAndView("redirect:/dashboard");
		
		return new ModelAndView("home");
	}
	
	
	
	
	@RequestMapping(value = "/error" , method = RequestMethod.GET)
	public ModelAndView handleError(HttpServletRequest request) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    ModelAndView m = new ModelAndView("errorView/errorPage");
	    
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	        m.addObject("errorCode" , statusCode);
	    }
	    
	    return m;
	}
	
	
	@PostMapping(value = "/addteacher") 
	public ModelAndView addTeacher(@ModelAttribute("teacher") Teacher t , RedirectAttributes rda ){
		// check if teacher email exists then cancel add, and show same form page with error 
		t.setPassword("1");
		t.setRole("ROLE_TEACHER");
		System.out.println("from here teacehr " + t.toString());
		try { 
			teacherService.addNewTeacher(t);
		}
		catch (Exception ex) {
			if(ex instanceof DataIntegrityViolationException) {
				System.out.println("error" + ex.getMessage() + " ex " + ex.getClass());
				
				rda.addFlashAttribute("errorMssg" , "Email Id Already in Use");
				ModelAndView m = new ModelAndView("redirect:/addteacher");
				
				return m;
			}
		}
		
		
		
		return new ModelAndView("redirect:/dashboard");
	}
	
	@GetMapping(value = "/addteacher") 
	public ModelAndView showAddTeacherForm(Model model){
		ModelAndView mv = new ModelAndView("addteacherform");
		mv.addObject("teacher" , new Teacher());
		mv.addAllObjects(model.asMap());
		return mv;
	}
	
	@GetMapping(value = "/addcourse") 
	public ModelAndView showAddCourseForm(Model model){
		model.addAttribute("course" , new Course());
		List<Teacher> teachers = teacherService.all();
		model.addAttribute("teachers" , teachers);
		return new ModelAndView("addcourseform");
	}
	
	@GetMapping(value = "/addstudent") 
	public ModelAndView showAddStudentForm(@RequestParam(name = "cid" , required = true) String id , Model model){
		ModelAndView mv = new ModelAndView("addstudentform");
		mv.addObject("student" , new Student());
		mv.addObject("courseId" , id);
		return mv;
	}
	
	@PostMapping(value = "/addstudent") 
	public ModelAndView addStudent(@RequestParam(name = "cid" , required = true) String courseId , @ModelAttribute("student") Student s,
			RedirectAttributes rda){
		System.out.println("cid " + courseId + "st " + s);
		
		try { 
			Course c = courseRepo.getById(Integer.parseInt(courseId));
			s.setPassword(encoder.encode("1234"));
			s.setRole("ROLE_STUDENT");
			s.setCourse(c);
			s.setDob(parseDate(s.getDob()));
			studentRepo.save(s);
		}
		catch (Exception ex) {
			if(ex instanceof DataIntegrityViolationException) {
				System.out.println("error" + ex.getMessage() + " ex " + ex.getClass());
				rda.addFlashAttribute("errorMssg" , "Email already in use");
				
			}
			else {
				rda.addFlashAttribute("errorMssg" , ex.getMessage());
			}
			ModelAndView m = new ModelAndView("redirect:/addstudent?cid="+courseId);
			return m;
		}
		
		return new ModelAndView("redirect: coursedetails/"+courseId);
	}
	
	private String parseDate(String date) {
		String d [] = date.split("\\-");
		String m [] = new String[] {"Jan" , "Feb" , "Mar" , "Apr" , "May" , "Jun" , "July" , "Aug" , "Sep" , "Oct" , "Nov" , "Dec"}; 
		String month = m[Integer.parseInt(d[1]) - 1];
		return String.join("-", d[2] , month , d[0]);
	}
	
	
	@PostMapping(value = "/addcourse") 
	public ModelAndView addCourse(HttpServletRequest req){
		String name = req.getParameter("courseName");
		String sd = req.getParameter("startDate");
		String ed = req.getParameter("endDate");
		String dsc = req.getParameter("description");
		String [] teachers = req.getParameterValues("teachers[]");
		
		Course c = new Course(name , sd , ed , dsc);
		Course addedC = courseRepo.save(c);
		if(teachers != null) {
			for(String id : teachers) {
				Integer tid = Integer.parseInt(id);
				Teacher t = teacherService.getById(tid);
				addedC.addTeacher(t);
			}
		}
		
		courseRepo.save(addedC);
		
		return new ModelAndView("redirect:/dashboard");
	}
	
	
	@GetMapping(value = "studentdetails/{sid}")
	public ModelAndView showstudentdetails(@PathVariable(name = "sid" , required = true) Integer sid) {
		ModelAndView m = new ModelAndView("studentdetails");
		Student s = studentRepo.findById(sid).orElse(null);
		System.out.println("found student " + s);
		m.addObject("s", s);
		
		TreeMap<Assignment , Answer> map = new TreeMap<>((a , b) -> {
			return a.getUploadedAt().before(b.getUploadedAt()) ? -1 : 1;
		}) ; 
		
		for(Assignment ass : s.getCourse().getAssignments()) {
			map.put(ass, ansRepo.findByStudentAndAssignment(s , ass).orElse(null));
		}
		
		m.addObject("assmap" , map);
		System.out.println(map);
		return m;
	}
	
	// /rmvs/sid/${s.id}/cid/${c.courseId}

	
	@GetMapping(value = "rmvs/sid/{sid}/cid/{cid}")
	public ModelAndView rmvStudent(@PathVariable(name = "sid" , required = true) Integer sid , @PathVariable(name = "cid" , required = true) Integer cid) {
		ModelAndView m = new ModelAndView("cdet");
		Student s = studentRepo.findById(sid).orElse(null);
		studentRepo.delete(s);
		return new ModelAndView("redirect:/coursedetails/"+cid);
	}
}
