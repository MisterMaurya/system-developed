package com.system.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PROTOCOL")
public class Protocol {
	private int protocol_id;
	private String title;
	private Date created_On;
	private Date effectivity_date;
	private Device device;

	@Id
	@GeneratedValue
	@Column(name = "PROTOCOL_ID")
	public int getProtocol_id() {
		return protocol_id;
	}

	public void setProtocol_id(int protocol_id) {
		this.protocol_id = protocol_id;
	}

	@Column(name = "TITLE", nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "CREATED_ON", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreated_On() {
		return created_On;
	}

	public void setCreated_On(Date created_On) {
		this.created_On = created_On;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EFFECTIVITY_DATE", nullable = false)
	public Date getEffectivity_date() {
		return effectivity_date;
	}

	public void setEffectivity_date(Date effectivity_date) {
		this.effectivity_date = effectivity_date;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

}
