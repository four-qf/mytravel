package com.tour.suse.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetCurrentDate {
	
	public static void main(String [] args)
	{
		System.out.println(GetCurrentDate.getTime());
	}
	public static String getTime() {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = format.format(new Date());
		return dateStr;
	}
}
