package com.system.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.system.dao.TagDAO;
import com.system.entity.Tag;
import com.system.services.DBConnect;

@Service
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

	@Override
	public Tag getTags(int tag_Id) {
		connect = new DBConnect();
		session = connect.getSession();
		Tag tag = null;
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
			Root<Tag> root = query.from(Tag.class);

			query.select(root).where(builder.equal(root.get("tag_Id"), tag_Id));
			tag = session.createQuery(query).getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			session.close();
		}
		return tag;
	}

}
