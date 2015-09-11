package com.example.mynote.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class TimeFormatUtil {

	public TimeFormatUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static String getTime(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String time = format.format(date);
		return time;
	}
	
	public static String getTimeName(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String time = format.format(date);
		return time;
	}

}
