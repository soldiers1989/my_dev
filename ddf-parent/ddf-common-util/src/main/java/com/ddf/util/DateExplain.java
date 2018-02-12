package com.ddf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateExplain {
//	private static final long ONE_MINUTE = 60000L;
	private static final long ONE_HOUR = 3600000L;
//	private static final long ONE_DAY = 86400000L;
//	private static final long ONE_WEEK = 604800000L;

//	private static final String ONE_SECOND_AGO = "秒前";
//	private static final String ONE_MINUTE_AGO = "分钟前";
//	private static final String ONE_HOUR_AGO = "小时前";
	private static final String ONE_DAY_AGO = "天前";
//	private static final String ONE_MONTH_AGO = "月前";
//	private static final String ONE_YEAR_AGO = "年前";

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse("2017-11-02 10:40:00)");
		System.out.println(format_HH_mm(date));
	}
	
	public static String format_HH_mm_ss(Date date) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
		String time = f.format(date);
		String str = format(date)+"	"+time;
		return str;
	}
	
	public static String format_HH_mm(Date date){
		SimpleDateFormat f = new SimpleDateFormat("HH:mm");
		String time = f.format(date);
		String str = format(date)+"	"+time;
		return str;
	}

	public static String format(Date date){
//		long delta = new Date().getTime() - date.getTime();
//		if (delta < 1L * ONE_MINUTE) {
//			long seconds = toSeconds(delta);
//			return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
//		}
//		if (delta < 45L * ONE_MINUTE) {
//			long minutes = toMinutes(delta);
//			return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
//		}
//		if (delta < 24L * ONE_HOUR) {
//			long hours = toHours(delta);
//			return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
//		}
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1=format1.format(new Date())+" 23:59:59";
		long delta2 = 0;
		try {
			delta2 = format2.parse(date1).getTime() - date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (delta2 < 24L * ONE_HOUR) {
			return "今天";
		}else if (delta2 < 48L * ONE_HOUR) {
			return "昨天";
		}else{
			long days = toDays(delta2);
			return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
		}
//		if (delta2 < 30L * ONE_DAY) {
//			long days = toDays(delta2);
//			return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
//		}
//		if (delta2 < 12L * 4L * ONE_WEEK) {
//			long months = toMonths(delta2);
//			return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
//		} else {
//			long years = toYears(delta2);
//			return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
//		}
	}

	private static long toSeconds(long date) {
		return date / 1000L;
	}

	private static long toMinutes(long date) {
		return toSeconds(date) / 60L;
	}

	private static long toHours(long date) {
		return toMinutes(date) / 60L;
	}

	private static long toDays(long date) {
		return toHours(date) / 24L;
	}

	private static long toMonths(long date) {
		return toDays(date) / 30L;
	}

	private static long toYears(long date) {
		return toMonths(date) / 365L;
	}
}
