package com.aiiju.store.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: AppUtil
 * @Description: 系统工具函数类
 * @author 乔巴
 * @date 2017年08月24日 
 *
 */
public class AppUtil {

	/**
	 * 系统可以识别的最小数,小于该数的认为是0
	 */
	private static final double MIN_NUMBER = 0.000001;

	/**
	 * 缺省的日期格式
	 */
	private static final String DAFAULT_DATE_FORMAT = "yyyy-M-d";

	/**
	 * 日期格式
	 */
	private static String DATE_FORMAT = DAFAULT_DATE_FORMAT;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	/**
	 * 缺省的时间格式
	 */
	private static final String DAFAULT_TIME_FORMAT = "yyyy-M-d HH:mm:ss";

	/**
	 * 时间格式
	 */
	private static String TIME_FORMAT = DAFAULT_TIME_FORMAT;

	private static SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);

	/**
	 * 缺省的BigDecimal格式
	 */
	private static final String DAFAULT_BigDecimal_FORMAT = "#0.##";

	/**
	 * 金额格式
	 */
	private static String BigDecimal_FORMAT = DAFAULT_BigDecimal_FORMAT;

	/**
	 * bigDecimal的格式化对象,在set BigDecimal_FORMAT时自动重建
	 */
	private static NumberFormat bigDecimalFormat = new DecimalFormat(BigDecimal_FORMAT);

	private static final String DAFAULT_AMOUNT_FORMAT = "#,##0.00";
	private static String AMOUNT_FORMAT = DAFAULT_AMOUNT_FORMAT;
	private static NumberFormat amountFormat = new DecimalFormat(AMOUNT_FORMAT);

	/**
	 * 格式化Calendar
	 * 
	 * @param calendar
	 * @return
	 */
	public static String formatCalendar(Calendar calendar) {
		if (calendar == null) {
			return "";
		}
		return dateFormat.format(calendar.getTime());
	}

	public static String formatDate(Date d) {
		if (d == null) {
			return "";
		}
		return dateFormat.format(d);
	}

	/**
	 * 格式化时间
	 * 
	 * @param calendar
	 * @return
	 */
	public static String formatTime(Date d) {
		if (d == null) {
			return "";
		}
		return timeFormat.format(d);
	}

	/**
	 * 格式化整数型日期
	 * 
	 * @param intDate
	 * @return
	 */
	public static String formatIntDate(Integer intDate) {
		if (intDate == null) {
			return "";
		}
		Calendar c = newCalendar(intDate);
		return formatCalendar(c);
	}

	/**
	 * 取得Integer型的当前日期
	 * 
	 * @return
	 */
	public static Integer getIntNow() {
		return getIntDate(getNow());
	}

	/**
	 * 取得Integer型的当前日期
	 * 
	 * @return
	 */
	public static Integer getIntToday() {
		return getIntDate(getNow());
	}

	public static String getStringToday() {
		return getIntDate(getNow()) + "";
	}

	/**
	 * 根据年月日获取整型日期
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Integer getIntDate(int year, int month, int day) {
		return getIntDate(newCalendar(year, month, day));
	}

	/**
	 * 某年月的第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Integer getFistDay(int year, int month) {
		return getIntDate(newCalendar(year, month, 1));
	}

	/**
	 * 某年月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Integer getLastDay(int year, int month) {
		return intDateSub(getIntDate(newCalendar(year, month + 1, 1)), 1);
	}

	/**
	 * 根据Calendar获取整型日期
	 * 
	 * @param c
	 * @return
	 */
	public static Integer getIntDate(Calendar c) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		return year * 10000 + month * 100 + day;
	}

	/**
	 * 根据Date获取整型日期
	 * 
	 * @param d
	 * @return
	 */
	public static Integer getIntDate(Date d) {
		if (d == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return getIntDate(c);
	}

	/**
	 * 根据Integer获取Date日期
	 * 
	 * @param n
	 * @return
	 */
	public static Date getDate(Integer n) {
		if (n == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.set(n / 10000, n / 100 % 100 - 1, n % 100);
		return c.getTime();
	}

	/**
	 * 根据年月日生成Calendar
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Calendar newCalendar(int year, int month, int day) {
		Calendar ret = Calendar.getInstance();
		if (year < 100) {
			year = 2000 + year;
		}
		ret.set(year, month - 1, day);
		return ret;
	}

	/**
	 * 根据整型日期生成Calendar
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar newCalendar(int date) {
		int year = date / 10000;
		int month = (date % 10000) / 100;
		int day = date % 100;

		Calendar ret = Calendar.getInstance();
		ret.set(year, month - 1, day);
		return ret;
	}

	/**
	 * 取得Date型的当前日期
	 * 
	 * @return
	 */
	public static Date getNow() {
		return new Date();
	}

	/**
	 * 整数型日期的加法
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Integer intDateAdd(int date, int days) {
		int year = date / 10000;
		int month = (date % 10000) / 100;
		int day = date % 100;

		day += days;

		return getIntDate(year, month, day);
	}

	/**
	 * 整数型日期的减法
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Integer intDateSub(int date, int days) {
		return intDateAdd(date, -days);
	}

	/**
	 * 计算两个整型日期之间的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Integer daysBetweenDate(Integer startDate, Integer endDate) {
		if (startDate == null || endDate == null) {
			return null;
		}
		Calendar c1 = newCalendar(startDate);
		Calendar c2 = newCalendar(endDate);

		Long lg = (c2.getTimeInMillis() - c1.getTimeInMillis()) / 1000 / 60 / 60 / 24;
		return lg.intValue();
	}

	// -----------------------------------------------------------日期相关end

	/**
	 * 判断文件名是否是Excel文件类型
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isExcelFileName(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return false;
		}
		return fileName.toLowerCase().endsWith(".xls");
	}

	public static String getStringDate() {
		return getStringDate(AppUtil.getNow());
	}

	/**
	 * 根据calendar产生字符串型日期
	 * 
	 * @param d
	 * @return eg:20080707
	 */
	public static String getStringDate(Date d) {
		if (d == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(d);
	}

	/**
	 * 解析字符串的日期类型为日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseDate(String date) {
		Integer intDate = parseIntDate(date);
		return getDate(intDate);
	}

	/**
	 * 解析时间毫秒数为时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseDateTime(Long timeMillis) {
		return new Date(timeMillis);
	}

	public static Date parseDateTime(String timeMillis) {
		if (timeMillis == null) {
			return null;
		}
		return new Date(Long.parseLong(timeMillis));
	}

	/**
	 * 解析字符串的日期类型为Integer类型的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Integer parseIntDate(String date) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		date = StringUtils.replaceChars(date, '.', '-');
		date = StringUtils.replaceChars(date, '/', '-');
		date = StringUtils.replaceChars(date, '\\', '-');

		String[] dates = date.split("-");
		try {
			if (dates.length > 2) {
				return getIntDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
			} else {
				return Integer.parseInt(date);
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 解析boolean型
	 * 
	 * @param s
	 * @return
	 */
	public static Boolean parseBoolean(String s) {
		s = StringUtils.stripToNull(s);
		if (s == null) {
			return false;
		}
		if (s.equals("1") || s.equalsIgnoreCase("true")) {
			return true;
		}
		if (s.equalsIgnoreCase("t") || s.equalsIgnoreCase("y")) {
			return true;
		}

		return false;
	}

	/**
	 * 转换like string
	 * 
	 * @param s
	 * @return
	 */
	public static String getLikeString(String s) {
		String ret = s.replace('*', '%');
		ret = ret.replace('?', '_');
		if (!ret.endsWith("%")) {
			ret = ret + "%";
		}
		return ret;
	}

	// ---------------------------------------------------
	/**
	 * BigDecimal加法
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	public static BigDecimal add(BigDecimal bd1, BigDecimal bd2) {
		if (bd1 == null) {
			bd1 = BigDecimal.ZERO;
		}
		if (bd2 == null) {
			bd2 = BigDecimal.ZERO;
		}
		return bd1.add(bd2);
	}

	/**
	 * BigDecimal减法
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	public static BigDecimal subtract(BigDecimal bd1, BigDecimal bd2) {
		if (bd1 == null) {
			bd1 = BigDecimal.ZERO;
		}
		if (bd2 == null) {
			bd2 = BigDecimal.ZERO;
		}
		return bd1.subtract(bd2);
	}

	/**
	 * bigdecimal的比较,大于
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	public static boolean gt(BigDecimal bd1, BigDecimal bd2) {
		if (bd1 == null) {
			return false;
		}
		if (bd2 == null) {
			return true;
		}
		return bd1.subtract(bd2).doubleValue() > 0;
	}

	/**
	 * bigdecimal的比较,大于等于
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	public static boolean ge(BigDecimal bd1, BigDecimal bd2) {
		if (bd1 == null && bd2 == null) {
			return false;
		}
		if (bd1 == null) {
			return false;
		}
		if (bd2 == null) {
			return true;
		}
		return bd1.subtract(bd2).doubleValue() >= 0;
	}

	/**
	 * bigdecimal的比较
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	public static boolean lt(BigDecimal bd1, BigDecimal bd2) {
		if (bd1 == null) {
			return true;
		}
		if (bd2 == null) {
			return false;
		}
		return bd1.subtract(bd2).doubleValue() < 0;
	}

	/**
	 * BigDecimal乘法
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal bd1, BigDecimal bd2) {
		if (bd1 == null) {
			Log.error(Log.LOG_ERROR_PREFIX + "bd1 is null");
			return BigDecimal.ZERO;
		}
		if (bd2 == null) {
			Log.error(Log.LOG_ERROR_PREFIX + "bd2 is null");
			return BigDecimal.ZERO;
		}
		return bd1.multiply(bd2);
	}

	/**
	 * BigDecimal除法
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 */
	public static BigDecimal divide(BigDecimal bd1, BigDecimal bd2) {
		if (isZero(bd1)) {
			return BigDecimal.ZERO;
		}
		if (isZero(bd2)) {
			return BigDecimal.ONE;
		}
		return bd1.divide(bd2, 2, RoundingMode.HALF_UP);
	}

	/**
	 * 判断一个BigDecimal的数值是否为0
	 * 
	 * @param bd
	 * @return
	 */
	public static boolean isZero(BigDecimal bd) {
		if (bd == null) {
			return true;
		}
		return Math.abs(bd.doubleValue()) <= MIN_NUMBER;
	}

	/**
	 * 判断一个BigDecimal的数值是否**不**为0
	 * 
	 * @param bd
	 * @return
	 */
	public static boolean isNotZero(BigDecimal bd) {
		return !isZero(bd);
	}

	/**
	 * 去掉前后的空白字符,如果为"null",也返回""
	 */
	public static String stripToEmpty(String s) {
		String ret = StringUtils.stripToEmpty(s);
		if (ret.equals("null")) {
			ret = "";
		}
		return ret;
	}

	/**
	 * 格式化BigDecimal,把绝对值小于MIN_NUMBER的显示为""
	 * 
	 * @param o
	 * @return
	 */
	public static String formatBigDecimal(BigDecimal o) {
		return formatBigDecimal(o, true);
	}

	public static String formatBigDecimal(Object o) {
		if (o == null) {
			return "";
		}
		if (o instanceof BigDecimal) {
			return formatBigDecimal((BigDecimal) o, true);
		}
		return o.toString();
	}

	public static String formatAmount(BigDecimal a) {
		return formatAmount(a, false);
	}

	public static String formatAmount(BigDecimal a, boolean emptyIfZero) {
		if (a == null) {
			a = BigDecimal.ZERO;
		}
		if (isZero(a) && emptyIfZero) {
			return "";
		}
		return amountFormat.format(a);
	}

	/**
	 * 格式化BigDecimal
	 * 
	 * @param o
	 * @param emptyIfZero
	 *            true:把绝对值小于MIN_NUMBER的显示为""
	 * @return
	 */
	public static String formatBigDecimal(BigDecimal o, boolean emptyIfZero) {
		if (o == null) {
			return "";
		}
		if (isZero(o) && emptyIfZero) {
			return "";
		}
		return bigDecimalFormat.format(o);
	}

	/**
	 * 去掉前后的空白字符,如果为"null",也返回""
	 * 
	 * @param s
	 * @return
	 */
	public static String stripToEmpty(Object o) {
		if (o == null) {
			return "";
		}
		return stripToEmpty(o.toString());
	}

	public static String toStringText(Integer o) {
		return toStringText(o, true);
	}

	public static String toStringText(Integer o, boolean emptyIfZero) {
		if (o == null) {
			return "";
		}
		if (isZero(o) && emptyIfZero) {
			return "";
		}
		return o.toString();
	}

	public static boolean isZero(Object o) {
		if (o == null) {
			return true;
		}

		return false;
	}

	public static boolean isZero(Double o) {
		if (o == null || o < MIN_NUMBER) {
			return true;
		}

		return false;
	}

	public static boolean isZero(Integer o) {
		if (o == null || o == 0) {
			return true;
		}

		return false;
	}

	public static boolean isZero(Long o) {
		if (o == null || o == 0L) {
			return true;
		}

		return false;
	}

	public static String getDATE_FORMAT() {
		return DATE_FORMAT;
	}

	public static void setDATE_FORMAT(String date_format) {
		DATE_FORMAT = date_format;
		dateFormat = new SimpleDateFormat(DATE_FORMAT);
	}

	public static String getBigDecimal_FORMAT() {
		return BigDecimal_FORMAT;
	}

	public static void setBigDecimal_FORMAT(String bigDecimal_FORMAT) {
		BigDecimal_FORMAT = bigDecimal_FORMAT;
		bigDecimalFormat = new DecimalFormat(BigDecimal_FORMAT);
	}

	public static NumberFormat getBigDecimalFormat() {
		return bigDecimalFormat;
	}

	public static BigDecimal multiply(BigDecimal bd1, Integer bd2) {
		if (bd2 == null) {
			return BigDecimal.ZERO;
		}
		return multiply(bd1, new BigDecimal(bd2));
	}

	/**
	 * 仅*保留*小数位 <br>
	 * eg: remainder(8.342) ==> 0.342
	 * 
	 * @param bd
	 * @return
	 * @deprecated
	 */
	public static BigDecimal remainder(BigDecimal bd, BigDecimal divisor) {
		if (bd == null) {
			return BigDecimal.ZERO;
		}
		if (divisor == null || divisor.equals(BigDecimal.ZERO)) {
			divisor = BigDecimal.ONE;
		}
		return bd.remainder(BigDecimal.ONE);
	}

	/**
	 * 四舍五入
	 * 
	 * @param bd
	 * @param scale
	 * @return
	 */
	public static BigDecimal round(BigDecimal bd, int scale) {
		if (bd == null) {
			return null;
		}
		return bd.setScale(scale, RoundingMode.HALF_UP);
	}

	/**
	 * 比较bd1与bd2,如果bd1>bd2返回true,如果是bd1<bd2返回false;
	 * 
	 * @param bd1
	 * @param bd2
	 * @return
	 * @throws Exception
	 */
	public static boolean compareBigdecimal(BigDecimal bd1, Integer bd2) throws Exception {
		if (bd1 == null || bd2 == null) {
			throw new Exception("比较参数里不能有null值.");
		}
		return bd1.doubleValue() > new Double(bd2);
	}

	/**
	 * 求星期几
	 * 
	 * @param d
	 * @return
	 */
	public static int getWeekDay(Calendar c) {
		return c.get(Calendar.DAY_OF_WEEK);
	}

	public static int getWeekDay(Date d) {
		if (d == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	final public static String[] WEEKDAYS = new String[] { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	/**
	 * 求中文的星期几
	 * 
	 * @param c
	 * @return
	 * @throws Exception
	 */
	public static String getWeekDayZh(Date c) throws Exception {
		int weekDay = getWeekDay(c);
		if (weekDay < 1 || weekDay > 7) {
			throw new Exception("无效的周内天数:" + weekDay + ",应该在1~7之间");
		}
		return WEEKDAYS[weekDay - 1];

	}

	/**
	 * 求今天中文的星期几
	 * 
	 * @param c
	 * @return
	 * @throws Exception
	 */
	public static String getWeekDayZh() throws Exception {
		return getWeekDayZh(getNow());
	}

	/**
	 * 获得字符串型的时间戳
	 * 
	 * @return
	 */
	public static String getTimestamptString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String s = sdf.format(new Date());
		return s;
	}

	/**
	 * 求今天星期几
	 * 
	 * @return
	 */
	public static int getWeekDay() {
		return getWeekDay(getNow());
	}

	public static BigDecimal parseBigDecimal(String s) {
		if (StringUtils.isBlank(s)) {
			return BigDecimal.ZERO;
		}
		return new BigDecimal(s);
	}

	public static BigDecimal newBigDecimal(String s) {
		if (StringUtils.isBlank(s)) {
			return BigDecimal.ZERO;
		}
		return new BigDecimal(s);
	}

	public static BigDecimal newBigDecimal(Object s) {
		if (s == null) {
			return BigDecimal.ZERO;
		}
		if (StringUtils.isBlank(s.toString())) {
			return BigDecimal.ZERO;
		}
		return new BigDecimal(s.toString());
	}

	public static Calendar newCalendar(Date date) {
		if (date == null) {
			return null;
		}
		Calendar ret = Calendar.getInstance();
		ret.setTime(date);
		return ret;
	}

	public static boolean after(Date c1, Date c2) {
		if (c1 == null) {
			return false;
		}
		if (c2 == null) {
			return true;
		}
		return c1.after(c2);
	}


	/**
	 * clone一个javabean
	 * 
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static Object cloneBean(Object o) throws Exception {
		try {
			return BeanUtils.cloneBean(o);
		} catch (Exception e) {
			throw new Exception("cloneBean错误," + o);
		}
	}



	public static BigDecimal negate(BigDecimal bd) {
		if (bd == null) {
			return BigDecimal.ZERO;
		}
		return bd.negate();
	}

	/**
	 * 解析Integer
	 * 
	 * @param text
	 * @return
	 */
	public static Integer parseInteger(String text) {
		return Integer.parseInt(text);
	}
}
