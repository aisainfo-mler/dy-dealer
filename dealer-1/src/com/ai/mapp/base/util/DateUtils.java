/* 
 * Copyright 2006 AsiaInfo Holdings, Inc
 * All right reserved.
 * Created on Aug 21, 2006
 */
package com.ai.mapp.base.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * 
 */
public class DateUtils {
	/**
	 * 
	 * @return String
	 */
	public static String getDateString() {
		DateFormat dfmt = new SimpleDateFormat("yyyyMMddHHmmss");
		return dfmt.format(new Date());
	}

	public static String getYYYYMMDDHH24MISS(String strYYYY_MM_DD) {
		String tmp = "";
		try {
			Date d = getDate(strYYYY_MM_DD, "yyyy-mm-dd");
			DateFormat dfmt = new SimpleDateFormat("yyyyMMddHHmmss");
			return dfmt.format(d);
		} catch (ParseException e) {
			;
		}

		return tmp;
	}

	/**
	 * 
	 * @return String
	 */
	public static String getDateString(String pattern) {
		DateFormat dfmt = new SimpleDateFormat(pattern);
		return dfmt.format(new Date());
	}

	/**
	 * 
	 * @param days
	 * @param pattern
	 * @return
	 */
	public static String getDateString(int days, String pattern) {
		DateFormat dfmt = new SimpleDateFormat(pattern);
		long days2 = (long) days;
		return dfmt.format(new java.util.Date((new Date()).getTime() + 1000
				* 60 * 60 * 24 * days2));
	}

	// 日期格式
	private static DateFormat vdfmt = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String getDateString(Date date) {
		if (date == null) {
			return "";
		}
		return vdfmt.format(date);
	}

	/**
	 * 
	 * @param date
	 * @param pattirn
	 * @return
	 */
	public static String getDateString(Date date, String pattern) {
		
		SimpleDateFormat sdfmt = new SimpleDateFormat(pattern);
		return date != null ? sdfmt.format(date) : "";
	}
	
	public static boolean isLife(Date expireDate)throws ParseException{
		if(expireDate == null || "".equals(expireDate))return false;
		Date curr = new Date();
		
		if(expireDate instanceof Timestamp){
			expireDate = getDate(expireDate.toString(), "yyyy-MM-dd HH:mm:ss");
		}
		
		if(expireDate.compareTo(curr) > 0){
			return true;
		}else{
			return false;
		}
	}

