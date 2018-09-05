package com.system.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity //Specifies that the class is an entity
@Table(name = "TAG") //specified for an entity class, and default value apply
public class Tag {

	//Property name refers to name used externally, as the field name in JSON objects.
	@JsonProperty("Tag_Id")
	private int tag_Id;
	@JsonProperty("Description")
	private String description;
	@JsonProperty("Is_Active")
	private boolean is_Active;
	@JsonProperty("Created_On")
	private Date created_On;
	
	// Initializes default constructor
	public Tag() {
		
	}

	//Initializes field constructor
	public Tag(String description, boolean is_Active, Date created_On) {
		super();
		this.description = description;
		this.is_Active = is_Active;
		this.created_On = created_On;
	}

	/*All Getters and Setters method*/

	@Id                                               //Specifies the primary key of an entity
	@GeneratedValue(strategy = GenerationType.AUTO)   //Provides for the specification of generation strategies for the values of primary keys.
	@Column(name = "TAG_ID")                          //Is used to specify the mapped column for a persistent property or field
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

}
