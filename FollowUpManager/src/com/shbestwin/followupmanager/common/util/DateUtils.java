/**
 * @COPYRIGHT Shanghai RaxTone-Tech Co.,Ltd.
 * @Title: DateUtils.java  
 * @Description: TODO 
 * @author Mojiajing 	 
 * @date 2012-2-8
 * @version V1.0 
 * 
 * Modification History: 
 * 2012-2-8  |  Mojiajing   |  Created 
 */
package com.shbestwin.followupmanager.common.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import android.text.format.Time;

/**
 * Description: 时间转换工具类 .<br>
 * <p>
 * 
 * @author jiajing.mo
 * @version V1.0
 */
public class DateUtils {

	private static Map<String, SimpleDateFormat> formatMap = new HashMap<String, SimpleDateFormat>();

	private static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
	private static SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

	public static String formatStartCountdownTime(long time) {
		StringBuilder format = new StringBuilder("####.#");
		DecimalFormat df = new DecimalFormat(format.toString());
		return df.format(time / 3600000.0);
	}

	/**
	 * 根据出生日期获取年龄
	 *
	 * @param birthday
	 * @return
	 */
	public static int getAgeByBirthday(String birthday) {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthday)) {
			throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		Date date = parseDate(birthday, "yyyy-MM-dd");
		cal.setTime(date);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		}
		return age;
	}

	/**
	 * 返回某月的开始时间，即2014-04-01 00:00:00
	 * 
	 * @param diffMonth
	 *            0：表示本月， 1：表示下月 ，-1：表示上月
	 * @return
	 */
	public static Date getMonthStartTime(int diffMonth, Date currentDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		Date date = null;
		try {
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) + diffMonth);
			c.set(Calendar.DATE, 1);
			date = shortSdf.parse(shortSdf.format(c.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 返回某月的结束时间，即2014-04-30 23:59:59
	 * 
	 * @param diffMonth
	 *            0：表示本月， 1：表示下月 ，-1：表示上月
	 * @return
	 */
	public static Date getMonthEndTime(int diffMonth, Date currentDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		Date day = null;
		try {
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) + diffMonth);
			c.set(Calendar.DATE, 1);// 表示为本月的第一天
			c.add(Calendar.MONTH, 1);// 增加一个月
			c.add(Calendar.DATE, -1);// 减一天即为上月的最后一天
			day = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return day;
	}

	/**
	 * 格式为周几/今天/明天/后天 00:00
	 * 
	 * @param timeMills
	 * @return
	 */
	public static String formatTime(long timeMills) {
		StringBuilder result = new StringBuilder();
		// 今天的开始日期
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);

		long diff = timeMills - todayStart.getTimeInMillis();
		if (timeMills > 0) {
			if (diff >= 0 && diff < 24 * 60 * 60 * 1000) {
				result.append("今天 ");
			} else if (diff >= 24 * 60 * 60 * 1000 && diff < 2 * 24 * 60 * 60 * 1000) {
				result.append("明天 ");
			} else if (diff >= 2 * 24 * 60 * 60 * 1000 && diff < 3 * 24 * 60 * 60 * 1000) {
				result.append("后天 ");
			}
		}
		if (result.length() <= 0) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(timeMills);
			int week = c.get(Calendar.DAY_OF_WEEK);
			switch (week) {
			case 1:
				result.append("周日 ");
				break;
			case 2:
				result.append("周一 ");
				break;
			case 3:
				result.append("周二 ");
				break;
			case 4:
				result.append("周三 ");
				break;
			case 5:
				result.append("周四 ");
				break;
			case 6:
				result.append("周五 ");
				break;
			case 7:
				result.append("周六 ");
				break;
			}
		}
		SimpleDateFormat df = new SimpleDateFormat("HH:mm", Locale.CHINA);
		result.append(df.format(new Date(timeMills)));
		return result.toString();
	}

	/**
	 * 将要字符串转换成Date类型
	 * <p>
	 * 
	 * @param str
	 *            要转换的字符串
	 * @param format
	 *            转换格式 例如：yyyy-MM-dd HH:mm:ss
	 * @return <p>
	 * @author jiajing.mo
	 * @date 2012-2-10
	 */
	public static Date parseDate(String str, String format) {
		if (str == null || "".equals(str)) {
			return null;
		}
		SimpleDateFormat sdf = formatMap.get(format);
		if (null == sdf) {
			sdf = new SimpleDateFormat(format, Locale.CHINA);
			formatMap.put(format, sdf);
		}
		try {
			synchronized (sdf) {
				return sdf.parse(str);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把Date类型的时间转换成字符串
	 * <p>
	 * 
	 * @param date
	 *            要转换的时间
	 * @param format
	 *            转换格式 例如：yyyy-MM-dd HH:mm:ss
	 * @return <p>
	 * @author jiajing.mo
	 * @date 2012-2-10
	 */
	public static String formatDate(Date date, String format) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = formatMap.get(format);
		if (null == sdf) {
			sdf = new SimpleDateFormat(format, Locale.CHINA);
			formatMap.put(format, sdf);
		}
		synchronized (sdf) {
			return sdf.format(date);
		}
	}

	/**
	 * 把时间戳类型的时间转换成字符串
	 * <p>
	 * 
	 * @param time
	 *            要转换的时间
	 * @param format
	 *            转换格式 例如：yyyy-MM-dd HH:mm:ss
	 * @return <p>
	 * @author jiajing.mo
	 * @date 2012-2-10
	 */
	public static String formatDate(Long time, String format) {
		Date date = new Date(time);
		date.toGMTString();
		return formatDate(date, format);
	}

	/**
	 * 把秒转换成 小时：分钟：秒 的形式
	 * <p>
	 * 
	 * @param seconds
	 *            要转换的秒
	 * 
	 * @return time 00 + ":" + 00 + ":" + 00形式的字符串
	 *         <p>
	 * @author Liangxianlin
	 * @date 2012-1-2
	 */
	public static String changSecondsToTime(long seconds) {
		StringBuffer time = new StringBuffer();

		time.append(MathsUtils.retainInteger(seconds / 3600, 2));
		time.append(":");
		time.append(MathsUtils.retainInteger((seconds % 3600) / 60, 2));
		time.append(":");
		time.append(MathsUtils.retainInteger(seconds % 60, 2));

		return time.toString();
	}

	/**
	 * 转换成 XX小时XX分，
	 * 
	 * @param seconds
	 * @return
	 */
	public static String[] changeSecondsToHoursMinutes1(long seconds) {
		String[] result = new String[] { "0", "0" };
		if (seconds >= 3600) {
			int hours = (int) (seconds / 3600);
			result[0] = String.valueOf(hours);
		}
		int minute = (int) ((seconds % 3600) / 60);
		if (minute > 0) {
			result[1] = String.valueOf(minute);
		}
		return result;
	}

	/**
	 * 转换成 XX小时XX分，
	 * 
	 * @param seconds
	 * @return
	 */
	public static String changeSecondsToHoursMinutes(double seconds) {
		if (seconds == 0) {
			return "0分钟";
		}
		StringBuffer time = new StringBuffer();
		if (seconds >= 3600) {
			int hours = (int) (seconds / 3600);
			time.append(hours).append("小时");
		}
		int minute = (int) ((seconds % 3600) / 60);
		if (minute > 0) {
			time.append(minute).append("分钟");
		}
		if (time.toString().isEmpty()) {
			time.append("0分钟");
		}
		return time.toString();
	}

	/**
	 * Use by Navi
	 * 
	 * @param seconds
	 * @return
	 */
	public static String changSecondsToTimeByCN(long seconds) {
		StringBuffer time = new StringBuffer();
		int hour = (int) seconds / 3600;
		if (hour != 0) {
			time.append(hour).append("小时");
		}

		int second = (int) (seconds % 3600) / 60;
		if (second < 1 && hour == 0) {
			second = 1;
		}

		if (second != 0) {
			time.append(second).append(hour > 0 ? "分" : "分钟");
		}
		return time.toString();
	}

	/**
	 * 把分钟转换成 天: 小时：分钟 的形式
	 * <p>
	 * 1天=24小时
	 * 
	 * @param minutes
	 *            要转换的分钟
	 * 
	 * @return time 天: 小时：分钟 形式的字符串
	 *         <p>
	 * @author xianlin.liang
	 * @date 2012-1-2
	 */
	public static String changSecondsToDay(long minutes) {
		StringBuffer time = new StringBuffer();

		int day = (int) (minutes / 1440);
		if (day != 0) {
			time.append(day).append("天");
		}

		int hour = (int) (minutes % 1440) / 60;
		if (hour != 0) {
			time.append(hour).append("小时");
		}

		int second = (int) minutes % 60;
		if (second == 0 && time.length() == 0) {
			second = 1;
		}

		if (second != 0) {
			time.append(second).append("分钟");
		}
		return time.toString();
	}

	/**
	 * 把秒转换成 小时：分钟 的形式
	 * <p>
	 * 
	 * @param seconds
	 *            要转换的秒
	 * 
	 * @return time 00 + ":" + 00 形式的字符串
	 *         <p>
	 * @author xianlin.liang
	 * @date 2012-1-2
	 */
	public static String changSecondsToHourMinute(long seconds) {
		StringBuffer time = new StringBuffer();

		time.append(MathsUtils.retainInteger(seconds / 3600, 2));
		time.append(":");
		time.append(MathsUtils.retainInteger((seconds % 3600) / 60, 2));

		return time.toString();
	}

	public static int changSecondsToMinute(long seconds) {
		int minute = (int) seconds / 60;
		return minute > 0 ? minute : 1;
	}

	public static String getNowTime() {
		Time now = new Time();
		now.setToNow();
		String hour = MathsUtils.retainInteger(now.hour, 2);
		String minu = MathsUtils.retainInteger(now.minute, 2);
		return hour + ":" + minu;
	}

	public static int changMillisecondToDay(long millisecond) {
		return (int) Math.ceil(millisecond * 1.0 / (24 * 60 * 60 * 1000));
	}

	public static long[] getSaveTime(String minuteStr) {
		long minute = 0;
		if (minuteStr != null) {
			try {
				minute = Long.valueOf(minuteStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		long[] saveTime = new long[2];
		saveTime[0] = minute / 60;
		saveTime[1] = minute % 60;
		return saveTime;
	}

	public static boolean isSameDay(long when1, long when2) {
		Time time = new Time();
		time.set(when1);
		int thenYear = time.year;
		int thenMonth = time.month;
		int thenMonthDay = time.monthDay;
		time.set(when2);
		return (thenYear == time.year) && (thenMonth == time.month) && (thenMonthDay == time.monthDay);
	}

	public static String getArriveTimeText(long timeMills) {
		long currentTime = System.currentTimeMillis();
		StringBuilder timeText = new StringBuilder(DateUtils.formatDate(currentTime + timeMills, "HH:mm"));
		Calendar nowTime = Calendar.getInstance();
		Calendar arrvalTime = Calendar.getInstance();
		arrvalTime.setTimeInMillis(nowTime.getTimeInMillis() + timeMills);
		int nowDay = nowTime.get(Calendar.DAY_OF_YEAR);
		int arrvalDay = arrvalTime.get(Calendar.DAY_OF_YEAR);

		int subDay = 0;
		if (nowTime.get(Calendar.YEAR) == arrvalTime.get(Calendar.YEAR)) {
			subDay = arrvalDay - nowDay;
		} else {
			subDay = nowTime.getActualMaximum(Calendar.DAY_OF_YEAR) - nowDay + arrvalDay;
		}

		switch (subDay) {
		// WARING 空格很重要
		case 0:
			break;
		case 1:
			timeText = timeText.insert(0, "明天 ");
			break;
		case 2:
			timeText = timeText.insert(0, "后天 ");
			break;
		default:
			timeText.delete(0, timeText.length());
			timeText.append(DateUtils.formatDate(currentTime + timeMills, "dd日 HH:mm"));
			break;
		}
		return timeText.toString();
	}

	/**
	 * 
	 * @param second
	 * @param format
	 *            "DD-HH-MM"
	 * @return
	 */
	public static String formatTimeForCHN(long second) {
		long nd = 24 * 60 * 60;// 一天的毫秒数
		long nh = 60 * 60;// 一小时的毫秒数
		long nm = 60;// 一分钟的毫秒数
		long day = second / nd;// 计算差多少天
		long hour = second % nd / nh;// 计算差多少小时
		long min = second % nd % nh / nm;// 计算差多少分钟

		StringBuffer sb = new StringBuffer();
		if (day > 0) {
			sb.append(day).append("天");
		}
		if (hour > 0) {
			sb.append(hour).append("小时");
		}
		if (min > 0) {
			sb.append(min).append("分钟");
		}

		if (day <= 0 && hour <= 0 && min <= 0) {
			sb.append(0).append("分钟");
		}
		return sb.toString();
	}

	/**
	 * @return format MM-dd HH:mm
	 */
	public static String formatTimeForMDH(long milliseconds) {
		Date data = new Date(milliseconds);
		StringBuffer str = new StringBuffer();
		str.append(DateUtils.formatDate(data.getTime(), "MM/dd"));
		str.append(" ");
		str.append(DateUtils.formatDate(data.getTime(), "HH:mm"));
		return str.toString();
	}

	/**
	 * 秒转换时间
	 * 
	 * @param seconds
	 * @return index 0:小时,1:分.
	 */
	public static int[] changeTimerArray(long seconds) {
		int[] data = new int[2];
		int hour = (int) seconds / 3600;
		int second = (int) (seconds % 3600) / 60;
		if (second < 1 && hour == 0) {
			second = 1;
		}
		data[0] = hour;
		data[1] = second;
		return data;
	}
}
