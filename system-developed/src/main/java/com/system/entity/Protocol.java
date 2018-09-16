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

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity // Specifies that the class is an entity
@Table(name = "PROTOCOL") // specified for an entity class, and default value apply
public class Protocol {

	// Property name refers to name used externally, as the field name in JSON
	// objects.
	@JsonProperty("Protocol_Id")
	private int protocol_id;
	@JsonProperty("Title")
	private String title;
	@JsonProperty("Created_On")
	private Date created_On;
	@JsonProperty("Effectivity_Date")
	private Date effectivity_date;

	private Device device;

	// Initializes default constructor
	public Protocol() {

	}

	// Initializes field constructor
	public Protocol(String title, Date created_On, Date effectivity_date, Device device) {
		super();
		this.title = title;
		this.created_On = created_On;
		this.effectivity_date = effectivity_date;
		this.device = device;
	}

	/* All Getters and Setters method */
	@Id // Specifies the primary key of an entity
	@GeneratedValue // Provides for the specification of generation strategies for the values of
					// primary keys.
	@Column(name = "PROTOCOL_ID") // Is used to specify the mapped column for a persistent property or field
	public int getProtocol_id() {
		return protocol_id;
	}

	public void setProtocol_id(int protocol_id) {
		this.protocol_id = protocol_id;
	}

	@Column(name = "TITLE", nullable = false, unique = true)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "CREATED_ON", nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getCreated_On() {
		return created_On;
	}

	public void setCreated_On(Date created_On) {
		this.created_On = created_On;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EFFECTIVITY_DATE", nullable = false)
	public Date getEffectivity_date() {
		return effectivity_date;
	}

	public void setEffectivity_date(Date effectivity_date) {
		this.effectivity_date = effectivity_date;
	}

	@JoinColumn(name = "DEVICE_ID")
	@ManyToOne(cascade = CascadeType.ALL)
	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

}
