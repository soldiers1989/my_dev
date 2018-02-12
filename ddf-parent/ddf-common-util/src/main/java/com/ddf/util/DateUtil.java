package com.ddf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public final static long ONE_DAY_SECONDS = 86400;
	public final static String format = "yyyyMMddHHmmssSSS";	
	public final static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public final static String yyyy_MM_dd = "yyyy-MM-dd";
	public final static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	
	
	public static Date getCurrentDate(){
		return new Date();
	}
	/**
	 * 计算当前时间几小时之后的时间
	 * 
	 * @param date
	 * @param hours
	 * 
	 * @return
	 */
	public static Date addHours(Date date, long hours) {
		return addMinutes(date, hours * 60);
	}

	/**
	 * 计算当前时间几分钟之后的时间
	 * 
	 * @param date
	 * @param minutes
	 * 
	 * @return
	 */
	public static Date addMinutes(Date date, long minutes) {
		return addSeconds(date, minutes * 60);
	}

	/**
	 * @param date1
	 * @param secs
	 * 
	 * @return
	 */

	public static Date addSeconds(Date date1, long secs) {
		return new Date(date1.getTime() + (secs * 1000));
	}
	/**
	 * 取得新的日期
	 * 
	 * @param date1
	 *            日期
	 * @param days
	 *            天数
	 * 
	 * @return 新的日期
	 */
	public static Date addDays(Date date1, long days) {
		return addSeconds(date1, days * ONE_DAY_SECONDS);
	}
	
	//获取当天0点
	public static Date getTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	//获得当天23:59:59点时间
	public static Date getTimesnight() {
		 Calendar cal = Calendar.getInstance();
		 cal.set(Calendar.HOUR_OF_DAY, 23);
		 cal.set(Calendar.SECOND, 59);
		 cal.set(Calendar.MINUTE, 59);
		 cal.set(Calendar.MILLISECOND, 99);
		 return cal.getTime();
	}
	
	//根据日期或者他的00:00:00
	public static Date getDateBegin(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	//根据日期或者他的23:59:59
	public static Date getDateEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.MILLISECOND, 99);
		return cal.getTime();
	}
	
	/**
	 * 获得时间范围，开始时间为00:00:00结束时间为23:59:59
	 * 
	 * @param date 起始天
	 * @param day 结束天，0为当天（1天），1为今天和明天（两天）
	 * @return
	 */
	public static Date[] getRangeDate(Date date,int day){
		Date bDate = null;getDateBegin(date);
		Date eDate = null;getDateEnd(addDays(date,day));
		if(day<0){
			bDate = getDateBegin(addDays(date,day));
			eDate = getDateEnd(date);
		}else{
			bDate = getDateBegin(date);
			eDate = getDateEnd(addDays(date,day));
		}
		
		
		Date[] dates = new Date[2];
		dates[0] = bDate;
		dates[1] = eDate;
		return dates;
	}
	
	/**
	 * 连个个日期相距多少天
	 * @param smdate
	 * @param edate
	 * @return
	 * @throws Exception
	 */
	public static int daysBetween(Date smdate, Date edate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			smdate = sdf.parse(sdf.format(smdate));
			edate = sdf.parse(sdf.format(edate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(edate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}
	/**
	 * 指定日期的N天后
	 * @param date
	 * @param n
	 * @return
	 * @throws Exception
	 */
	public static String getAfterDayFormat(String date, int n) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(date));
		calendar.add(Calendar.DATE, n);
		return sdf.format(calendar.getTime());
	}
	
	public static Date StringToDate(String strDate){
		SimpleDateFormat sdf = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss); 
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
	public static Date StringToDate(String strDate,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern); 
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String formateDate(Date date,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	/**
	 * 获取当前时间的上个月的最后一天 23:59:59秒
	 * @return
	 */
	public static Date getPrevMonthLastDay(){
        Calendar cale = Calendar.getInstance();   
        cale.set(Calendar.DAY_OF_MONTH,0);
        return getDateEnd(cale.getTime());
	}
	
	
	public static void main(String[] args) {
		/*Date[] dates = getRangeDate(new Date(),30);
		System.out.println(dates[0]+"**********"+dates[1]);*/
		System.out.println(getPrevMonthLastDay());
		//System.out.println(daysBetween(StringToDate("2017-07-01 00:00:00"),StringToDate("2017-07-02 01:00:00")));
		System.out.println(StringToDate("2017-10-26","yyyy-MM-dd"));
	}
	
	
}
