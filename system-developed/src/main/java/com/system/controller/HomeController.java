package com.system.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.dao.impl.DeviceDAOImpl;
import com.system.dao.impl.DeviceUserMapDAOImpl;
import com.system.dao.impl.ProtocolDAOImpl;
import com.system.dao.impl.TagDAOImpl;
import com.system.dao.impl.UserDAOImpl;
import com.system.entity.Device;
import com.system.entity.DeviceUserMap;
import com.system.entity.Protocol;
import com.system.entity.Tag;
import com.system.entity.User;
import com.system.services.FormatedDate;

@RestController
@ComponentScan(basePackages = { "com.system.dao.impl" })
public class HomeController {

	@Autowired
	private ProtocolDAOImpl pro;

	@Autowired
	private DeviceDAOImpl dev;

	@Autowired
	private TagDAOImpl tags;

	@Autowired
	private UserDAOImpl usr;

	@Autowired
	private DeviceUserMapDAOImpl map;

	//add protocols
	@RequestMapping(value = "/protocols", method = RequestMethod.GET)
	public String addProtocols(@RequestParam Map<String, String> var) throws ParseException {
		boolean check = false;
		String title = var.get("title");
		String created_On = var.get("created");
		String effectivity_date = var.get("effective");
		String device_Id = var.get("devId");

		try {
			check = dev.isDeviceExists(Integer.parseInt(device_Id));
		} catch (Exception e) {
			return "Something went wrong! Please provides valid parameter";
		}
		if (!check) {
			return "Fail to assign Protocol with this device id :" + device_Id;
		}
		Protocol protocol = new Protocol(title, FormatedDate.dateFormat(created_On),
				FormatedDate.dateFormat(effectivity_date), dev.getDevice(Integer.parseInt(device_Id)));

		pro.addProtocol(protocol);
		return " Protocol successfully added in your device";
	}

	//add devices 
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

	//add parent tag
	@RequestMapping(value = "/tags")
	public String addTags(@RequestParam Map<String, String> var) {
		Tag tag = null;
		String description = var.get("desc");
		String is_Active = var.get("status");
		String created_On = var.get("created");
		try {
			tag = new Tag(description, is_Active, FormatedDate.dateFormat(created_On));
		} catch (Exception e) {
			e.printStackTrace();
			return "Something went wrong! Please provides valid parameter";
		}
		tag = tags.addTag(tag);
		tags.updateTagMapping(tag.getMapping_Id(), tag.getTag_Id());
		return "Tag successfully added";
	}

	//insert tag child 
	@RequestMapping(value = "/tags/childstags")
	public String addChildTags(@RequestParam Map<String, String> var) {
		Tag tag = null;
		boolean check = false;
		String description = var.get("desc");
		String is_Active = var.get("status");
		String created_On = var.get("created");
		String tag_Id = var.get("tagId");
		check = tags.isTagsExists(Integer.parseInt(tag_Id));
		if (check) {
			try {
				tag = new Tag(Integer.parseInt(tag_Id), description, is_Active, FormatedDate.dateFormat(created_On));
			} catch (Exception e) {
				e.printStackTrace();
				return "Something went wrong! Please provides valid parameter";
			}
		} else {
			return "Something went wrong! Please provides valid parameter";
		}
		tag = tags.addTag(tag);
		return "child Tag successfully added";
	}

	@RequestMapping(value = "/tags/{tagId}")
	public List<Tag> getTags(@PathVariable("tagId") int tagId) throws Exception {
		return tags.getList(tagId);
	}

	//add user with tag id perform one to one relationship
	@RequestMapping(value = "/users")
	public String addUser(@RequestParam Map<String, String> var) {
		String user_Name = var.get("name");
		String email = var.get("email");
		String tag_Id = var.get("tagId");
		User user = null;
		boolean check = tags.isTagsExists(Integer.parseInt(tag_Id));
		if (!check) {
			return "Please provide a valid tag id";
		}
		user = new User(user_Name, email, tags.getTags(Integer.parseInt(tag_Id)));
		usr.saveUser(user);
		return "User Successfully added";
	}

	//establish a relation between user and devices many to many relationship
	@RequestMapping(value = "/users/relation/devices")
	public String establishRelationship(@RequestParam Map<String, String> var)
			throws NumberFormatException, ParseException {
		String user_Id = var.get("uId");
		String device_Id = var.get("dId");
		String is_Active = var.get("status");
		DeviceUserMap deviceUserMap = new DeviceUserMap(usr.getUser(Integer.parseInt(user_Id)),
				dev.getDevice(Integer.parseInt(device_Id)), is_Active,
				FormatedDate.dateFormat(FormatedDate.currentDateTime()));

		map.saveDeviceUserMap(deviceUserMap);

		return "relationship successfully estabilish";

	}

	
	//getting all user list
	@RequestMapping("/users/list")
	public List<User> getAllUser() {
		UserDAOImpl list = new UserDAOImpl();
		List<User> getAllUser = list.gerUserList();
		return getAllUser;
	}

}
