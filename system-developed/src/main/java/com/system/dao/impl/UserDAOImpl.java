package com.system.dao.impl;

import org.hibernate.Session;

import com.system.dao.UserDAO;
import com.system.entity.User;
import com.system.services.DBConnect;

public class UserDAOImpl implements UserDAO{
	DBConnect connect = null;
	Session session = null;
	@Override
	public boolean saveUser(User user) {
		connect = new DBConnect();
		session = connect.getSession();try {
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

}
