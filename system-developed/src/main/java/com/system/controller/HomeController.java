package com.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.system.entity.User;

@Controller
public class HomeController {
	@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("title", "SYSTEM : DEVELOPED");
		modal.addAttribute("message", "Backend Test");
		return "hello";
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute("user") User user,BindingResult result) {
		if(result.hasErrors()) {
			ModelAndView mv = new ModelAndView("userdetails");
			return mv;	
		}else {
		ModelAndView mv = new ModelAndView("userdetails");
		mv.addObject("msg","success");
		return mv;
	}}

}
