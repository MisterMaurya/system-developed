package com.system.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBConnect {
	public Session getSession() {
		SessionFactory sessionFactory = new Configuration().configure("com.system.services.hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		return session;
	}
}
