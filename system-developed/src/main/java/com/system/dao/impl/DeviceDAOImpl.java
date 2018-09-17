package com.system.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.system.dao.DeviceDAO;
import com.system.entity.Device;
import com.system.services.DBConnect;

@Service
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

	@Override
	public Device getDevice(int device_Id) throws Exception {
		Device device = null;
		connect = new DBConnect();
		try {
			session = connect.getSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Device.class);
			criteria.add(Restrictions.eq("device_Id", device_Id));
			device = (Device) criteria.uniqueResult();
			if (device == null) {
				return null;
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
		return device;
	}
}
