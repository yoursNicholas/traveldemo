package com.webproject.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

/**
 * 日期util类
 */
public class DateUtil {
	static long CONST_WEEK = 3600 * 1000 * 24 * 7;

	public static  Timestamp getTimestamp()
	{
		return new Timestamp(new Date().getTime());
	}
	
	public static Timestamp getTimestamp(Date date)
	{
		
		return new Timestamp(date.getTime());
	}
	/**
	 * 获取某天的开始
	 * @return
	 */
	public static Date getCurrentDateStart() {
		String dateTimeString= getStringDateShort() +" 00:00:00";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date = formatter.parse(dateTimeString,pos);
		return date;
	}
	/**
	 * 获取某月的开始
	 * @return
	 */
	public static Date getCurrentMonthStart() {
		String dateTimeString= getStringMonthShort() +"-01 00:00:00";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date = formatter.parse(dateTimeString,pos);
		return date;
	}
	/**
	 * 获取某年的开始
	 * @return
	 */
	public static Date getCurrentYearStart() {
		String dateTimeString=  getStringYearShort()+"-01-01 00:00:00";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date = formatter.parse(dateTimeString,pos);
		return date;
	}
	public static String getStringMonthShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	public static String getStringYearShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	public static String getStringDateShort2() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	/**
	 * 获取两个时间之间相差的周数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getInternalWeeks(Date date1,Date date2){
		Calendar before = Calendar.getInstance();
		Calendar after = Calendar.getInstance();
		before.setTime(date1);
		after.setTime(date2);
		int week = before.get(Calendar.DAY_OF_WEEK);
		before.add(Calendar.DATE, -week);
		week = after.get(Calendar.DAY_OF_WEEK);
		after.add(Calendar.DATE, 7 - week);
		int interval = (int) ((after.getTimeInMillis() - before
				.getTimeInMillis()) / CONST_WEEK);
		interval = interval - 1;
		return interval;
	}

	/**
	 * 还原LONG类型日戳到日期对象
	 * 
	 * @return Date
	 */
	public static Date longToDate(Long longDate) {
		Date date0 = new Date();
		// 因为setTime的时候需要使用毫秒数，所以要用秒*1000
		date0.setTime(longDate * 1000);
		return date0;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 */
	public static Date getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回短时间格式 yyyy-MM-dd
	 */
	public static Date getNowDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}


	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(java.util.Date dateDate) {
		if(dateDate==null){
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @param k
	 * @return
	 */
	public static String dateToStr(java.util.Date dateDate) {
		// 作个判断
		if (dateDate == null) {
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}
	public static String dateToTime(java.util.Date dateDate) {
		// 作个判断
		if (dateDate == null) {
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}
	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 得到现在时间
	 * 
	 * @return
	 */
	public static Date getNow() {
		Date currentTime = new Date();
		return currentTime;
	}

	/**
	 * 提取一个月中的最后一天
	 * 
	 * 
	 * @param day
	 * @return
	 */
	public static Date getLastDate(long day) {
		Date date = new Date();
		long date_3_hm = date.getTime() - 3600000 * 34 * day;
		Date date_3_hm_date = new Date(date_3_hm);
		return date_3_hm_date;
	}

	/**
	 * 得到现在时间
	 * 
	 * @return 字符串 yyyyMMdd HHmmss
	 */
	public static String getStringToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 得到今天日期
	 */
	public static int getToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		int today = Integer.parseInt(dateString.substring(8, 10));
		return today;
	}

	/**
	 * 得到今天年份字符
	 */
	public static String getStrYear() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		dateString = dateString.substring(0, 4);
		return dateString;
	}

	/**
	 * 得到本月字符
	 */
	public static String getStrMonth() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString.substring(5, 7);
	}

	/**
	 * 得到昨天年月日字符
	 * 
	 */
	public static String getYestodayDate() {
		String datestr = DateUtil.getNextDay(new Date(), "-1");
		datestr = datestr.substring(0, 10);
		return datestr;
	}

	/**
	 * 得到今天年月日字符
	 * 
	 * 
	 */
	public static String getTodayDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		dateString = dateString.substring(0, 10);
		return dateString;
	}

