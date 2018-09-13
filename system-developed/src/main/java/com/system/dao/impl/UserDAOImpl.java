package com.system.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.system.dao.UserDAO;
import com.system.entity.Device;
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

	@Override
	public boolean isUserExists(int user_Id) {
		connect = new DBConnect();
		session = connect.getSession();
		User user = null;
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);

			query.select(root).where(builder.equal(root.get("user_Id"), user_Id));
			user = session.createQuery(query).getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			return false;

		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public User getUser(int User) {
		connect = new DBConnect();
		session = connect.getSession();
		User user = null;
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);

			query.select(root).where(builder.equal(root.get("user_Id"), User));
			user = session.createQuery(query).getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			session.close();
		}
		return user;
	}

}
