package com.system.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChildTag extends Tag {

	@JsonProperty("Mapping_Id")
	private int mapping_Id;

	@JsonProperty("Description")
	private String description;

	@JsonProperty("Is_Active")
	private String is_Active;

	@JsonProperty("Created_On")
	private Date created_On;

	

	public ChildTag(int mapping_Id, String description, String is_Active, Date created_On) {
		super(description, is_Active, created_On);
		this.mapping_Id = mapping_Id;

	}

}
