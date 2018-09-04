package com.system.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

import com.system.dao.impl.ProtocolDAOImpl;
import com.system.dao.impl.UserDAOImpl;
import com.system.entity.Device;
import com.system.entity.Protocol;
import com.system.entity.User;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@RequestMapping("/addDeviceProtocol")
	public Device addProtocolAndDevice() {
		ProtocolDAOImpl addProtocol = new ProtocolDAOImpl();
		Device device = new Device();
		device.setDescription("Device 1");
		device.setOperator_Id(1);

		Protocol protocol1 = new Protocol();
		protocol1.setTitle("protocol: 1");
		protocol1.setCreated_On(new Date());
		protocol1.setEffectivity_date(new Date());
		protocol1.setDevice(device);

		Protocol protocol2 = new Protocol();
		protocol2.setTitle("protocol: 2");
		protocol2.setCreated_On(new Date());
		protocol2.setEffectivity_date(new Date());
		protocol2.setDevice(device);

		addProtocol.addProtocol(protocol1);
		addProtocol.addProtocol(protocol2);

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

}
