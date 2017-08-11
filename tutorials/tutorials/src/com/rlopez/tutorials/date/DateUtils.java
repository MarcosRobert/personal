/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rlopez.tutorials.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cliente
 */
public class DateUtils {
	
	public static void main(String args[]){
		System.out.println("**************** Test add one day to current date **************");
		Date d = new Date();
		System.out.println("Current date: " + d);
		d = DateUtils.addOneDay(d);
		System.out.println("After add a date: " + d);
		
		System.out.println("**************** Test Subtract  **************");
		String sd1 = "12-03-2017";
		String sd2 = "03-04-2017";
		Date d1 = DateUtils.toDate("dd-MM-yyyy", sd1);
		Date d2 = DateUtils.toDate("dd-MM-yyyy", sd2);
		System.out.println("Subtract " + sd1 + " to " + sd2);
		int fields[] = DateUtils.subtract(d2, d1);
		System.out.println("The diff of date is"
				  +" Years:" + fields[0]
				  + " Month:" + fields[1]
				  + " Days:" + fields[2]);
		
	}
	
	public static Date toDate(String format, String date){
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			return dateFormat.parse(date);
		} catch (ParseException ex) {
			Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
	
	public static int[] subtract(Date sub, Date min){
		
		Calendar subCalendar = Calendar.getInstance();
		Calendar minCalendar = Calendar.getInstance();
		subCalendar.setTime(sub);
		minCalendar.setTime(min);
		//year
		int year = subCalendar.get(Calendar.YEAR) - minCalendar.get(Calendar.YEAR);
		//month start with 0
		int month = (subCalendar.get(Calendar.MONTH) - minCalendar.get(Calendar.MONTH)) + 1;
		//day
		int day = subCalendar.get(Calendar.DAY_OF_MONTH) - minCalendar.get(Calendar.DAY_OF_MONTH);
		int fields[] = new int[3];
		fields[0] = year;
		fields[1] = month;
		fields[2] = day;
		return fields;
	}
	
	/**
	 * Add one day and return the result as new instance
	 * @param date
	 * @return 
	 */
	public static Date addOneDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//add one day
		calendar.roll(Calendar.DAY_OF_MONTH, true);
		return calendar.getTime();
	}
	
	
}
