package com.system.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.system.dao.ProtocolDAO;
import com.system.entity.Protocol;
import com.system.services.DBConnect;

@Service
public class ProtocolDAOImpl implements ProtocolDAO {
	DBConnect connect = null;
	Session session = null;

	@Override
	public String addProtocol(Protocol protocol) {
		connect = new DBConnect();
		session = connect.getSession();
		try {
			session.beginTransaction();
			session.save(protocol);
			session.getTransaction().commit();
		} catch (Exception e) {

			return "Duplicate Entry/Please provide a valid paramter";

		} finally {
			session.close();
		}
		return "Protocols successfully added in your device";
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

	@Override
	public boolean updateProtocol(int protocolId, int deviceId) {
		connect = new DBConnect();
		session = connect.getSession();
		try {
			session.beginTransaction();
			session.createQuery(
					"UPDATE Protocol set DEVICE_ID = '" + deviceId + "'where PROTOCOLID='" + protocolId + "'")
					.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			
			return false;

		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public Protocol getProtocol(int protocolId) {
		Protocol protocol = null;
		connect = new DBConnect();
		try {
			session = connect.getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Protocol.class);
			criteria.add(Restrictions.eq("protocol_id", protocolId));
			protocol = (Protocol) criteria.uniqueResult();
			if (protocol == null) {
				return null;
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return protocol;
	}

}
