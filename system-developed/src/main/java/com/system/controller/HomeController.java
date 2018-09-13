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
import com.system.dao.impl.ProtocolDAOImpl;
import com.system.dao.impl.TagDAOImpl;
import com.system.entity.Device;
import com.system.entity.Protocol;
import com.system.entity.Tag;
import com.system.services.FormatedDate;

@RestController
@ComponentScan(basePackages = { "com.system.dao.impl" })
public class HomeController {

	@Autowired
	private ProtocolDAOImpl pro;

	@Autowired
	private DeviceDAOImpl dev;

	@Autowired
	TagDAOImpl tags;

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
}
