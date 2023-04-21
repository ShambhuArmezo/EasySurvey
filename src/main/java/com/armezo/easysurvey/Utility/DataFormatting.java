package com.armezo.easysurvey.Utility;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class DataFormatting {
	
	//set time stamp in date
		public static Date addTimeInDate(Date date) {
			Instant date1 = date.toInstant()
					.plus(Duration.ofHours(23).plus(Duration.ofMinutes(59).plus(Duration.ofSeconds(59))));
			Date date2 = Date.from(date1);
			return date2;
		}
		//Date Format in String type
		public static String dateFormatToString(Date date) {
		      SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		      String formattedDate = formatter.format(date);
		      return formattedDate;
		} 

}
