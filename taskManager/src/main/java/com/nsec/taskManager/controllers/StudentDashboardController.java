package com.nsec.taskManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nsec.taskManager.services.AnswerFileService;

@RestController 
public class StudentDashboardController {
	@Autowired 
	AnswerFileService ansService ;
	
	
}
