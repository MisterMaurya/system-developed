package com.system.dao.impl;

import java.sql.PreparedStatement;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
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
	public Device getDevice(int device_Id) {
		connect = new DBConnect();
		session = connect.getSession();
		Device device = null;
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Device> query = builder.createQuery(Device.class);
			Root<Device> root = query.from(Device.class);

			query.select(root).where(builder.equal(root.get("device_Id"), device_Id));
			device = session.createQuery(query).getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			session.close();
		}
		return device;
	}

	@Override
	public boolean isDeviceExists(int device_Id) {
		connect = new DBConnect();
		session = connect.getSession();
		Device device = null;
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Device> query = builder.createQuery(Device.class);
			Root<Device> root = query.from(Device.class);

			query.select(root).where(builder.equal(root.get("device_Id"), device_Id));
			device = session.createQuery(query).getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			return false;

		} finally {
			session.close();
		}
		return true;
	}
}
