package com.system.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity // Specifies that the class is an entity
@Table(name = "DEVICE") // specified for an entity class, and default value apply
public class Device {

	// Property name refers to name used externally, as the field name in JSON
	// objects.
	@JsonProperty("Device_Id")
	private int device_Id;

	@JsonProperty("Description")
	private String description;

	@JsonProperty("Device_Status")
	private String status;

	@JsonProperty("Operator_Id")
	private int operator_Id;

	 @JsonProperty("Protocols")
	private Set<Protocol> protocol_Id = new HashSet<Protocol>(0);

	// Initializes default constructor
	public Device() {

	}

	// Initializes field constructor
	public Device(String description, String status, int operator_Id, Set<Protocol> protocol_Id) {
		super();
		this.description = description;
		this.status = status;
		this.operator_Id = operator_Id;
		this.protocol_Id = protocol_Id;
	}

	/* All Getters and Setters method */

	@Id // Specifies the primary key of an entity
	@GeneratedValue // Provides for the specification of generation strategies for the values of
					// primary keys.
	@Column(name = "DEVICE_ID", nullable = false) // Is used to specify the mapped column for a persistent property or
													// field
	public int getDevice_Id() {
		return device_Id;
	}

	public void setDevice_Id(int device_Id) {
		this.device_Id = device_Id;
	}

	@Column(name = "DESCRIPTION", nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "STATUS", nullable = false)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "OPERATOR_ID", nullable = false)
	public int getOperator_Id() {
		return operator_Id;
	}

	public void setOperator_Id(int operator_Id) {
		this.operator_Id = operator_Id;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "DEVICE_PROTOCOL", joinColumns = { @JoinColumn(name = "DEVICE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PROTOCOL_ID") })
	public Set<Protocol> getProtocol_Id() {
		return protocol_Id;
	}

	public void setProtocol_Id(Set<Protocol> protocol_Id) {
		this.protocol_Id = protocol_Id;
	}

}
