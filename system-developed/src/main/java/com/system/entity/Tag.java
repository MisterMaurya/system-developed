package com.system.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TAG")
public class Tag {

	private int tag_Id;
	private String description;
	private boolean is_Active;
	private Date created_On;
	private User user;

	public Tag() {
	}

	public Tag(String description, boolean is_Active, Date created_On) {
		super();
		this.description = description;
		this.is_Active = is_Active;
		this.created_On = created_On;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TAG_ID")
	public int getTag_Id() {
		return tag_Id;
	}

	public void setTag_Id(int tag_Id) {
		this.tag_Id = tag_Id;
	}

	@Column(name = "DESCRIPTION", nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "IS_ACTIVE", nullable = false)
	public boolean isIs_Active() {
		return is_Active;
	}

	public void setIs_Active(boolean is_Active) {
		this.is_Active = is_Active;
	}

	@Column(name = "CREATED_ON", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreated_On() {
		return created_On;
	}

	public void setCreated_On(Date created_On) {
		this.created_On = created_On;
	}

	@OneToOne(targetEntity = User.class)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