	public static boolean isLife(String strDate,String pattern) throws ParseException{
		if(strDate == null ||"".equals(strDate)){
			return false;
		}
		Date date = getDate(strDate, pattern);
		return isLife(date);
	}
	/**
	 * 
	 * @param strDate
	 *            String
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getDate(String strDate) throws ParseException {
		// 日期格式
		DateFormat dfmt = new SimpleDateFormat("yyyyMMddHHmmss");
		return dfmt.parse(strDate);
	}

	/**
	 * 
	 * @param strDate
	 *            String
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getDate(String strDate, String pattern)
			throws ParseException {
		// 日期格式
		DateFormat dfmt = new SimpleDateFormat(pattern);
		return dfmt.parse(strDate);
	}

	/**
	 * 
	 * @param strDate
	 *            String
	 * @return Date
	 * @throws ParseException
	 */
	public static String getStringDate(String stringdate) {
		if (stringdate == null)
			return null;

		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat formatter2 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String dateString = "";
		try {
			Date date = formatter1.parse(stringdate);
			dateString = formatter2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateString;
	}

	public static String getStringDate(String stringdate, String fpattern,
			String tpattern) {
		if (stringdate == null)
			return null;

		SimpleDateFormat formatter1 = new SimpleDateFormat(fpattern);
		SimpleDateFormat formatter2 = new SimpleDateFormat(tpattern);
		String dateString = "";
		try {
			Date date = formatter1.parse(stringdate.trim());
			dateString = formatter2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateString;
	}

	public static String getChdate(int month_num) {
		Calendar c1 = Calendar.getInstance();
		String result = "";
		c1.add(2, month_num);
		result = String.valueOf(c1.get(1));
		if ((c1.get(2) + 1) >= 10) {
			result = result + String.valueOf(c1.get(2) + 1);
		} else {
			result = result + "0" + String.valueOf(c1.get(2) + 1);
		}
		return result;
	}
	
	public static String getUSdate(int month_num,String format) {
		Calendar c1 = Calendar.getInstance();
		c1.add(2, month_num);
		SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.ENGLISH);  
		return c1.getTime() != null ? sdf.format(c1.getTime()) : "";
	}

	public static int getSysYear() {
		Calendar calendar = new GregorianCalendar();
		int iyear = calendar.get(Calendar.YEAR);
		return iyear;
	}

	public static int getSysMonth() {
		Calendar calendar = new GregorianCalendar();
		int imonth = calendar.get(Calendar.MONTH) + 1;
		return imonth;
	}

	public static int getSysDay() {
		Calendar calendar = new GregorianCalendar();
		int idate = calendar.get(Calendar.DAY_OF_MONTH);
		return idate;
	}

	public static String getDateString2() {
		String tmp = "";
		tmp = getSysYear() + "    " + getSysMonth() + "    " + getSysDay()
				+ "    ";
		return tmp;
	}

	public static int getTwoMonthNum(String startDate, String endDate) {
		int year1 = Integer.parseInt(startDate.substring(0, 4));
		int year2 = Integer.parseInt(endDate.substring(0, 4));
		int month1 = Integer.parseInt(startDate.substring(4));
		int month2 = Integer.parseInt(endDate.substring(4));
		return Math.abs(year1 - year2) * 12 - (month1 - month2) + 1;
	}

	public static String getNextMonth(String startDate, int i) {
		int start = Integer.parseInt(startDate);
		int next = start + i;
		int year = Integer.parseInt(String.valueOf(next).substring(0, 4));
		int month = Integer.parseInt(String.valueOf(next).substring(4));
		if (month > 12) {
			year = year + 1;
			month = month - 12;
		}
		if (month < 10) {
			return year + "0" + month;
		} else {
			return year + "" + month;
		}
	}

	public static int getDays(String yearMonth) {
		int[] days = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int year = Integer.parseInt(yearMonth.substring(0, 4));
		int month = Integer.parseInt(yearMonth.substring(4)) - 1;
		if (month == 1) {
			if (year % 4 == 0) {
				if (year % 100 == 0) {
					return 28;
				} else {
					return 29;
				}
			} else {
				return 28;
			}
		} else {
			return days[month];
		}
	}

	public static int isBetweenDays(String startDay, String endDay) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String today = formatter.format(date);
		startDay = today.substring(0, 6) + startDay;
		endDay = today.substring(0, 6) + endDay;
		if (today.compareTo(startDay) >= 0 && today.compareTo(endDay) <= 0) {
			return 0;
		} else {
			return 1;
		}
	}

	public static boolean isDate(String dateStr, String dateFomrat) {
		// "yyyy-mm-dd","yyyyMMddHHmmss"
		boolean tmp = false;
		try {
			Date d = getDate(dateStr, dateFomrat);
			DateFormat dfmt = new SimpleDateFormat(dateFomrat);
			dfmt.format(d);
			tmp = true;
		} catch (ParseException e) {
			tmp = false;
		}
		return tmp;
	}

	public static boolean isBetweenDays(String startDay, String endDay,
			String dateFomrat) {
		boolean tmp = false;

		if (isDate(startDay, dateFomrat) && isDate(endDay, dateFomrat)) {
			try {
				if (getDate(startDay, dateFomrat).getTime() > (getDate(endDay,
						dateFomrat)).getTime()) {
					tmp = false;
				} else
					tmp = true;
			} catch (ParseException e) {
				;
			}
		}

		return tmp;
	}

	public static String getYyyyMm(String theDayYyyy_mm_dd) {
		String dayYYYYMMDD = "";
		dayYYYYMMDD = theDayYyyy_mm_dd.replaceAll("-", "");
		return dayYYYYMMDD.substring(0, 6);
	}

