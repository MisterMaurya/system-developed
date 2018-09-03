package com.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("title", "SYSTEM : DEVELOPED");
		modal.addAttribute("message", "Backend Test");
		return "hello";
	}

}
