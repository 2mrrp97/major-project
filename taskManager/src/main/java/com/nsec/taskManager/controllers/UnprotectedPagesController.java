package com.nsec.taskManager.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UnprotectedPagesController {
	
	@GetMapping(value = "/")
	public ModelAndView getHomePage(Model model) {
		ModelAndView mav = new ModelAndView("index");
		mav.addAllObjects(model.asMap());
		return mav;
	}
}
