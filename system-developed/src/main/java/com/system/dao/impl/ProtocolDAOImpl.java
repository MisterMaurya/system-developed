package com.system.dao.impl;

import org.hibernate.Session;

import com.system.dao.ProtocolDAO;
import com.system.entity.Protocol;
import com.system.services.DBConnect;

public class ProtocolDAOImpl implements ProtocolDAO {
	DBConnect connect = null;
	Session session = null;
	@Override
	public boolean addProtocol(Protocol protocol) {
		connect = new DBConnect();
		session = connect.getSession();
		try {
			session.beginTransaction();
			session.save(protocol);
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
