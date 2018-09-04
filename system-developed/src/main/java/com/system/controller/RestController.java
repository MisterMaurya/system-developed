package com.system.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

import com.system.dao.impl.ProtocolDAOImpl;
import com.system.dao.impl.TagDAOImpl;
import com.system.dao.impl.UserDAOImpl;
import com.system.entity.Device;
import com.system.entity.Protocol;
import com.system.entity.Tag;
import com.system.entity.User;

@org.springframework.web.bind.annotation.RestController
@ComponentScan(basePackages = { "com.system.dao.impl" })
public class RestController {

	@Autowired
	private ProtocolDAOImpl pro1;

	@Autowired
	private ProtocolDAOImpl pro2;

	@Autowired
	private TagDAOImpl tag;

	@Autowired
	private UserDAOImpl user;

	@RequestMapping("/addDeviceProtocol")
	public Device addProtocolAndDevice() {

		Device device = new Device();
		device.setDescription("Device 1");
		device.setOperator_Id(1);

		Protocol protocol1 = new Protocol();
		protocol1.setTitle("protocol: 3");
		protocol1.setCreated_On(new Date());
		protocol1.setEffectivity_date(new Date());
		protocol1.setDevice(device);

		Protocol protocol2 = new Protocol();
		protocol2.setTitle("protocol: 4");
		protocol2.setCreated_On(new Date());
		protocol2.setEffectivity_date(new Date());
		protocol2.setDevice(device);

		pro1.addProtocol(protocol1);
		pro2.addProtocol(protocol2);

		return device;

	}

	@RequestMapping("/listProtocol")
	public List<Protocol> protocolList() {
		ProtocolDAOImpl list = new ProtocolDAOImpl();
		List<Protocol> getAllProtocols = list.protocolList();
		return getAllProtocols;

	}

	@RequestMapping("/getAllUser")
	public List<User> getAllUser() {
		UserDAOImpl list = new UserDAOImpl();
		List<User> getAllUser = list.gerUserList();
		return getAllUser;
	}

	@RequestMapping(value = "/user/device")
	public User userInteractionWithDevice() {
		Tag tag1 = new Tag("tag1", true, new Date());
		User user1 = new User("Pawan", "abc@gmail.com", tag1);
		user.saveUser(user1);
		return user1;
	}
}
