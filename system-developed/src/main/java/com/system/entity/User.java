package com.system.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity // Specifies that the class is an entity
@Table(name = "USER") // specified for an entity class, and default value apply

/*
 * Annotation that can be used to either suppress serialization of properties
 * (during serialization), or ignore processing ofJSON properties read
 * 
 */

public class User {

	/*
	 * Property name refers to name used externally, as the field name in JSON
	 * objects.
	 */
	@JsonProperty("User_Id")
	private int user_Id;

	@JsonProperty("UserName")
	private String user_Name;

	@JsonProperty("Email")
	private String email;

	@JsonProperty("Devices")
	private Set<Device> device = new HashSet<Device>(0);

	@JsonProperty("Tag_id")
	private Tag tag_Id;

	// Initializes default constructor
	public User() {

	}

	// Initializes field constructor
	public User(String user_Name, String email, Set<Device> device, Tag tag_Id) {
		super();
		this.user_Name = user_Name;
		this.email = email;
		this.device = device;
		this.tag_Id = tag_Id;
	}

	/*
	 * All Getters and Setters method
	 */

	@Id // Specifies the primary key of an entity
	@GeneratedValue // Provides for the specification of generation strategies for the values of
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

	@Email(message = "Please enter a valid email")
	@Column(name = "EMAIL", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "DEVICE_USER_MAP", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "DEVICE_ID") })
	public Set<Device> getdevice() {
		return device;
	}

	public void setdevice(Set<Device> device) {
		this.device = device;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Tag_Id")
	public Tag getTag_Id() {
		return tag_Id;
	}

	public void setTag_Id(Tag tag_Id) {
		this.tag_Id = tag_Id;
	}

}
