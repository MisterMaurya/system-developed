package com.system.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity // Specifies that the class is an entity
@Table(name = "USER") // specified for an entity class, and default value apply
public class User {

	/*
	 * Property name refers to name used externally, as the field name in JSON
	 * objects.
	 */
	@JsonProperty("User_Id")
	private int user_Id;

	@JsonProperty("UserName")
	private String user_Name;

	@JsonIgnore // would be ignored and no such property would be output
	@JsonProperty("Email")
	private String email;

	// @JsonProperty("Devices")
	private Set<DeviceUserMap> device = new HashSet<DeviceUserMap>(0);

	@JsonProperty("Tag_id")
	private Tag tag_Id;

	// Initializes default constructor
	public User() {

	}

	// Initializes field constructor
	public User(String user_Name, String email, Tag tag_Id) {
		super();
		this.user_Name = user_Name;
		this.email = email;
		this.tag_Id = tag_Id;
	}

	/*
	 * All Getters and Setters method
	 */

	@Id // Specifies the primary key of an entity
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Provides for the specification of generation strategies for
														// the values of
	// primary keys.
	@Column(name = "USER_ID") // Is used to specify the mapped column for a persistent property or field
	public int getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}

	@Column(name = "USERNAME", nullable = false)
	public String getUser_Name() {
		return user_Name;
	}

	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}

	@JsonIgnore
	@Email(message = "Please enter a valid email")
	@Column(name = "EMAIL", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	public Set<DeviceUserMap> getDevice() {
		return device;
	}

	public void setDevice(Set<DeviceUserMap> device) {
		this.device = device;
	}

	@JsonIgnore
	@JoinColumn(name = "TAG_ID", unique = true)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Tag getTag_Id() {
		return tag_Id;
	}

	public void setTag_Id(Tag tag_Id) {
		this.tag_Id = tag_Id;
	}

}
