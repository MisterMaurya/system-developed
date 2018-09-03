package com.system.dao.impl;

import org.hibernate.Session;

import com.system.dao.TagDAO;
import com.system.entity.Tag;
import com.system.services.DBConnect;

public class TagDAOImpl implements TagDAO {
	DBConnect connect = null;
	Session session = null;

	@Override
	public boolean addTag(Tag tag) {
		connect = new DBConnect();
		session = connect.getSession();
		try {
			session.beginTransaction();
			session.save(tag);
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
