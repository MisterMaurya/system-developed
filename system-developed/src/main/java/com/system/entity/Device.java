package com.system.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEVICE")
public class Device {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int device_Id;

	private String description;

	public enum status {
		WORKING, IDLE, COMPLETE
	}

	private int operator_Id;
	
	private Protocol protocol;

	public int getDevice_Id() {
		return device_Id;
	}

	public void setDevice_Id(int device_Id) {
		this.device_Id = device_Id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOperator_Id() {
		return operator_Id;
	}

	public void setOperator_Id(int operator_Id) {
		this.operator_Id = operator_Id;
	}

	

}
