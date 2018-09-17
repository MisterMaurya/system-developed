package com.system.controller;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.dao.impl.DeviceDAOImpl;
import com.system.dao.impl.DeviceUserMapDAOImpl;
import com.system.dao.impl.UserDAOImpl;
import com.system.entity.Device;
import com.system.entity.DeviceUserMap;
import com.system.services.FormatedDate;

@RestController
@ComponentScan(basePackages = { "com.system.dao.impl" })
public class DeviceController {

	@Autowired
	private DeviceDAOImpl dev;

	@Autowired
	private UserDAOImpl usr;
	@Autowired
	private DeviceUserMapDAOImpl map;

	// add devices
	@RequestMapping(value = "/devices")
	public String addDevice(@RequestParam Map<String, String> var) throws ParseException {
		Device device = null;
		String description = var.get("desc");
		String status = var.get("status");
		String operator_Id = var.get("opId");
		try {
			device = new Device(description, status, Integer.parseInt(operator_Id));

		} catch (Exception e) {
			return "Something went wrong! Please provides valid parameter";
		}
		dev.insertDevice(device);
		return "Device Successfully added";
	}

	// get a device information
	@RequestMapping("/getDevice/{devid}")
	public Device getDevice(@PathVariable("devid") String devceId) throws NumberFormatException, Exception {
		return dev.getDevice(Integer.parseInt(devceId));
	}

	// establish a relation between user and devices many to many relationship
	@RequestMapping(value = "/users/relation/devices")
	public String establishRelationship(@RequestParam Map<String, String> var) throws Exception {
		String user_Id = var.get("uId");
		String device_Id = var.get("dId");
		String is_Active = var.get("status");

		if (usr.isUserExists(Integer.parseInt(user_Id)) && dev.getDevice(Integer.parseInt(device_Id)) != null) {
			DeviceUserMap deviceUserMap = new DeviceUserMap(usr.getUser(Integer.parseInt(user_Id)),
					dev.getDevice(Integer.parseInt(device_Id)), is_Active,
					FormatedDate.dateFormat(FormatedDate.currentDateTime()));

			return map.saveDeviceUserMap(deviceUserMap);
		}
		return "provide valid parameters";
	}

}
