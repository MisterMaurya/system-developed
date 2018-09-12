package com.system.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ChildTag extends Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("Tag_Id")

	private int tag_Id;
	@JsonProperty("Description")
	private String description;

	@JsonProperty("Is_Active")
	private String is_Active;

	@JsonProperty("Created_On")
	private Date created_On;

	private int mappingId;

	public int getTag_Id() {
		return tag_Id;
	}

	public void setTag_Id(int tag_Id) {
		this.tag_Id = tag_Id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIs_Active() {
		return is_Active;
	}

	public void setIs_Active(String is_Active) {
		this.is_Active = is_Active;
	}

	public Date getCreated_On() {
		return created_On;
	}

	public void setCreated_On(Date created_On) {
		this.created_On = created_On;
	}

	public int getMappingId() {
		return mappingId;
	}

	public void setMappingId(int mappingId) {
		this.mappingId = mappingId;
	}
}