	/**
	 * 得到昨天月份字符
	 */
	public static String getStrBeforeMonth() {
		String datestr = DateUtil.getNextDay(new Date(), "-1");
		datestr = datestr.substring(5, 7);
		return datestr;
	}

	/**
	 * 得到昨天年份字符
	 */
	public static String getStrBeforeYear() {
		String datestr = DateUtil.getNextDay(new Date(), "-1");
		datestr = datestr.substring(0, 4);
		return datestr;
	}

	/**
	 * 得到昨天日期字符
	 */
	public static String getStrYesterday() {
		String datestr = DateUtil.getNextDay(new Date(), "-1");
		datestr = datestr.substring(8, 10);
		return datestr;
	}

	/**
	 * 得到前天月份字符
	 */
	public static String getStrBeforeYesMonth() {
		String datestr = DateUtil.getNextDay(new Date(), "-2");
		datestr = datestr.substring(5, 7);
		return datestr;
	}

	/**
	 * 得到前天年份字符
	 */
	public static String getStrBeforeYesYear() {
		String datestr = DateUtil.getNextDay(new Date(), "-2");
		datestr = datestr.substring(0, 4);
		return datestr;
	}

	/**
	 * 得到前天日期字符
	 */
	public static String getStrBeforeYesterday() {
		String datestr = DateUtil.getNextDay(new Date(), "-2");
		datestr = datestr.substring(8, 10);
		return datestr;
	}

