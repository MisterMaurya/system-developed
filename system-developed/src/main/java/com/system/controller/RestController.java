package com.system.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.system.dao.impl.UserDAOImpl;
import com.system.entity.User;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@RequestMapping("/home")
	public String home() {
		System.out.println("hello");
		return "hello";
	}
	
	
	@RequestMapping(value = "/getList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getList() {
		UserDAOImpl list = new UserDAOImpl();
		List<User> user_List = list.gerUserList();
		if (user_List == null) {
			System.out.println("Something went wrong");
			return null;
		}
		return user_List;
	}
}
