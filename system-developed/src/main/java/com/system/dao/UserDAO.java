package com.system.dao;

import java.util.List;

import com.system.entity.User;

public interface UserDAO {
	public boolean saveUser(User user);

	public List<User> gerUserList();
}