	/**
	 * 得到今天日期字符
	 */
	public static String getStrToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString.substring(8, 10);
	}

	/**
	 * 得到现在小时
	 */
	public static String getHour() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}
	/**
	 * mdf
	 * 信息特征表
	 * 得到现在年后两位
	 */
	public static int getIntYear() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);
		int Time = Integer.parseInt(dateString.substring(2, 4));
		return Time;
	}
	/**
	 * mdf
	 * 信息特征表
	 * 得到现在月
	 */
	public static int getIntMonth() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);
		int Time = Integer.parseInt(dateString.substring(4, 6));
		return Time;
	}
	/**
	 * mdf
	 * 信息特征表
	 * 得到现在日
	 */
	public static int getIntDay() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);
		int Time = Integer.parseInt(dateString.substring(6, 8));
		return Time;
	}
	/**
	 * mdf
	 * 信息特征表
	 * 得到现在小时
	 */
	public static int getIntHour() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);
		int Time = Integer.parseInt(dateString.substring(8, 10));
		return Time;
	}
	/**
	 * mdf
	 * 信息特征表
	 * 得到现在分钟
	 */
	public static int getIntMinute() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);
		int Time = Integer.parseInt(dateString.substring(10, 12));
		return Time;
	}
	/**
	 * mdf
	 * 信息特征表
	 * 得到现在秒钟
	 */
	public static int getIntSecond() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(currentTime);
		int Time = Integer.parseInt(dateString.substring(12));
		return Time;
	}

	/**
	 * 得到现在分钟
	 * 
	 * @return
	 */
	public static String getTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String min;
		min = dateString.substring(14, 16);
		return min;
	}

	/**
	 * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
	 * 
	 * 
	 * @param sformat
	 *            yyyyMMddhhmmss
	 * @return
	 */
	public static String getUserDate(String sformat) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(sformat);
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	/**
	 * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
	 */
	public static String getTwoHour(String st1, String st2) {
		String[] kk = null;
		String[] jj = null;
		kk = st1.split(":");
		jj = st2.split(":");
		if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
			return "0";
		else {
			double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1])
					/ 60;
			double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1])
					/ 60;
			if ((y - u) > 0)
				return y - u + "";
			else
				return "0";
		}
	}

	/**
	 * 得到二个日期间的间隔天数
	 */
	public static long getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			java.util.Date date = myFormatter.parse(sj1);
			java.util.Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return 0;
		}
		return day;
	}

	/**
	 * 时间前推或后推分钟,其中JJ表示分钟.
	 */
	public static String getPreTime(String sj1, String jj) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mydate1 = "";
		try {
			Date date1 = format.parse(sj1);
			long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
			date1.setTime(Time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
		}
		return mydate1;
	}

	/**
	 * 时间前推或后推秒钟,其中sec表示秒.
	 */
	public static String getPreSec(String sj1, int sec) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mydate1 = "";
		try {
			Date date1 = format.parse(sj1);
			long Time = (date1.getTime() / 1000) + sec;
			date1.setTime(Time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
		}
		return mydate1;
	}

	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 * 
	 */
	public static String getNextDay(String nowdate, String delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String mdate = "";
			Date d = strToDate(nowdate);
			long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24
					* 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 * 
	 */
	public static String getNextDay(Date d, String delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String mdate = "";
			long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24
					* 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 判断二个时间是否在同一个周
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周

			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

	/**
	 * 产生周序列,即得到当前时间所在的年度是第几周
	 * 
	 * @return
	 */
	public static String getSeqWeek() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0" + week;
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + week;
	}

	/**
	 * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
	 * 
	 * 
	 * @param sdate
	 * @param num
	 * @return
	 */
	public static String getWeek(String sdate, String num) {
		// 再转换为时间
		Date dd = DateUtil.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if (num.equals("1")) // 返回星期一所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		else if (num.equals("2")) // 返回星期二所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		else if (num.equals("3")) // 返回星期三所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		else if (num.equals("4")) // 返回星期四所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		else if (num.equals("5")) // 返回星期五所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		else if (num.equals("6")) // 返回星期六所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		else if (num.equals("0")) // 返回星期日所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = DateUtil.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	public static String getWeekStr(String sdate) {
		String str = "";
		str = DateUtil.getWeek(sdate);
		if ("1".equals(str)) {
			str = "星期日";
		} else if ("2".equals(str)) {
			str = "星期一";
		} else if ("3".equals(str)) {
			str = "星期二";
		} else if ("4".equals(str)) {
			str = "星期三";
		} else if ("5".equals(str)) {
			str = "星期四";
		} else if ("6".equals(str)) {
			str = "星期五";
		} else if ("7".equals(str)) {
			str = "星期六";
		}
		return str;
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间

		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		java.util.Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	 * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
	 * 此函数返回该日历第一行星期日所在的日期
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getNowMonth(String sdate) {
		// 取该时间所在月的一号

		sdate = sdate.substring(0, 8) + "01";

		// 得到这个月的1号是星期几

		Date date = DateUtil.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int u = c.get(Calendar.DAY_OF_WEEK);
		String newday = DateUtil.getNextDay(sdate, (1 - u) + "");
		return newday;
	}



	/**
	 * 返回一个随机数
	 * 
	 * @param i
	 * @return
	 */
	public static String getRandom(int i) {
		Random jjj = new Random();
		// int suiJiShu = jjj.nextInt(9);
		if (i == 0)
			return "";
		String jj = "";
		for (int k = 0; k < i; k++) {
			jj = jj + jjj.nextInt(9);
		}
		return jj;
	}

	public static long getLong(Date date, int addDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, addDay);
		return cal.getTime().getTime();
	}

	// date转long
	public static long getLongs(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getTime().getTime() / 1000;
	}

	public static long getLong(int year, int month, int day, int hour, int min) {
		Date date = new Date(year, month, day, hour, min);
		return date.getTime();
	}

	public static int getYear() {
		return new Date().getYear() + 1900;
	}

	public static int getMonth() {
		return new Date().getMonth() + 1;
	}

	public static void main(String[] args) {
		System.out.println(getInternalWeeks(strToDate("2016-7-17"), new Date()));
	}
}
