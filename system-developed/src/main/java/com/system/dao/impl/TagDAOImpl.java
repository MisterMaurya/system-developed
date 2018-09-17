package com.system.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.system.dao.TagDAO;
import com.system.entity.Tag;
import com.system.services.DBConnect;
import com.system.services.FormatedDate;

@Service
public class TagDAOImpl implements TagDAO {
	DBConnect connect = null;
	Session session = null;

	@Override
	public Tag addTag(Tag tag) {
		connect = new DBConnect();
		session = connect.getSession();
		try {
			session.beginTransaction();
			session.save(tag);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			session.close();
		}
		return tag;
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

	@Override
	public boolean updateTagMapping(int tag_Id) {
		connect = new DBConnect();
		session = connect.getSession();
		try {
			session.beginTransaction();
			session.createQuery("UPDATE Tag set MAPPING_ID = '" + tag_Id + "'where TAG_ID='" + tag_Id + "'")
					.executeUpdate();
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
	public boolean isTagsExists(int tag_Id) {
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

			return false;

		} finally {
			session.close();
		}
		return true;
	}

//	@SuppressWarnings("static-access")
	@Override
	public List<Tag> getList(int tagId) throws Exception {
		List<Tag> list = null;
		PreparedStatement ps = null;
		if (isTagsExists(tagId)) {
			list = new ArrayList<Tag>();
			list.add(getTags(tagId));
		} else {
			System.out.println("please provide a valid tag id");
		}
		DBConnect connect = new DBConnect();
		ps = connect.preparedStatement("select * from Tag where mapping_Id =?");
		ps.setInt(1, tagId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if (rs.getInt("MAPPING_ID") != rs.getInt("TAG_ID")) {
				Tag tag = new Tag(rs.getInt("TAG_ID"), rs.getInt("MAPPING_ID"), rs.getString("DESCRIPTION"),
						rs.getString("IS_ACTIVE"), FormatedDate.dateFormat(rs.getString("CREATED_ON")));

				list.add(tag);
			}
		}
		return list;
	}

}