	public static boolean isDateStr(String strDate, String pattern) {
		boolean tmp = true;

		try {
			getDate(strDate, pattern);
		} catch (ParseException e) {
			tmp = false;
		}

		return tmp;
	}

	/**
	 */
	public static Date getAfterNDaysDate(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, days);

		return cal.getTime();
	}

	/**
	 */
	public static long DaysBetweenTwoDate(String firstString,
			String secondString) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date firstDate = null;
		Date secondDate = null;
		try {
			firstDate = df.parse(firstString);
			secondDate = df.parse(secondString);
		} catch (Exception e) {
			// 日期型字符串格式错误
		}

		long nDay = ((secondDate.getTime() - firstDate.getTime()) / (60 * 1000));
		return nDay;
	}
	/**
	 * M :yyyyMM
	 * D :yyyyMM
	 * @param pattern
	 * @param date
	 * @param style
	 * @return
	 * @throws ParseException 
	 */
	public static Date changeDateStyle(String pattern, Date date, String style) throws ParseException {
		SimpleDateFormat sdfmt = new SimpleDateFormat(pattern);
		String strDate = sdfmt.format(date);
		if ("M".equals(style)) {
			strDate = strDate + "-01 00:00:00";
		}
		else{
			SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strDate=sdfmt1.format(date);
		}
		DateFormat dfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dfmt.parse(strDate);
	}
	
	public static Date changeDateStyle2(String pattern, Date date, String style) throws ParseException {
		SimpleDateFormat sdfmt = new SimpleDateFormat(pattern);
		String strDate = sdfmt.format(date);
		if ("M".equals(style)) {					
			int day = getDays(getDateString(date, "yyyyMM"));
			strDate = strDate + "-" + String.valueOf(day) + " 23:59:59";
		}
		else{
			SimpleDateFormat sdfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strDate=sdfmt1.format(date);
		}
		DateFormat dfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dfmt.parse(strDate);
	}
	
	//日期从大到小
	public static List<String> getLastSixMonths() {
		List<String> tables = new ArrayList<String>();
		for(int i = -6; i <= -1; i++){
			tables.add(DateUtils.getChdate(i));
		}
		return tables;
	}
	
	//日期从大到小
		public static List<String> getLastSixUSMonths() {
			List<String> tables = new ArrayList<String>();
			for(int i = -6; i <= -1; i++){
				tables.add(DateUtils.getUSdate(i, "MMM,yy"));
			}
			return tables;
		}
	
	//日期从大到小
	public static List<String> getLastTwelveMonths(int startMonth, int lastMonths) {
		List<String> tables = new ArrayList<String>();
		int length = lastMonths + startMonth;
		if(length >= 0){
			for(int i = startMonth; i < length; i++){
				tables.add(DateUtils.getChdate(i));
			}
		}else{
			for(int i = startMonth; i > length; i--){
				tables.add(DateUtils.getChdate(i));
			}
		}
		
		return tables;
	}
	
	public static void main(String[] args) throws Exception{
//		System.out.println("----------------"+DateUtils.getPHPDate(0,"yyyy-MM-dd HH:mm:ss"));
//			System.out.println(changeDateStyle("yyyy-MM", new Date(), "D"));
//			System.out.println(getDateString(new Date(), "yyyyMM"));
//			System.out.println(getChdate(-12));
			List months = getLastSixUSMonths();
			
			Date startTime = DateUtils.getDate("01"+","+months.get(0) + " 00:00:00", "dd,MMM,yy HH:mm:ss");
//			Date endTime = DateUtils.getDate(months.get(months.size() - 1) + DateUtils.getDays(months.get(months.size() - 1)) + " 23:59:59", "yyyyMMdd HH:mm:ss");
			
			
			
			System.out.println(DateUtils.getDateString(startTime));
	}
	
	public static Date getNextDayCurrDay(Date currDate,int i){
    	GregorianCalendar gc = new GregorianCalendar();
    	gc.setTime(currDate);
    	gc.add(GregorianCalendar.DATE, i);
    	return gc.getTime();
    }
      
    //��ñ���һ������  
    public static Date getThisMondayDate(){  
    	Calendar c = Calendar.getInstance();
        c.set(c.DAY_OF_WEEK, Calendar.MONDAY);
        
        Date monday_temp = c.getTime();
        
        Date today = new Date();
        
        if(today.before(monday_temp))
        {
        	 c.add(Calendar.DATE, -7);
        }
        return c.getTime();
    }
    
    public static Date getThisSundayDate()
    {
		Calendar c = Calendar.getInstance();
        c.setTime(DateUtils.getThisMondayDate());
        c.set(c.DAY_OF_WEEK, Calendar.SUNDAY);
        c.add(Calendar.DATE, 7);
        return c.getTime();
    }

    public static String getPHPDate(long d,String pattern)
    {
    	d = d*1000;
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(d);
    }
    
	/**
	 * 加月
	 */
	public static Timestamp addMonth(Timestamp time,int months){
		Calendar calendar = Calendar.getInstance();
		if(time != null){
			calendar.setTimeInMillis(time.getTime());
		}
		calendar.add(Calendar.MONTH, months);  //日期加月
		return new Timestamp(calendar.getTimeInMillis());
	}
	
	/**
	 * 加秒
	 */
	public static Timestamp addSeconds(Timestamp time,int seconds){
		Calendar calendar = Calendar.getInstance();
		if(time != null){
			calendar.setTimeInMillis(time.getTime());
		}
		calendar.add(Calendar.SECOND, seconds);  //日期加月
		return new Timestamp(calendar.getTimeInMillis());
	}
	
	/**
	 * <p>描述: 获得一个月的零晨时间</p> 
	 * @param:        @param month 201001
	 * @param:        @return    20100101 00:00:00
	 * @return:       Timestamp    
	 */
	public static Timestamp getMonthFirstTime(String month){
		month += "01 00:00:00";
		Calendar calendar = string2Calendar(month, "yyyyMMdd HH:mm:ss");
		return calendar2Timestamp(calendar);
	}
	
	/**
	 * <p>描述: 获得一个月的末时间</p> 
	 * @param:        @param month 201001
	 * @param:        @return    20100131 23:59:59
	 * @return:       Timestamp    
	 */
	public static Timestamp getMonthEndTime(String month){
		Calendar calendar = string2Calendar(month, "yyyyMM");
		int maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		month = month + maxDayOfMonth + " 23:59:59";
//		System.out.println(month);
		calendar = string2Calendar(month, "yyyyMMdd HH:mm:ss");
		return calendar2Timestamp(calendar);
	}
	
	/**
	 * <p>描述: 获得一天的零晨时间</p> 
	 * @param:        @param month 20100102
	 * @param:        @return    201001002 00:00:00
	 * @return:       Timestamp    
	 */
	public static Timestamp getDayFirstTime(String day){
		day += " 00:00:00";
		Calendar calendar = string2Calendar(day, "yyyyMMdd HH:mm:ss");
		return calendar2Timestamp(calendar);
	}
	
	/**
	 * <p>描述: 获得一天的末时间</p> 
	 * @param:        @param month 20100102
	 * @param:        @return    20100102 23:59:59
	 * @return:       Timestamp    
	 */
	public static Timestamp getDayEndTime(String day){
		day += " 23:59:59";
//		System.out.println(month);
		Calendar calendar = string2Calendar(day, "yyyyMMdd HH:mm:ss");
		return calendar2Timestamp(calendar);
	}
	
	/**
	 * string-->calendar eg. format:yyyy-MM-dd HH:mm:ss
	 */
	public static Calendar string2Calendar(String time,String format){
		SimpleDateFormat sdf= new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	/**
	 * Calendar-->Timestamp
	 */
	public static Timestamp calendar2Timestamp(Calendar calendar){
		return new Timestamp(calendar.getTimeInMillis());
	}
}
