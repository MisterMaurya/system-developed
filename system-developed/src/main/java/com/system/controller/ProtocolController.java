package com.system.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
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
import com.system.entity.Protocol;
import com.system.services.FormatedDate;

@RestController
@ComponentScan(basePackages = { "com.system.dao.impl" })
public class ProtocolController {

	@Autowired
	private ProtocolDAOImpl pro;

	@Autowired
	private DeviceDAOImpl dev;

	// add protocols old API
	@RequestMapping(value = "/protocols", method = RequestMethod.GET)
	public String addProtocols(@RequestParam Map<String, String> var) {
		Protocol protocol = null;
		String title = var.get("title");
		String created_On = var.get("created");
		String effectivity_date = var.get("effective");

		Date date = null;
		try {
			date = FormatedDate.dateFormat(effectivity_date);
		} catch (ParseException e1) {
			return "please enter a valid effective date";
		}
		LocalDate pdate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate now = LocalDate.now();
		Period diff = Period.between(now, pdate);

		if (diff.getDays() < 0) {
			try {
				protocol = new Protocol(title, FormatedDate.dateFormat(created_On),
						FormatedDate.dateFormat(effectivity_date));
			} catch (Exception e) {
				return " Provide a valid parameters ";
			}
			return pro.addProtocol(protocol);
		} else {
			return "check your effective date";
		}
	}

	// assign multiple protocol in a device
	@RequestMapping(value = "/devices/protocol", method = RequestMethod.GET)
	public String assignProtocol(@RequestParam("protocolId") int[] protocolId, @RequestParam("deviceId") int deviceId)
			throws Exception {

		if (dev.getDevice(deviceId) != null) {
			for (int i : protocolId) {
				if (pro.getProtocol(i) == null) {
					return "Protocol not exists with this id : " + i;
				}
				pro.updateProtocol(i, deviceId);
			}
			return "add";
		} else {
			return "device not found with this device id";
		}

	}

	// get Protocol information
	@RequestMapping("/protocol/{protocoId}")
	public Protocol getProtocol(@PathVariable("protocoId") String protocoId) throws NumberFormatException, Exception {
		return pro.getProtocol(Integer.parseInt(protocoId));
	}

}
