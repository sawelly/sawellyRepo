package com.sawelly.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期、时间常用方法类.<p>
 * 
 * @author 阿海
 * 
 */
public class DateTime {

	public static String getDate() {
		return getDate(0);
	}
	/**
	 * 得到N天后的日期
	 * @param num
	 * @return
	 */
	public static String getDate(int num) {
		long time = System.currentTimeMillis() + (1000L * 60 * 60 * 24 * num);
		return getDate(time);
	}

	/**
	 * 根据long值得到日期
	 * @param time
	 * @return
	 */
	public static String getDate(long time) {
		String pattern = "yyyy-MM-dd";
		Date date = new Date();
		if (time > 0) {
			date.setTime(time);
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 得到当前时间
	 * @return
	 */
	public static String getTime() {

		return getTime(0);
	}

	/**
	 * 根据time值得到时间
	 * @param time
	 * @return
	 */
	public static String getTime(long time) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		Date date = new Date();
		if (time > 0) {
			date.setTime(time);
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);

		return format.format(date);
	}

	/**
	 * 根据time值得到全数字格式的时间
	 * @param time
	 * @return
	 */
	public static String getNumTime(long time) {
		String pattern = "yyyyMMddHHmmssS";
		Date date = new Date();
		if (time > 0) {
			date.setTime(time);
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);

		return format.format(date);
	}

	/**
	 * 得到星期几
	 * @return
	 */
	public static String getWeekName() {
		String[] names = { "天", "一", "二", "三", "四", "五", "六" };
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return names[day];
	}
	/**
	 * 得到指定日期是星期几
	 * @return
	 */
	public static Integer getWeekName(String date) {
		 date = date.trim();
		String[] tmpdate = date.split("-");
		int year = Integer.parseInt(tmpdate[0]);
		int month = Integer.parseInt(tmpdate[1]);
		int day = Integer.parseInt(tmpdate[2]);
		Integer[] names = { 0,1,2,3,4,5,6 };
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(year, month - 1, day, 0, 0, 0);
		int weekday = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return names[weekday];
	}
	/**
	 * 判断字符串是否为正确的日期格式
	 * 
	 * @param str
	 * @return 是否合法日期格式
	 */
	public static boolean isDate(String str) {
		String regex = "^[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2}$";
		return str.matches(regex);
	}

	/**
	 * 判断字符串是否为正确的时间格式
	 * 
	 * @param str
	 * @return 是否合法时间格式
	 */
	public static boolean isTime(String str) {
		String regex = "^[0-9]{2}:[0-9]{1,2}:[0-9]{1,2}$";
		return str.matches(regex);
	}

	/**
	 * 判断字符串是否为正确的日期 + 时间格式
	 * 
	 * @param str
	 * @return 是否合法日期 + 时间格式
	 */
	public static boolean isDateTime(String str) {
		String regex = "^[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2} [0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2}$";
		return str.matches(regex);
	}

	public static long getSecond(String time) {
		long milli = getMilliSecond(time);
		return milli / 1000;
	}

	public static long getMilliSecond(String time) {
		long ret = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			java.util.Date date = sdf.parse(time);
			ret = date.getTime();
		}
		catch (Exception e) {
			return 0;
		}
		return ret;
	}

