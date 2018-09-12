package com.system.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

@Service
public class DBConnect {
	//to get a database session 
	public Session getSession() {
		SessionFactory sessionFactory = new Configuration().configure("com/system/services/hibernate.cfg.xml")
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		return session;
	}

	
	public static final String URL = "jdbc:mysql://localhost:3306/backendtest?useSSL=false";
	public static final String USR = "root";
	public static final String PWD = "1000";

	 
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USR, PWD);
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
		return con;
	}

	public static PreparedStatement preparedStatement(String query) {
		PreparedStatement ps = null;
		try {
			ps = DBConnect.getConnection().prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return ps;
	}

}
