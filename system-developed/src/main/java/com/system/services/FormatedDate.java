package com.system.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatedDate {

	public static Date dateFormat(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date date1 = format.parse(date);
		return date1;
	}
	public static String currentDateTime() {
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	Date date = new Date();
	return dateFormat.format(date);
	}
}
