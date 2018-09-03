package com.system.entity;

import javax.persistence.Entity;

@Entity
public class Device {
	private int device_Id;
	private String description;

	public enum status {
		WORKING, IDLE, COMPLETE
	}

	private int operator_Id;
	private int protocol_Id;

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

	public int getProtocol_Id() {
		return protocol_Id;
	}

	public void setProtocol_Id(int protocol_Id) {
		this.protocol_Id = protocol_Id;
	}

}
