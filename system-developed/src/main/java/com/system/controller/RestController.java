package com.system.controller;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

import com.system.dao.impl.DeviceDAOImpl;
import com.system.dao.impl.UserDAOImpl;
import com.system.entity.Device;
import com.system.entity.Protocol;
import com.system.entity.Tag;
import com.system.entity.User;
import com.system.services.FormatedDate;

//this annotation are treated as controllers 
@org.springframework.web.bind.annotation.RestController

@ComponentScan(basePackages = { "com.system.dao.impl" })
public class RestController {

	@Autowired
	private UserDAOImpl users;

	@Autowired
	private DeviceDAOImpl devices;

	@RequestMapping("/getAllUser")
	public List<User> getAllUser() {
		UserDAOImpl list = new UserDAOImpl();
		List<User> getAllUser = list.gerUserList();
		return getAllUser;
	}

	@RequestMapping(value = "/device")
	public Device[] deviceInfo() throws ParseException {
		Set<Protocol> protocols1 = new HashSet<Protocol>();
		protocols1.add(new Protocol("Secure Shell", FormatedDate.dateFormat("02-09-2018"),
				FormatedDate.dateFormat("03-10-2018")));
		protocols1.add(new Protocol("Secure Sockets Layer", FormatedDate.dateFormat("03-09-2018"),
				FormatedDate.dateFormat("04-10-2018")));
		
		Set<Protocol> protocols2 = new HashSet<Protocol>();
		protocols2.add(new Protocol("Simple Mail Transfer Protocol", FormatedDate.dateFormat("04-09-2018"),
				FormatedDate.dateFormat("05-10-2018")));
		
		Device[] device = new Device[2];
		device[0] = new Device("HVAC Professional", "working", 1, protocols1);
        device[1] = new Device("Marketing","completed",2,protocols2);
        
        devices.insertDevice(device[0]);
		devices.insertDevice(device[1]);
		return device;
	}

	@RequestMapping(value = "/user")
	public User userInfo() throws ParseException {
		Set<Device> device = new HashSet<Device>();
		device.add(devices.getDevice(1));
		Tag tag = new Tag("Java", true, FormatedDate.dateFormat("02-09-2018"));
		User user = new User("Tanay", "abc@gmail.com", device, tag);
		users.saveUser(user);
		return user;
	}

	@RequestMapping(value = "/getdevice")
	public void getDeviceInfo() {
		Device device = devices.getDevice(1);
		System.out.println(device.getDescription());
	}

}
