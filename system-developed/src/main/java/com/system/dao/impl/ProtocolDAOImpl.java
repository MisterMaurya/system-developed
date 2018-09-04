package com.system.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.system.dao.ProtocolDAO;
import com.system.entity.Protocol;
import com.system.entity.User;
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

	@Override
	public List<Protocol> protocolList() {
		connect = new DBConnect();
		session = connect.getSession();
		List<Protocol> list = null;
		try {
			session.beginTransaction();
			list = new ArrayList<>();
			list = session.createQuery("From Protocol").list();
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
