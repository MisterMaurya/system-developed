package com.system.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.system.dao.DeviceUserMapDAO;
import com.system.entity.DeviceUserMap;
import com.system.services.DBConnect;

@Service
public class DeviceUserMapDAOImpl implements DeviceUserMapDAO {
	DBConnect connect = null;
	Session session = null;

	@Override
	public boolean saveDeviceUserMap(DeviceUserMap deviceUserMap) {
		connect = new DBConnect();
		session = connect.getSession();
		try {
			session.beginTransaction();
			session.save(deviceUserMap);
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
