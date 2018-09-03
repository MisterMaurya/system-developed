package com.system.dao.impl;

import org.hibernate.Session;

import com.system.dao.DeviceDAO;
import com.system.entity.Device;
import com.system.services.DBConnect;

public class DeviceDAOImpl implements DeviceDAO {
	DBConnect connect = null;
	Session session = null;

	@Override
	public boolean insertDevice(Device device) {
		connect = new DBConnect();
		session = connect.getSession();
		try {
			session.beginTransaction();
			session.save(device);
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
