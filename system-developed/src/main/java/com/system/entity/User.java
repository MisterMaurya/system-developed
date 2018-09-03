package com.system.entity;

import javax.persistence.Entity;

@Entity
public class User {
	private int user_Id;
	private String user_Name;
	private String email;
	private int device_Id;
	private int Tag_Id;

	public int getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}

	public String getUser_Name() {
		return user_Name;
	}

	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDevice_Id() {
		return device_Id;
	}

	public void setDevice_Id(int device_Id) {
		this.device_Id = device_Id;
	}

	public int getTag_Id() {
		return Tag_Id;
	}

	public void setTag_Id(int tag_Id) {
		Tag_Id = tag_Id;
	}

}
