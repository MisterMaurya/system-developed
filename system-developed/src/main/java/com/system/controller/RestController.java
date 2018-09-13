package com.system.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.system.dao.impl.DeviceDAOImpl;
import com.system.dao.impl.TagDAOImpl;
import com.system.dao.impl.UserDAOImpl;
import com.system.entity.Device;
import com.system.entity.Protocol;
import com.system.entity.Tag;
import com.system.entity.User;
import com.system.services.DBConnect;
import com.system.services.FormatedDate;

//this annotation are treated as controllers 
@org.springframework.web.bind.annotation.RestController

@ComponentScan(basePackages = { "com.system.dao.impl" })
public class RestController {

	@Autowired // Marks a constructor, field, setter method or config method as to be autowired
				// by Spring's dependency injection facilities
	private UserDAOImpl users;

	@Autowired
	private DeviceDAOImpl devices;

	@Autowired
	private TagDAOImpl tags;

	// instantiate object of DBConnect
	private DBConnect connect = null;

	// API for getting All the user
	@RequestMapping("/getAllUser")
	public List<User> getAllUser() {
		UserDAOImpl list = new UserDAOImpl();
		List<User> getAllUser = list.gerUserList();
		return getAllUser;
	}

	// API for getting a tag details by tag_Id
	/*@SuppressWarnings("static-access")
	@RequestMapping("/getTags/{tag_Id}")
	public List<Tag> getTagDetails(@PathVariable("tag_Id") int tagId) {
		List<Tag> list = new ArrayList<Tag>();

		connect = new DBConnect();
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		Tag tag = new Tag();
		int mappingId = 0;
		try {
			ps1 = connect.preparedStatement("SELECT * FROM TAG WHERE TAG_ID = ?");
			ps1.setInt(1, tagId);

			ResultSet rs1 = ps1.executeQuery();
			if (rs1.next()) {
				mappingId = tag.getMappingId();
			}
			ps2 = connect.preparedStatement("SELECT * FROM TAG WHERE MAPPING_ID = ?");
			ps2.setInt(1, mappingId);
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				Tag tags = new Tag(rs2.getString("DESCRIPTION"), rs2.getString("IS_ACTIVE"),
						FormatedDate.dateFormat(rs2.getString("CREATED_ON")));
				list.add(tags);
			}

		} catch (Exception e) {
			System.out.println("Enter a valid tag id");
		}

		return list;
	}*/

	// API for adding a device and protocol details
	/*@RequestMapping(value = "/device")
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
		device[1] = new Device("Marketing", "completed", 2, protocols2);

		devices.insertDevice(device[0]);
		devices.insertDevice(device[1]);
		return device;
	}*/

	// API for adding a user details
	/*@RequestMapping(value = "/user")
	public User userInfo() throws ParseException {
		Set<Device> device = new HashSet<Device>();
		device.add(devices.getDevice(6));
		Tag tag = new Tag("Java", "Y", FormatedDate.dateFormat("02-09-2018"));
		User user = new User("Tanay", "abc@gmail.com", device, tag);
		users.saveUser(user);
		return user;
	}*/

	// API for getting a device details
	@RequestMapping(value = "/getdevice")
	public void getDeviceInfo() {
		Device device = devices.getDevice(1);
		System.out.println(device.getDescription());
	}

	// API for establish relations between device and user
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/relation/{deviceId}/{userId}")
	public void deviceUserMap(@PathVariable("deviceId") int deviceId, @PathVariable("userId") int userId) {

		connect = new DBConnect();
		boolean u_id = false;
		boolean d_id = false;
		try {
			u_id = users.isUserExists(userId); // check user is exists or not, by user_Id
			d_id = devices.isDeviceExists(deviceId);// check device is exists or not, by device_Id
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(
					"we can't establish relation between device and user. /nProvide valid values for user_id and device_id");
		}
		System.out.println(u_id + " " + d_id);

		if (u_id == true && d_id == true) {
			String created_On = FormatedDate.currentDateTime(); // to get current time. when establish a relation
																// between device and user
			String isActive = "Y"; // status is active or not
			PreparedStatement ps = null;
			ps = connect.preparedStatement("insert into mapping values(?,?,?,?)");
			try {
				ps.setInt(1, deviceId);
				ps.setInt(2, userId);
				ps.setString(3, isActive);
				ps.setString(4, created_On);
				ps.executeUpdate();
				System.out.println("Relation successfully estabish!");

			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("Someting went wrong");
			}
		} else {
			System.out.println(
					"we can't establish relation between device and user. /nProvide valid values for user_id and device_id");
		}
	}
}
