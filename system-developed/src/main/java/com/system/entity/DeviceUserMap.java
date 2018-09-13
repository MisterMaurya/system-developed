package com.system.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "DEVICE_USER_MAP")
public class DeviceUserMap {
	private long id;
	private User user;
	private Device device;

	// additional fields
	private String isActive;
	private Date created_On;

	public DeviceUserMap(){
		
	}
	
	public DeviceUserMap(User user, Device device, String isActive, Date created_On) {
		super();
		this.user = user;
		this.device = device;
		this.isActive = isActive;
		this.created_On = created_On;
	}

	@Id
	@GeneratedValue
	@Column(name = "DEVICE_USER_ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DEVICE_ID")
	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	@Column(name = "IS_ACTIVE")
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Column(name = "CREATED_ON")
	@Temporal(TemporalType.DATE)
	public Date getCreated_On() {
		return created_On;
	}

	public void setCreated_On(Date created_On) {
		this.created_On = created_On;
	}

}
