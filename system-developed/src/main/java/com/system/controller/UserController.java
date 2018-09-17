package com.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.dao.impl.TagDAOImpl;
import com.system.dao.impl.UserDAOImpl;
import com.system.entity.User;

@RestController
@ComponentScan(basePackages = { "com.system.dao.impl" })
public class UserController {

	@Autowired
	private TagDAOImpl tags;

	@Autowired
	private UserDAOImpl usr;

	// add user with tag id perform one to one relationship
	@RequestMapping(value = "/users")
	public String addUser(@RequestParam Map<String, String> var) {
		String user_Name = var.get("name");
		String email = var.get("email");
		String tag_Id = var.get("tagId");
		User user = null;
		boolean check = tags.isTagsExists(Integer.parseInt(tag_Id));
		if (!check) {
			return "Please provide a valid tag id";
		}
		user = new User(user_Name, email, tags.getTags(Integer.parseInt(tag_Id)));
		usr.saveUser(user);
		return "User Successfully added";
	}

	
	// getting all user list
		@RequestMapping("/users/list")
		public List<User> getAllUser() {
			UserDAOImpl list = new UserDAOImpl();
			List<User> getAllUser = list.gerUserList();
			return getAllUser;
		}
		
		
		
		
}
