package com.system.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.system.dao.UserDAO;
import com.system.entity.User;
import com.system.services.DBConnect;

@Service
public class UserDAOImpl implements UserDAO {
	DBConnect connect = null;
	Session session = null;

	@Override
	public boolean saveUser(User user) {
		connect = new DBConnect();
		session = connect.getSession();
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public List<User> gerUserList() {
		connect = new DBConnect();
		session = connect.getSession();
		List<User> list = null;
		try {
			session.beginTransaction();
			list = new ArrayList<>();
			list = session.createQuery("From User").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return list;
	}

}