	/**
	 * 得到时间的long值
	 * @param datetime
	 * @return
	 */
	public static long getLongTime(String datetime) {
		datetime = datetime.trim();
		String date = datetime.substring(0, datetime.indexOf(" "));
		String[] tmpdate = date.split("-");
		int year = Integer.parseInt(tmpdate[0]);
		int month = Integer.parseInt(tmpdate[1]);
		int day = Integer.parseInt(tmpdate[2]);
		String time = datetime.substring(datetime.indexOf(" ")).trim();
		String[] tmptime = time.split(":");
		int hour = Integer.parseInt(tmptime[0]);
		int minute = Integer.parseInt(tmptime[1]);
		if(tmptime[2]==null ){
			tmptime[2] = "0";
		}else if(tmptime[2].length()>2){
			tmptime[2] = tmptime[2].substring(0,2);
		}
		int second = Integer.parseInt(tmptime[2]);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, hour, minute, second);
		return calendar.getTimeInMillis();
	}

	public static void test() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss G E D F w W a E F");

	}

	/**
	 * 时间格式转换: 秒-->小时:分钟:秒
	 * @param second
	 * @return
	 */
	public static String second2hour(int second){
		if(second<0){
			return null;
		}
		int hour = second/3600;
		int minute = second%3600/60;
		int second2 = second%3600%60;
		return "" + hour + ":" + StringUtil.addzero(minute, 2)+ ":"+StringUtil.addzero(second2, 2);
	}

	/**
	 * 得到指定时间间隔后的时间
	 * @param starttime 开始时间
	 * @param mode "y":year;"m":month;"d":day;"h":hour;"s":second
	 * @param num 数量
	 * @return
	 */
	public static String getEndDateTime(String starttime,String mode, int num) {
		long ltime = getLongTime(starttime);
		Calendar cld = Calendar.getInstance();
		//if(start>0) cld.setTimeInMillis(start);
		if (ltime > 0) {
			Date dd = new Date();
			dd.setTime(ltime);
			cld.setTime(dd);
		}

		if ("M".equals(mode)) {
			//cld.roll(Calendar.MONTH,amount);
			cld.add(Calendar.MONTH, num);
		}
		if ("m".equals(mode)) {
			//cld.roll(Calendar.MONTH,amount);
			cld.add(Calendar.MINUTE, num);
		}
		if ("d".equals(mode)) {
			//cld.roll(Calendar.DAY_OF_MONTH,amount);
			cld.add(Calendar.DAY_OF_MONTH, num);
		}
		if ("h".equals(mode)) {
			//cld.roll(Calendar.DAY_OF_MONTH,amount);
			cld.add(Calendar.HOUR_OF_DAY, num);
		}
		if ("s".equals(mode)) {
			//cld.roll(Calendar.DAY_OF_MONTH,amount);
			cld.add(Calendar.SECOND, num);
		}

		//return cld.getTimeInMillis();
		int syear = cld.get(Calendar.YEAR);

		int dmonth = cld.get(Calendar.MONTH) + 1;
		String smonth = "" + dmonth;
		if (dmonth < 10) {
			smonth = "0" + dmonth;

		}
		int dday = cld.get(Calendar.DAY_OF_MONTH);
		String sday = "" + dday;
		if (dday < 10) {
			sday = "0" + dday;

		}
		int dhour = cld.get(Calendar.HOUR_OF_DAY);
		String shour = "" + dhour;
		if (dhour < 10) {
			shour = "0" + dhour;

		}
		int dminute = cld.get(Calendar.MINUTE);
		String sminute = "" + dminute;
		if (dminute < 10) {
			sminute = "0" + dminute;

		}
		int dsecond = cld.get(Calendar.SECOND);
		String ssecond = "" + dsecond;
		if (dsecond < 10) {
			ssecond = "0" + dsecond;

		}

		String dtime = syear + "-" + smonth + "-" + sday + " " + shour + ":" + sminute+":" + ssecond;
		return dtime;
	}

	/**
	 * 格式化时间 2006-11-11 11:11
	 * @param datetime
	 * @param len 位数
	 * @return
	 */
	public static String formatTime(String datetime,int len){
		if(!StringUtil.isFine(datetime) ){
			return "";
		}
		if(len<=0){
			return datetime;
		}
		if(datetime.length()>len)
			datetime = datetime.substring(0,len);
		return datetime;
	}
	public static Date convertStringToDate(String strIn, String dateFormat){
		SimpleDateFormat format =
			new SimpleDateFormat(dateFormat);
		Date dateReturn = null;
		try{
			dateReturn = format.parse(strIn);
		}catch(Exception ex){
		}
		return dateReturn;
	}

	

	/**
	 * convert date to String ,  yyyy-MM-dd, yyyy-MM-dd HH:mm:ss
	 * @param dateIn
	 * @param dateFormat
	 * @return
	 */
	public static String convertDateToString(Date dateIn, String dateFormat){
		//String strFormat = "yyyy-MM-dd";
		if (dateIn==null)
			return "";
		SimpleDateFormat format =
			new SimpleDateFormat(dateFormat);

		return format.format(dateIn);
	}

	public static  String getEndDayofMonth(int sYear,int sMonth){
		Calendar   c   =   Calendar.getInstance(); 
		String tEnddate = "";
		c.set(c.YEAR,sYear); 
		c.set(c.MONTH,sMonth);
		tEnddate = String.valueOf(sYear)+"-"+String.valueOf(sMonth)+"-"+c.getActualMaximum(c.DAY_OF_MONTH);
		return tEnddate;
	}

	public static  String getEndDayofMonth(String time) {
		return getEndDayofMonth(Integer.valueOf(time.substring(0,4)),Integer.valueOf( time.substring(5, 7)));
	}
	
	/**
	 * 把日期字符串偏移后返回
	 * @param date 日期字符串
	 * @param n 偏移量
	 * @param format 日期格式
	 * @return
	 */
	public static String dateStrAddToStr(String date,Integer n,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		String reDate = "";
		try {
			cal.setTime(sdf.parse(date));
			cal.add(Calendar.DATE, n);
			reDate = sdf.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return reDate;
	}
	/**
	 * 日期转换为字符串
	 * @param date
	 * @param n
	 * @param format
	 * @return
	 */
	public static String dateAddToStr(Date date,Integer n,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		String reDate = "";
		cal.setTime(date);
		cal.add(Calendar.DATE, n);
		reDate = sdf.format(cal.getTime());
		return reDate;
	}
	public static String dateAddToStr(Date date,Integer day,Integer hour,Integer min,Integer second,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		String reDate = "";
		cal.setTime(date);
		if(day!=null){
			cal.add(Calendar.DATE, day);
		}
		if(hour!=null){
			cal.add(Calendar.HOUR, hour);
		}
		if(min!=null){
			cal.add(Calendar.MINUTE, min);
		}
		if(second!=null){
			cal.add(Calendar.SECOND, second);
		}
		
		reDate = sdf.format(cal.getTime());
		return reDate;
	}
	public static String dateParseToStr(Date date,Integer month,Integer day,Integer hour,Integer min,Integer second,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		String reDate = "";
		cal.setTime(date);
		if(month!=null){
			cal.add(Calendar.MONTH, month);
		}
		if(day!=null){
			cal.add(Calendar.DATE, day);
		}
		if(hour!=null){
			cal.add(Calendar.HOUR, hour);
		}
		if(min!=null){
			cal.add(Calendar.MINUTE, min);
		}
		if(second!=null){
			cal.add(Calendar.SECOND, second);
		}
		
		reDate = sdf.format(cal.getTime());
		return reDate;
	}
	
	public static long chDateToLong(String time,String format) throws ParseException{
		SimpleDateFormat st=new SimpleDateFormat(format);
		return st.parse(time).getTime();
	}
	public static int sumDaysOfMonth(String strDate,String format){  
	   SimpleDateFormat sdf = new SimpleDateFormat(format);
	   Calendar calendar = Calendar.getInstance();
	   Date date = null;
	   try {
		   date = sdf.parse(strDate);
	   }catch (ParseException e) {
		   e.printStackTrace();
	   }
	   calendar.setTime(date);  
	   int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);  
	   return day;
	}
	public static int daysOfMonth(String taskDate,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
	    try {
		   date = sdf.parse(taskDate);
	    }catch (ParseException e) {
		   e.printStackTrace();
	    }
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 比较时间大小  "yyyy-MM-dd hh:mm:ss"
	 * @param DATE1
	 * @param DATE2
	 * @return 1:DATE1 > DATE2;-1:DATE1 < DATE2;0 相等
	 */
    public static int compare_datetime(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1>dt2");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1<dt2");
                return -1;
            } else {
            	System.out.println("dt1==dt2");
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
	public static void main(String[] args) {
		String s = DateTime.getTime(1396513306968l);
       //int i= compare_datetime("1999-12-11 09:59:24", "1999-12-11 09:59:23");
       System.out.println(s);

//		long time = 1163697636931L;
//		//System.out.println("date:" + DateTime.getEndDateTime("2000-01-01 00:00:00","s",3700));
//		System.out.println(DateTime.getDate().substring(0,8)+"01");
//		System.out.println(getWeekName(DateTime.getDate().substring(0,8)+"01"));
//		//test();
//		try {
//			System.out.println(chDateToLong("2013-01-13 13:00:00","yyyy-MM-dd HH:mm:ss"));
//			System.out.println(chDateToLong("2013-01-13 13","yyyy-MM-dd HH"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(getWeekName("2012-01-19"));
//		System.out.println(daysOfMonth("20130325","yyyyMMdd"));
//		System.out.println(daysOfMonth("20130225","yyyyMMdd"));
//		System.out.println(convertDateToString(new Date(), "yyyy年MM月dd日"));
//		System.out.println(dateParseToStr(new Date(),-1,null,null,null,null,"yyyy-MM"));
	}
	

}
